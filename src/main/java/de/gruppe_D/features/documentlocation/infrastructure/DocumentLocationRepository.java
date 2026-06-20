package de.gruppe_D.features.documentlocation.infrastructure;

import de.gruppe_D.features.documentlocation.DocumentLocationModel;
import de.gruppe_D.features.documentlocation.UserMapper;
import de.gruppe_D.features.documentlocation.interfaces.JdbcDocumentLocationRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DocumentLocationRepository implements JdbcDocumentLocationRepository {
    private final Map<String, DocumentLocationModel> users = new HashMap<>();

    private final DataSource dataSource;

    public DocumentLocationRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        users.put("admin", new DocumentLocationModel(1L, "admin", "1234"));
    }

    @Override
    public DocumentLocationModel findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public DocumentLocationModel findByUsernameInDB(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection con = dataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return UserMapper.toUser(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("DB Fehler", e);
        }
    }

    @Override
    public void save(DocumentLocationModel authModel) {
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