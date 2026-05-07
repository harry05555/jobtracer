package de.gruppe_D.features.dbkonfigurieren.infrastructure;

import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenMapper;
import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenModel;
import de.gruppe_D.features.dbkonfigurieren.interfaces.JdbcDbKonfigurierenRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbKonfigurierenRepository implements JdbcDbKonfigurierenRepository {
    private final Map<String, DbKonfigurierenModel> users = new HashMap<>();

    private final DataSource dataSource;

    public DbKonfigurierenRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        users.put("admin", new DbKonfigurierenModel(1L, "admin", "1234"));
    }

    @Override
    public DbKonfigurierenModel findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public DbKonfigurierenModel findByUsernameInDB(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection con = dataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return DbKonfigurierenMapper.toUser(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("DB Fehler", e);
        }
    }

    @Override
    public void save(DbKonfigurierenModel dbKonfigurierenModel) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

        try (Connection con = dataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, dbKonfigurierenModel.getUsername());
            stmt.setString(2, "hashed-password");

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DB Fehler", e);
        }
    }
}