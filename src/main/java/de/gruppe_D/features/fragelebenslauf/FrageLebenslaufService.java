package de.gruppe_D.features.fragelebenslauf;

import de.gruppe_D.features.fragelebenslauf.interfaces.JDBCFrageLebenslaufRepository;

public class FrageLebenslaufService {

    private final JDBCFrageLebenslaufRepository frageLebenslaufRepository;

    public FrageLebenslaufService(JDBCFrageLebenslaufRepository frageLebenslaufRepository) {
        this.frageLebenslaufRepository = frageLebenslaufRepository;
    }

    public boolean login(String username, String password) {
        FrageLebenslaufModel authModel = frageLebenslaufRepository.findByUsernameInDB(username);
        return authModel != null && authModel.checkPassword(password);
    }
}