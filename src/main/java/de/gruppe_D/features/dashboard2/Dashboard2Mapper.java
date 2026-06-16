package de.gruppe_D.features.dashboard2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dashboard2Mapper {
    public static Dashboard2Model toUser(ResultSet rs) throws SQLException {
        return new Dashboard2Model(rs.getLong("iduser"), rs.getString("username"), rs.getString("password"));
    }
}