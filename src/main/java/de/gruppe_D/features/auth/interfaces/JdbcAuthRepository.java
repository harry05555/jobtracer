package de.gruppe_D.features.auth.interfaces;

import de.gruppe_D.features.auth.AuthModel;

public interface JdbcAuthRepository {
    AuthModel findByUsername(String username);

    AuthModel findByUsernameInDB(String username);

    void save(AuthModel authModel);
}