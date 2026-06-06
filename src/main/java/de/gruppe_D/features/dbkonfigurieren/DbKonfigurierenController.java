package de.gruppe_D.features.dbkonfigurieren;

import de.gruppe_D.app.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static de.gruppe_D.app.utils.UIUtils.*;

public class DbKonfigurierenController {
    private final DbKonfigurierenView view;
    private final DbKonfigurierenService dbKonfigurierenService;
    private final Router router;

    public DbKonfigurierenController(DbKonfigurierenView view, DbKonfigurierenService dbKonfigurierenService, Router router) {
        this.view = view;
        this.dbKonfigurierenService = dbKonfigurierenService;
        this.router = router;
        init();
    }

    private void init() {
        view.btnConnect.addActionListener(DBAnmeldeinformationenSpeichern());
        view.verbindungTestButton.addActionListener(verbindungsTest());
    }

    private ActionListener DBAnmeldeinformationenSpeichern() {
        return e -> {
            String username = view.tfUser.getText();
            String port = view.tfPort.getText();
            String password = new String(view.pfPass.getPassword());
            String hostnameField = view.tfHost.getText();


            boolean erfolgreich = dbKonfigurierenService.verbindungTesten(username, password, port, hostnameField);

            if (erfolgreich) {
                view.tfUser.setBorder(BorderFactory.createLineBorder(COLOR_GREEN, 2));
                view.tfPort.setBorder(BorderFactory.createLineBorder(COLOR_GREEN, 2));
                view.pfPass.setBorder(BorderFactory.createLineBorder(COLOR_GREEN, 2));
                view.tfHost.setBorder(BorderFactory.createLineBorder(COLOR_GREEN, 2));
                dbKonfigurierenService.DBInfosSpeichern(username, password, port, hostnameField);
//                TODO Hier kommt die Weiterleitung zur Aufforderung neu das Programm zu starten damit die konfig geladen werden
            } else {
                view.tfUser.setBorder(BorderFactory.createLineBorder(COLOR_RED, 2));
                view.tfPort.setBorder(BorderFactory.createLineBorder(COLOR_RED, 2));
                view.pfPass.setBorder(BorderFactory.createLineBorder(COLOR_RED, 2));
                view.tfHost.setBorder(BorderFactory.createLineBorder(COLOR_RED, 2));
            }
        };
    }

    private ActionListener verbindungsTest() {
        return e -> {
            String username = view.tfUser.getText();
            String port = view.tfPort.getText();
            String password = new String(view.pfPass.getPassword());
            String hostname = view.tfHost.getText();
            boolean erfolgreich = dbKonfigurierenService.verbindungTesten(username, password, port, hostname);

            if (erfolgreich) {
                view.tfUser.setBorder(BorderFactory.createLineBorder(COLOR_GREEN, 2));
                view.tfPort.setBorder(BorderFactory.createLineBorder(COLOR_GREEN, 2));
                view.pfPass.setBorder(BorderFactory.createLineBorder(COLOR_GREEN, 2));
                view.tfHost.setBorder(BorderFactory.createLineBorder(COLOR_GREEN, 2));
            } else {
                view.tfUser.setBorder(BorderFactory.createLineBorder(COLOR_RED, 2));
                view.tfPort.setBorder(BorderFactory.createLineBorder(COLOR_RED, 2));
                view.pfPass.setBorder(BorderFactory.createLineBorder(COLOR_RED, 2));
                view.tfHost.setBorder(BorderFactory.createLineBorder(COLOR_RED, 2));
            }
        };
    }
}