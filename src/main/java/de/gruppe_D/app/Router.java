package de.gruppe_D.app;

import de.gruppe_D.features.auth.AuthController;
import de.gruppe_D.features.auth.AuthService;
import de.gruppe_D.features.auth.AuthView;
import de.gruppe_D.features.dashboard.DashboardController;
import de.gruppe_D.features.dashboard.DashboardView;

public class Router {
    private final MainFrame frame;
    private final AuthService authService;

    public Router(MainFrame frame, AuthService authService) {
        this.frame = frame;
        this.authService = authService;
    }

    public void showAuth() {
        AuthView view = new AuthView();
        new AuthController(view, authService, this);
        frame.setView(view);
    }

    public void showDashboard() {
        DashboardView view = new DashboardView();
        new DashboardController(view, this);
        frame.setView(view);
    }
}