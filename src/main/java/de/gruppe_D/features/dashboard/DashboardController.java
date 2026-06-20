package de.gruppe_D.features.dashboard;

import de.gruppe_D.app.Router;

public class DashboardController {
    private final DashboardView view;
    private final Router router;

    public DashboardController(DashboardView view, Router router) {
        this.view = view;
        this.router = router;

        init();
    }

    private void init() {
       // view.logoutButton.addActionListener(e -> router.showAuth());
    }
}