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
import de.gruppe_D.features.documentlocation.DocumentLocationController;
import de.gruppe_D.features.documentlocation.DocumentLocationService;
import de.gruppe_D.features.documentlocation.DocumentLocationView;
import de.gruppe_D.features.uebersicht.UebersichtController;
import de.gruppe_D.features.uebersicht.UebersichtService;
import de.gruppe_D.features.uebersicht.UebersichtView;
import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenController;
import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenService;
import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenView;

public class Router {
    private final MainFrame frame;
    private final AuthService authService;
    private final Dashboard2Service Dashboard2Service;
    private final DbKonfigurierenService dbKonfigurierenService;
    private final UebersichtService uebersichtService;
    private final ErinnerungEinstellenService erinnerungEinstellenService;
    private final DocumentLocationService documentLocationService;


    public Router(MainFrame frame, AuthService authService, DbKonfigurierenService dbKonfigurierenService, ErinnerungEinstellenService erinnerungEinstellenService, DocumentLocationService documentLocationService) {
        this.frame = frame;
        this.authService = authService;
        Dashboard2Service = dashboard2Service;
        this.dbKonfigurierenService = dbKonfigurierenService;
        this.erinnerungEinstellenService = erinnerungEinstellenService;
        this.documentLocationService = documentLocationService;
        this.uebersichtService = uebersichtService;
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

    public void showUebersicht() {
        UebersichtView view = new UebersichtView();
        // Jetzt kennt der Router uebersichtService und kann ihn übergeben!
        new UebersichtController(view, uebersichtService, this);
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

    public void showErinnerungEinstellen() {
        ErinnerungEinstellenView view = new ErinnerungEinstellenView();
        new ErinnerungEinstellenController(view, erinnerungEinstellenService, this);
        frame.setView(view);
    }

    public void showDocumentLocation() {
        DocumentLocationView view = new DocumentLocationView();
        new DocumentLocationController(view, documentLocationService, this);
        frame.setView(view);
    }
}