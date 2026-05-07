package de.gruppe_D.app.configs;

import de.gruppe_D.app.MainFrame;
import de.gruppe_D.app.Router;
import de.gruppe_D.features.auth.AuthService;
import de.gruppe_D.features.auth.infrastructure.AuthRepository;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenService;
import de.gruppe_D.features.dbkonfigurieren.infrastructure.DateiSpeichernDbKonfigurierenRepository;

import javax.sql.DataSource;

//„wir nutzen eine zentrale AppConfig als einfachen Dependency Injection Container, der gleichzeitig als Factory fungiert und Singleton-Lifecycle verwaltet.“
//injection
public class AppConfig {
    private MainFrame mainFrame;
    private AuthService authService;
    private DbKonfigurierenService dbKonfigurierenService;
    private AuthRepository authRepository;
    private DateiSpeichernDbKonfigurierenRepository dbKonfigurierenRepository;

    // Repository
    public AuthRepository authRepository() {
        if (authRepository == null) {
            authRepository = new AuthRepository(databaseConnection());
        }
        return authRepository;
    }

    public DateiSpeichernDbKonfigurierenRepository dbKonfigurierenRepository() {
        if (dbKonfigurierenRepository == null) {
            dbKonfigurierenRepository = new DateiSpeichernDbKonfigurierenRepository();
        }
        return dbKonfigurierenRepository;
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
            dbKonfigurierenService = new DbKonfigurierenService(dbKonfigurierenRepository());
        }
        return dbKonfigurierenService;
    }

    // UI
    public Router router() {
        mainFrame().setVisible(true);
        return new Router(mainFrame(), authService(), dbKonfigurierenService());
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