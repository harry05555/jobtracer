package de.gruppe_D.features.uebersicht;

import de.gruppe_D.app.utils.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UebersichtView extends JPanel {

    // --- SIDEBAR BUTTONS ---
    public JButton btnNavDashboard, btnNavUebersicht, btnNavDokumente, btnNavEinstellungen, btnNavBewerbungAnlegen, btnLogout;

    // --- ÜBERSICHT KOMPONENTEN ---
    public JTable table;
    public DefaultTableModel tableModel;
    public TableRowSorter<DefaultTableModel> rowSorter;
    public JComboBox<String> cbFilterUnternehmen;
    public JComboBox<String> cbFilterStatus;

    // --- DETAIL PANEL KOMPONENTEN ---
    public JPanel detailPanel;
    public JScrollPane detailScroll;
    public JButton btnCloseDetails = new JButton("✖");
    public JTextField txtFirma = UIUtils.createTextField("");
    public JTextField txtStelle = UIUtils.createTextField("");
    public JComboBox<String> cbOrt = UIUtils.createEditableDropdown(new String[]{"Berlin", "München", "Hamburg", "Köln", "Remote"});
    public JTextField txtAdresse = UIUtils.createTextField("");
    public JComboBox<String> cbPlattform = UIUtils.createEditableDropdown(new String[]{"LinkedIn", "Stepstone", "Direkt / Website", "Headhunter"});
    public JComboBox<String> cbStatus = UIUtils.createDropdown(new String[]{"Warten", "Interview", "Abgesagt", "Angebot erhalten", "Erinnerung", "Archiviert"});
    public JTextField txtKontakt = UIUtils.createTextField("");
    public JTextField txtEmail = UIUtils.createTextField("");
    public JTextField txtTelefon = UIUtils.createTextField("");
    public JTextField txtFrist = UIUtils.createTextField("");
    public JTextField txtErinnerung = UIUtils.createTextField("");
    public JButton btnSpeichern = new JButton("💾 Änderungen speichern");

    public UebersichtView() {
        setLayout(new BorderLayout());
        setBackground(UIUtils.BG_DARK);

        // =====================================================================
        // 1. HARDCODIERTE SIDEBAR (Wie im Dashboard2)
        // =====================================================================
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(UIUtils.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(250, 900));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel navLabel = new JLabel("Navigation");
        navLabel.setForeground(UIUtils.TEXT_WHITE);
        navLabel.setFont(new Font("Arial", Font.BOLD, 18));
        sidebar.add(navLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        btnNavDashboard = createNavButton("📊 Dashboard");
        btnNavUebersicht = createNavButton("📋 Übersicht");
        btnNavDokumente = createNavButton("📁 Meine Unterlagen");
        btnNavEinstellungen = createNavButton("⚙ Einstellungen");

        btnNavBewerbungAnlegen = new JButton("- Neue Bewerbung anlegen");
        btnNavBewerbungAnlegen.setForeground(UIUtils.TEXT_WHITE);
        btnNavBewerbungAnlegen.setBackground(UIUtils.ACCENT_COLOR);
        btnNavBewerbungAnlegen.setBorderPainted(false);
        btnNavBewerbungAnlegen.setFocusPainted(false);
        btnNavBewerbungAnlegen.setHorizontalAlignment(SwingConstants.LEFT);
        btnNavBewerbungAnlegen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnNavBewerbungAnlegen.setFont(new Font("Arial", Font.BOLD, 14));
        btnNavBewerbungAnlegen.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogout = createNavButton("🚪 Logout / Trennen");

        sidebar.add(btnNavDashboard); sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnNavUebersicht); sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnNavDokumente); sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnNavEinstellungen); sidebar.add(Box.createRigidArea(new Dimension(0, 30)));
        sidebar.add(btnNavBewerbungAnlegen); sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // =====================================================================
        // 2. ÜBERSICHT CONTENT (Mitte & Rechts)
        // =====================================================================
        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBackground(UIUtils.BG_DARK);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // --- FILTER ---
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        filterPanel.setBackground(UIUtils.BG_DARK);
        JLabel lblFilter = new JLabel("Filter: ");
        lblFilter.setForeground(UIUtils.TEXT_WHITE);

        cbFilterUnternehmen = UIUtils.createDropdown(new String[]{"Alle"});
        cbFilterStatus = UIUtils.createDropdown(new String[]{
                "Aktive & Abgesagte", "Alle (inkl. Archiv)", "Aktiv (Laufend)", "Gesendet (Warten)",
                "Interview geplant", "Angebot erhalten", "Abgesagt", "Archiviert"
        });
        filterPanel.add(lblFilter);
        filterPanel.add(cbFilterUnternehmen);
        filterPanel.add(cbFilterStatus);
        contentPanel.add(filterPanel, BorderLayout.NORTH);

        // --- TABELLE ---
        String[] columns = {"Aktion", "Firma", "Stelle", "Ort", "Plattform", "Versendet am", "Frist / Deadline", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);

        table.setBackground(UIUtils.BG_DARK);
        table.setForeground(UIUtils.TEXT_WHITE);
        table.setGridColor(UIUtils.SIDEBAR_BG);
        table.setRowHeight(35);
        table.getTableHeader().setBackground(UIUtils.TABLE_HEADER_BG);
        table.getTableHeader().setForeground(UIUtils.TEXT_WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        CustomRowRenderer customRenderer = new CustomRowRenderer();
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.getViewport().setBackground(UIUtils.BG_DARK);
        tableScroll.setBorder(BorderFactory.createLineBorder(UIUtils.SIDEBAR_BG));
        contentPanel.add(tableScroll, BorderLayout.CENTER);

        // --- DETAIL PANEL (Rechts, Scrollbar) ---
        detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBackground(UIUtils.SIDEBAR_BG);
        detailPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        detailScroll = new JScrollPane(detailPanel);
        detailScroll.setPreferredSize(new Dimension(360, 0));
        detailScroll.setBorder(null);
        detailScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        detailScroll.setVisible(false);

        JPanel detailHeader = new JPanel(new BorderLayout());
        detailHeader.setBackground(UIUtils.SIDEBAR_BG);
        detailHeader.setMaximumSize(new Dimension(320, 40));

        JLabel lblDetailTitle = new JLabel("📝 Details bearbeiten");
        lblDetailTitle.setForeground(UIUtils.TEXT_WHITE);
        lblDetailTitle.setFont(new Font("Arial", Font.BOLD, 18));
        UIUtils.styleActionButton(btnCloseDetails, UIUtils.SIDEBAR_BG, UIUtils.TEXT_GREY);
        btnCloseDetails.setFont(new Font("Arial", Font.BOLD, 16));

        detailHeader.add(lblDetailTitle, BorderLayout.WEST);
        detailHeader.add(btnCloseDetails, BorderLayout.EAST);
        detailPanel.add(detailHeader);
        detailPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        detailPanel.add(createEditRow("Firma:", txtFirma));
        detailPanel.add(createEditRow("Stelle:", txtStelle));
        detailPanel.add(createEditRow("Ort:", cbOrt));
        detailPanel.add(createEditRow("Adresse:", txtAdresse));
        detailPanel.add(createEditRow("Plattform:", cbPlattform));
        detailPanel.add(createEditRow("Status:", cbStatus));
        detailPanel.add(createEditRow("Kontakt:", txtKontakt));
        detailPanel.add(createEditRow("E-Mail:", txtEmail));
        detailPanel.add(createEditRow("Telefon:", txtTelefon));
        detailPanel.add(createEditRow("Frist:", txtFrist));
        detailPanel.add(createEditRow("Erinn.:", txtErinnerung));
        detailPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        UIUtils.styleActionButton(btnSpeichern, UIUtils.COLOR_GREEN, Color.WHITE);
        btnSpeichern.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.add(btnSpeichern);

        contentPanel.add(detailScroll, BorderLayout.EAST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(UIUtils.TEXT_GREY);
        btn.setBackground(UIUtils.SIDEBAR_BG);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setFont(new Font("Arial", Font.PLAIN, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel createEditRow(String labelText, JComponent comp) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBackground(UIUtils.SIDEBAR_BG);
        p.setMaximumSize(new Dimension(320, 35));
        JLabel lbl = new JLabel(labelText);
        lbl.setForeground(UIUtils.TEXT_GREY);
        lbl.setPreferredSize(new Dimension(80, 25));
        p.add(lbl, BorderLayout.WEST);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    // --- FARB RENDERER ---
    class CustomRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            int modelRow = table.convertRowIndexToModel(row);
            String status = (String) table.getModel().getValueAt(modelRow, 7);
            String fristStr = (String) table.getModel().getValueAt(modelRow, 6);

            Color bgColor = UIUtils.BG_DARK;
            boolean isAbgelaufen = false;
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate fristDate = LocalDate.parse(fristStr, dtf);
                if (fristDate.isBefore(LocalDate.now()) && !status.contains("Absage") && !status.contains("Archiv") && !status.contains("Angebot") && !status.contains("Interview")) {
                    isAbgelaufen = true;
                }
            } catch (Exception e) {}

            if (isAbgelaufen) bgColor = new Color(245, 124, 0); // Orange
            else if (status.contains("Warten")) bgColor = new Color(46, 125, 50);
            else if (status.contains("Interview")) bgColor = new Color(25, 118, 210);
            else if (status.contains("Absage") || status.contains("Abgesagt")) bgColor = UIUtils.COLOR_RED;
            else if (status.contains("Erinnerung")) bgColor = UIUtils.COLOR_YELLOW;
            else if (status.contains("Angebot")) bgColor = new Color(3, 169, 244);

            c.setBackground(bgColor);
            c.setForeground(UIUtils.TEXT_WHITE);
            setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
            return c;
        }
    }
}