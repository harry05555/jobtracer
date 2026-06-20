package de.gruppe_D.app.configs;

import de.gruppe_D.app.MainFrame;
import de.gruppe_D.app.Router;
import de.gruppe_D.features.auth.AuthService;
import de.gruppe_D.features.auth.infrastructure.AuthRepository;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenService;
import de.gruppe_D.features.dbkonfigurieren.infrastructure.DateiSpeichernDbKonfigurierenDaoFileImpl;
import de.gruppe_D.features.documentlocation.DocumentLocationService;
import de.gruppe_D.features.documentlocation.infrastructure.DocumentLocationRepository;
import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenService;
import de.gruppe_D.features.erinnerungeinstellen.infrastructure.ErinnerungEinstellenRepository;

import javax.sql.DataSource;

//„wir nutzen eine zentrale AppConfig als einfachen Dependency Injection Container, der gleichzeitig als Factory fungiert und Singleton-Lifecycle verwaltet.“
//injection
public class AppConfig {
    private MainFrame mainFrame;
    private AuthService authService;
    private DbKonfigurierenService dbKonfigurierenService;
    private ErinnerungEinstellenService erinnerungEinstellenService;
    private DocumentLocationService documentLocationService;
    private AuthRepository authRepository;
    private DateiSpeichernDbKonfigurierenDaoFileImpl dbKonfigurierenFileImpl;
    private ErinnerungEinstellenRepository erinnerungEinstellenRepository;
    private DocumentLocationRepository documentLocationRepository;

    // Repository
    public AuthRepository authRepository() {
        if (authRepository == null) {
            authRepository = new AuthRepository(databaseConnection());
        }
        return authRepository;
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

    public DocumentLocationRepository documentLocationRepository() {
        if (documentLocationRepository == null) {
            documentLocationRepository = new DocumentLocationRepository(databaseConnection());
        }
        return documentLocationRepository;
    }


    // Service
    public AuthService authService() {
        if (authService == null) {
            authService = new AuthService(authRepository());
        }
        return authService;
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

    public DocumentLocationService documentLocationService() {
        if (documentLocationService == null) {
            documentLocationService = new DocumentLocationService(documentLocationRepository());
        }
        return documentLocationService;
    }


    // UI
    public Router router() {
        mainFrame().setVisible(true);
        return new Router(mainFrame(), authService(), dbKonfigurierenService(), ErinnerungEinstellenServiceService(), documentLocationService());
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