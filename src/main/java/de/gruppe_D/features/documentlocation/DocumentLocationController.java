package de.gruppe_D.features.documentlocation;

import de.gruppe_D.app.Router;

import java.awt.event.ActionListener;

public class DocumentLocationController {
    private final DocumentLocationView view;
    private final DocumentLocationService documentLocationService;
    private final Router router;

    public DocumentLocationController(DocumentLocationView view, DocumentLocationService documentLocationService, Router router) {
        this.view = view;
        this.documentLocationService = documentLocationService;
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

            if (documentLocationService.login(username, password)) {
                router.showDashboard(); // 🔥 VIEW WECHSEL
            } else {
                System.out.println("Fehler!");
                view.usernameField.setText("Fehler");
            }
        };
    }
}