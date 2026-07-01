package de.gruppe_D.features.fragelebenslauf;

import de.gruppe_D.app.Router;

import java.awt.event.ActionListener;

public class FrageLebenslaufController {
    private final FrageLebenslaufView view;
    private final FrageLebenslaufService frageLebenslaufService;
    private final Router router;

    public FrageLebenslaufController(FrageLebenslaufView view, FrageLebenslaufService frageLebenslaufService, Router router) {
        this.view = view;
        this.frageLebenslaufService = frageLebenslaufService;
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

            if (frageLebenslaufService.login(username, password)) {
                router.showDashboard(); // 🔥 VIEW WECHSEL
            } else {
                System.out.println("Fehler!");
                view.usernameField.setText("Fehler");
            }
        };
    }
}