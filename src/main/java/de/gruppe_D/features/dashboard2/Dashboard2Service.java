package de.gruppe_D.features.dashboard2;

import de.gruppe_D.features.dashboard2.interfaces.JdbcDashboard2Repository;

public class Dashboard2Service {

    private final JdbcDashboard2Repository Dashboard2Repository;

    public Dashboard2Service(JdbcDashboard2Repository userRepository) {
        this.Dashboard2Repository = userRepository;
    }

    public boolean login(String username, String password) {
        Dashboard2Model dashboard2Model = Dashboard2Repository.findByUsernameInDB(username);
        return dashboard2Model != null && dashboard2Model.checkPassword(password);
    }
}