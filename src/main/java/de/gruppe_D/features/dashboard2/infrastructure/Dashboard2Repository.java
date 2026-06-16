package de.gruppe_D.features.dashboard2.infrastructure;

import de.gruppe_D.features.dashboard2.Dashboard2Mapper;
import de.gruppe_D.features.dashboard2.Dashboard2Model;
import de.gruppe_D.features.dashboard2.interfaces.JdbcDashboard2Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Dashboard2Repository implements JdbcDashboard2Repository {
    private final Map<String, Dashboard2Model> users = new HashMap<>();

    private final DataSource dataSource;

    public Dashboard2Repository(DataSource dataSource) {
        this.dataSource = dataSource;
        users.put("admin", new Dashboard2Model(1L, "admin", "1234"));
    }

    @Override
    public Dashboard2Model findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public Dashboard2Model findByUsernameInDB(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection con = dataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Dashboard2Mapper.toUser(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("DB Fehler", e);
        }
    }

    @Override
    public void save(Dashboard2Model authModel) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

        try (Connection con = dataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, authModel.getUsername());
            stmt.setString(2, "hashed-password");

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DB Fehler", e);
        }
    }
}