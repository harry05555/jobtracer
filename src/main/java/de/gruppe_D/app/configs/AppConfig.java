package de.gruppe_D.app.configs;

import de.gruppe_D.app.MainFrame;
import de.gruppe_D.app.Router;
import de.gruppe_D.features.auth.AuthService;
import de.gruppe_D.features.auth.infrastructure.AuthRepository;
import de.gruppe_D.features.dashboard2.Dashboard2Service;
import de.gruppe_D.features.dashboard2.infrastructure.Dashboard2Repository;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenService;
import de.gruppe_D.features.dbkonfigurieren.infrastructure.DateiSpeichernDbKonfigurierenDaoFileImpl;
import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenService;
import de.gruppe_D.features.erinnerungeinstellen.infrastructure.ErinnerungEinstellenRepository;
import de.gruppe_D.features.uebersicht.UebersichtService;
import de.gruppe_D.features.uebersicht.infrastructure.UebersichtRepository;

import javax.sql.DataSource;

//„wir nutzen eine zentrale AppConfig als einfachen Dependency Injection Container, der gleichzeitig als Factory fungiert und Singleton-Lifecycle verwaltet.“
//injection
public class AppConfig {
    private MainFrame mainFrame;
    private AuthService authService;
    private DbKonfigurierenService dbKonfigurierenService;
    private ErinnerungEinstellenService erinnerungEinstellenService;
    private AuthRepository authRepository;
    private Dashboard2Service Dashboard2Service;
    private Dashboard2Repository Dashboard2Repository;
    private DateiSpeichernDbKonfigurierenDaoFileImpl dbKonfigurierenFileImpl;
    private ErinnerungEinstellenRepository erinnerungEinstellenRepository;
    private UebersichtRepository uebersichtRepository;
    private UebersichtService uebersichtService;

    // Repository
    public AuthRepository authRepository() {
        if (authRepository == null) {
            authRepository = new AuthRepository(databaseConnection());
        }
        return authRepository;
    }

    public Dashboard2Repository Dashboard2Repository() {
        if (Dashboard2Repository == null) {
            Dashboard2Repository = new Dashboard2Repository(databaseConnection());
        }
        return Dashboard2Repository;
    }

    public DateiSpeichernDbKonfigurierenDaoFileImpl dbKonfigurierenDao() {
        if (dbKonfigurierenFileImpl == null) {
            dbKonfigurierenFileImpl = new DateiSpeichernDbKonfigurierenDaoFileImpl();
        }
        return dbKonfigurierenFileImpl;
    }


    public ErinnerungEinstellenRepository erinnerungEinstellenRepository() {
        if (erinnerungEinstellenRepository == null) {
            erinnerungEinstellenRepository = new ErinnerungEinstellenRepository(databaseConnection());
        }
        return erinnerungEinstellenRepository;
    }

    public UebersichtRepository uebersichtRepository() {
        if (uebersichtRepository == null) {
            uebersichtRepository = new UebersichtRepository(databaseConnection());
        }
        return uebersichtRepository;
    }

    // Service
    public AuthService authService() {
        if (authService == null) {
            authService = new AuthService(authRepository());
        }
        return authService;
    }

    public Dashboard2Service Dashboard2Service() {
        if (Dashboard2Service == null) {
            Dashboard2Service = new Dashboard2Service(Dashboard2Repository());
        }
        return Dashboard2Service;
    }

    public DbKonfigurierenService dbKonfigurierenService() {
        if (dbKonfigurierenService == null) {
            dbKonfigurierenService = new DbKonfigurierenService(dbKonfigurierenDao());
        }
        return dbKonfigurierenService;
    }

    public ErinnerungEinstellenService ErinnerungEinstellenServiceService() {
        if (erinnerungEinstellenService == null) {
            erinnerungEinstellenService = new ErinnerungEinstellenService(erinnerungEinstellenRepository());
        }
        return erinnerungEinstellenService;
    }

    public UebersichtService uebersichtService() {
        if (uebersichtService == null) {
            uebersichtService = new UebersichtService(uebersichtRepository());
        }
        return uebersichtService;
    }

    // UI
    public Router router() {
        mainFrame().setVisible(true);
        return new Router(mainFrame(), authService(), dbKonfigurierenService(), ErinnerungEinstellenServiceService(), Dashboard2Service(), uebersichtService());
    }

    public MainFrame mainFrame() {
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

    // Infrastruktur
    private DataSource databaseConnection() {
        return DatabaseConfig.getDataSource();
    }
}