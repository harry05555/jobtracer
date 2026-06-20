package de.gruppe_D.features.documentlocation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public static DocumentLocationModel toUser(ResultSet rs) throws SQLException {
        return new DocumentLocationModel(rs.getLong("iduser"), rs.getString("username"), rs.getString("password"));
    }
}