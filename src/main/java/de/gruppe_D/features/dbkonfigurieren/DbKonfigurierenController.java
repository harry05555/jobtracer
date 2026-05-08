package de.gruppe_D.features.dbkonfigurieren;

import de.gruppe_D.app.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        view.loginButton.addActionListener(DBAnmeldeinformationenSpeichern());
        view.verbindungTestButton.addActionListener(verbindungsTest());
    }

    private ActionListener DBAnmeldeinformationenSpeichern() {
        return e -> {
            String username = view.usernameField.getText();
            String port = view.portField.getText();
            String password = new String(view.passwordField.getPassword());
            String hostnameField = view.hostnameField.getText();
            dbKonfigurierenService.DBInfosSpeichern(username, password, port, hostnameField);
            System.out.println("fertig");
        };
    }

    private ActionListener verbindungsTest() {
        return e -> {
            String username = view.usernameField.getText();
            String port = view.portField.getText();
            String password = new String(view.passwordField.getPassword());
            String hostname = view.hostnameField.getText();

            boolean erfolgreich = dbKonfigurierenService.verbindungTesten(username, password, port, hostname);

            if (erfolgreich) {
                view.usernameField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                view.portField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                view.passwordField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                view.hostnameField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            } else {
                view.usernameField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                view.portField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                view.passwordField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                view.hostnameField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
            }
        };
    }
}