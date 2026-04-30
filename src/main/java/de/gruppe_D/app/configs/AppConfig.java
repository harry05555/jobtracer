package de.gruppe_D.app.configs;

import de.gruppe_D.app.MainFrame;
import de.gruppe_D.app.Router;
import de.gruppe_D.features.auth.AuthService;
import de.gruppe_D.features.auth.infrastructure.AuthRepository;

import javax.sql.DataSource;

//„wir nutzen eine zentrale AppConfig als einfachen Dependency Injection Container, der gleichzeitig als Factory fungiert und Singleton-Lifecycle verwaltet.“
//injection
public class AppConfig {
    private MainFrame mainFrame;
    private AuthService authService;
    private AuthRepository authRepository;

    // Repository
    public AuthRepository authRepository() {
        if (authRepository == null) {
            authRepository = new AuthRepository(databaseConnection());
        }
        return authRepository;
    }

    // Service
    public AuthService authService() {
        if (authService == null) {
            authService = new AuthService(authRepository());
        }
        return authService;
    }

    // UI
    public Router router() {
        mainFrame().setVisible(true);
        return new Router(mainFrame(), authService());
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