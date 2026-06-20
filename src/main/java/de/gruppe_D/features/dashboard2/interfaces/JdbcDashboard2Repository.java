package de.gruppe_D.features.dashboard2.interfaces;

import de.gruppe_D.features.dashboard2.Dashboard2Model;

public interface JdbcDashboard2Repository {
    Dashboard2Model findByUsername(String username);

    Dashboard2Model findByUsernameInDB(String username);

    void save(Dashboard2Model authModel);
}