package de.gruppe_D.features.auth;

import de.gruppe_D.app.Router;

import java.awt.event.ActionListener;

public class AuthController {
    private final AuthView view;
    private final AuthService authService;
    private final Router router;

    public AuthController(AuthView view, AuthService authService, Router router) {
        this.view = view;
        this.authService = authService;
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

            if (authService.login(username, password)) {
                router.showDashboard(); // 🔥 VIEW WECHSEL
            } else {
                System.out.println("Fehler!");
                view.usernameField.setText("Fehler");
            }
        };
    }
}