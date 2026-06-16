package de.gruppe_D.app;

import de.gruppe_D.features.auth.AuthController;
import de.gruppe_D.features.auth.AuthService;
import de.gruppe_D.features.auth.AuthView;
import de.gruppe_D.features.dashboard.DashboardController;
import de.gruppe_D.features.dashboard.DashboardView;
import de.gruppe_D.features.dashboard2.Dashboard2Controller;
import de.gruppe_D.features.dashboard2.Dashboard2Service;
import de.gruppe_D.features.dashboard2.Dashboard2View;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenController;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenService;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenView;

public class Router {
    private final MainFrame frame;
    private final AuthService authService;
    private final Dashboard2Service Dashboard2Service;
    private final DbKonfigurierenService dbKonfigurierenService;

    public Router(MainFrame frame, AuthService authService,DbKonfigurierenService dbKonfigurierenService, Dashboard2Service dashboard2Service) {
        this.frame = frame;
        this.authService = authService;
        Dashboard2Service = dashboard2Service;
        this.dbKonfigurierenService = dbKonfigurierenService;
    }

    public void showAuth() {
        AuthView view = new AuthView();
        new AuthController(view, authService, this);
        frame.setView(view);
    }

    public void showDashboard2() {
        Dashboard2View view = new Dashboard2View();
        new Dashboard2Controller(view, Dashboard2Service, this);
        frame.setView(view);
    }

    public void showDashboard() {
        DashboardView view = new DashboardView();
        new DashboardController(view, this);
        frame.setView(view);
    }

    public void showDbKonfigurieren() {
        DbKonfigurierenView view = new DbKonfigurierenView();
        new DbKonfigurierenController(view, dbKonfigurierenService, this);
        frame.setView(view);
    }
}