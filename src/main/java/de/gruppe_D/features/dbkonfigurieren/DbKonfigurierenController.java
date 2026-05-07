package de.gruppe_D.features.dbkonfigurieren;

import de.gruppe_D.app.Router;

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
        view.loginButton.addActionListener(login());
    }

    private ActionListener login() {
        return e -> {
            String username = view.usernameField.getText();
            String password = new String(view.passwordField.getPassword());

            if (dbKonfigurierenService.login(username, password)) {
                router.showDashboard(); // 🔥 VIEW WECHSEL
            } else {
                System.out.println("Fehler!");
                view.usernameField.setText("Fehler");
            }
        };
    }
}