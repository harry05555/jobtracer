package de.gruppe_D.features.dbkonfigurieren;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.gruppe_D.features.dbkonfigurieren.interfaces.LokalDbKonfigurierenRepository;

import java.sql.Connection;

public class DbKonfigurierenService {

    private final LokalDbKonfigurierenRepository dbKonfigurierenRepository;

    public DbKonfigurierenService(LokalDbKonfigurierenRepository dbKonfigurierenRepository) {
        this.dbKonfigurierenRepository = dbKonfigurierenRepository;
    }

    public void DBInfosSpeichern(String username, String password, String port, String hostname) {
        DbKonfigurierenModel konfigurieren = new DbKonfigurierenModel(username, password, port, hostname);
        dbKonfigurierenRepository.saveDatei(konfigurieren);
    }

    public boolean verbindungTesten(String username, String password, String port, String hostname) {

        String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/app";

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        try (HikariDataSource ds = new HikariDataSource(config);
             Connection connection = ds.getConnection()) {

            return connection.isValid(2);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}