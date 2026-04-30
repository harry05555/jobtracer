package de.gruppe_D.features.auth;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public static AuthModel toUser(ResultSet rs) throws SQLException {
        return new AuthModel(rs.getLong("iduser"), rs.getString("username"), rs.getString("password"));
    }
}