package de.gruppe_D.app.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {
    private static DataSource dataSource;

    private static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/app");
        config.setUsername("root");
        config.setPassword("SebiSebi");
        return new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = createDataSource();
        }
        return dataSource;
    }
}