package de.gruppe_D.features.dbkonfigurieren;

import de.gruppe_D.features.dbkonfigurieren.interfaces.JdbcDbKonfigurierenRepository;

public class DbKonfigurierenService {

    private final JdbcDbKonfigurierenRepository jdbcDbKonfigurierenRepository;

    public DbKonfigurierenService(JdbcDbKonfigurierenRepository userRepository) {
        this.jdbcDbKonfigurierenRepository = userRepository;
    }

    public boolean login(String username, String password) {
        DbKonfigurierenModel dbKonfigurierenModel = jdbcDbKonfigurierenRepository.findByUsernameInDB(username);
        return dbKonfigurierenModel != null && dbKonfigurierenModel.checkPassword(password);
    }
}