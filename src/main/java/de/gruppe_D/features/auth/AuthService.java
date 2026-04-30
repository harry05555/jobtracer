package de.gruppe_D.features.auth;

import de.gruppe_D.features.auth.interfaces.JdbcAuthRepository;

public class AuthService {

    private final JdbcAuthRepository AuthRepository;

    public AuthService(JdbcAuthRepository userRepository) {
        this.AuthRepository = userRepository;
    }

    public boolean login(String username, String password) {
        AuthModel authModel = AuthRepository.findByUsernameInDB(username);
        return authModel != null && authModel.checkPassword(password);
    }
}