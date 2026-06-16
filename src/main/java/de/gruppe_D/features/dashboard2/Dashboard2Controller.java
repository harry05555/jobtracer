package de.gruppe_D.features.dashboard2;

import de.gruppe_D.app.Router;

import java.awt.event.ActionListener;

public class Dashboard2Controller {
    private final Dashboard2View view;
    private final Dashboard2Service Dashboard2Service;
    private final Router router;

    public Dashboard2Controller(Dashboard2View view, Dashboard2Service dashboard2Service, Router router) {
        this.view = view;
        this.Dashboard2Service = dashboard2Service;
        this.router = router;
        init();
    }

    private void init() {
        view.loginButton.addActionListener(login());
    }

    private ActionListener login() {
        return e -> {
            String username = view.usernameField.getText();
            String password = new String(view.passwordField.getPassword());

            if (Dashboard2Service.login(username, password)) {
                router.showDashboard(); // 🔥 VIEW WECHSEL
            } else {
                System.out.println("Fehler!");
                view.usernameField.setText("Fehler");
            }
        };
    }
}