package de.gruppe_D.features.dbkonfigurieren;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbKonfigurierenMapper {
    public static DbKonfigurierenModel toUser(ResultSet rs) throws SQLException {
        return new DbKonfigurierenModel(rs.getLong("iduser"), rs.getString("username"), rs.getString("password"));
    }
}