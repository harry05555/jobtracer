package de.gruppe_D.app;

import de.gruppe_D.features.auth.AuthController;
import de.gruppe_D.features.auth.AuthService;
import de.gruppe_D.features.auth.AuthView;
import de.gruppe_D.features.dashboard.DashboardController;
import de.gruppe_D.features.dashboard.DashboardView;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenController;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenService;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenView;
import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenController;
import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenService;
import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenView;

public class Router {
    private final MainFrame frame;
    private final AuthService authService;
    private final DbKonfigurierenService dbKonfigurierenService;
    private final ErinnerungEinstellenService erinnerungEinstellenService;

    public Router(MainFrame frame, AuthService authService, DbKonfigurierenService dbKonfigurierenService, ErinnerungEinstellenService erinnerungEinstellenService) {
        this.frame = frame;
        this.authService = authService;
        this.dbKonfigurierenService = dbKonfigurierenService;
        this.erinnerungEinstellenService = erinnerungEinstellenService;
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

    public void showDbKonfigurieren() {
        DbKonfigurierenView view = new DbKonfigurierenView();
        new DbKonfigurierenController(view, dbKonfigurierenService, this);
        frame.setView(view);

    }   public void showErinnerungEinstellen() {
        ErinnerungEinstellenView view = new ErinnerungEinstellenView();
        new ErinnerungEinstellenController(view, erinnerungEinstellenService, this);
        frame.setView(view);
    }
}