package de.gruppe_D.features.uebersicht;

import de.gruppe_D.app.Router;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UebersichtController {

    private final UebersichtView view;
    private final UebersichtService service;
    private final Router router;

    private List<UebersichtModel> aktuelleDaten;
    private int selectedModelRow = -1;

    public UebersichtController(UebersichtView view, UebersichtService service, Router router) {
        this.view = view;
        this.service = service;
        this.router = router;

        ladeDaten();
        initListeners();
    }

    private void ladeDaten() {
        view.tableModel.setRowCount(0);
        aktuelleDaten = service.getAllBewerbungen();

        for (UebersichtModel model : aktuelleDaten) {
            view.tableModel.addRow(new Object[]{
                    "🔍", model.getFirma(), model.getStelle(), model.getOrt(),
                    model.getPlattform(), model.getVersendetAm(), model.getFrist(), model.getStatus()
            });

            boolean exists = false;
            for (int i = 0; i < view.cbFilterUnternehmen.getItemCount(); i++) {
                if (view.cbFilterUnternehmen.getItemAt(i).equals(model.getFirma())) {
                    exists = true; break;
                }
            }
            if (!exists) view.cbFilterUnternehmen.addItem(model.getFirma());
        }
    }

    private void initListeners() {
// --- SIDEBAR ROUTING ---
        view.btnNavDashboard.addActionListener(e -> router.showDashboard2());
        view.btnNavUebersicht.addActionListener(e -> router.showUebersicht()); // Lädt die Übersicht neu
        //view.btnLogout.addActionListener(e -> router.showAuth());
        
        // --- FILTER LOGIK ---
        java.awt.event.ActionListener filterAction = e -> applyFilters();
        view.cbFilterUnternehmen.addActionListener(filterAction);
        view.cbFilterStatus.addActionListener(filterAction);

        // --- TABELLE KLICKEN (Details öffnen) ---
        view.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    selectedModelRow = view.table.convertRowIndexToModel(row);
                    UebersichtModel model = aktuelleDaten.get(selectedModelRow);

                    view.txtFirma.setText(model.getFirma());
                    view.txtStelle.setText(model.getStelle());
                    view.cbOrt.getEditor().setItem(model.getOrt());
                    view.txtAdresse.setText(model.getAdresse());
                    view.cbPlattform.getEditor().setItem(model.getPlattform());
                    view.cbStatus.setSelectedItem(model.getStatus());
                    view.txtKontakt.setText(model.getAnsprechpartner());
                    view.txtEmail.setText(model.getEmail());
                    view.txtTelefon.setText(model.getTelefon());
                    view.txtFrist.setText(model.getFrist());
                    view.txtErinnerung.setText(model.getErinnerung());

                    view.detailScroll.setVisible(true);
                    view.revalidate();
                    view.repaint();
                }
            }
        });

        // --- DETAILS SCHLIEßEN ---
        view.btnCloseDetails.addActionListener(e -> {
            view.detailScroll.setVisible(false);
            view.revalidate();
            view.repaint();
        });

        // --- ÄNDERUNGEN SPEICHERN ---
        view.btnSpeichern.addActionListener(e -> {
            if (selectedModelRow >= 0) {
                UebersichtModel model = aktuelleDaten.get(selectedModelRow);
                model.setFirma(view.txtFirma.getText());
                model.setStelle(view.txtStelle.getText());
                model.setOrt(view.cbOrt.getEditor().getItem().toString());
                model.setAdresse(view.txtAdresse.getText());
                model.setPlattform(view.cbPlattform.getEditor().getItem().toString());
                model.setStatus(view.cbStatus.getSelectedItem().toString());
                model.setAnsprechpartner(view.txtKontakt.getText());
                model.setEmail(view.txtEmail.getText());
                model.setTelefon(view.txtTelefon.getText());
                model.setFrist(view.txtFrist.getText());
                model.setErinnerung(view.txtErinnerung.getText());

                service.updateBewerbungen(aktuelleDaten);
                JOptionPane.showMessageDialog(view, "Änderungen erfolgreich gespeichert!");

                ladeDaten(); // Refresh
                applyFilters();
            }
        });
    }

    private void applyFilters() {
        String firma = view.cbFilterUnternehmen.getSelectedItem().toString();
        String status = view.cbFilterStatus.getSelectedItem().toString();
        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (!firma.equals("Alle")) filters.add(RowFilter.regexFilter("^" + firma + "$", 1));

        if (!status.equals("Alle (inkl. Archiv)")) {
            if (status.equals("Aktive & Abgesagte")) filters.add(RowFilter.notFilter(RowFilter.regexFilter("Archiv", 7)));
            else if (status.equals("Aktiv (Laufend)")) filters.add(RowFilter.notFilter(RowFilter.regexFilter("Absage|Abgesagt|Archiv", 7)));
            else if (status.equals("Gesendet (Warten)")) filters.add(RowFilter.regexFilter("Warten", 7));
            else if (status.equals("Interview geplant")) filters.add(RowFilter.regexFilter("Interview", 7));
            else if (status.equals("Angebot erhalten")) filters.add(RowFilter.regexFilter("Angebot", 7));
            else if (status.equals("Abgesagt")) filters.add(RowFilter.regexFilter("Absage|Abgesagt", 7));
            else if (status.equals("Archiviert")) filters.add(RowFilter.regexFilter("Archiv", 7));
        }
        view.rowSorter.setRowFilter(RowFilter.andFilter(filters));
    }
}