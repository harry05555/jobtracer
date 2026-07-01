package de.gruppe_D.features.fragelebenslauf.infrastructure;

import de.gruppe_D.features.fragelebenslauf.FrageLebenslaufModel;
import de.gruppe_D.features.fragelebenslauf.interfaces.JDBCFrageLebenslaufRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FrageLebenslaufRepository implements JDBCFrageLebenslaufRepository {
    private final Map<String, FrageLebenslaufModel> users = new HashMap<>();

    private final DataSource dataSource;

    public FrageLebenslaufRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        users.put("admin", new FrageLebenslaufModel(1L, "admin", "1234"));
    }

    @Override
    public FrageLebenslaufModel findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public FrageLebenslaufModel findByUsernameInDB(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection con = dataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("DB Fehler", e);
        }
    }

    @Override
    public void save(FrageLebenslaufModel frageLebenslaufModel) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

        try (Connection con = dataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, frageLebenslaufModel.getUsername());
            stmt.setString(2, "hashed-password");

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DB Fehler", e);
        }
    }
}