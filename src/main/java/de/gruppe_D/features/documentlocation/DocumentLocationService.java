package de.gruppe_D.features.documentlocation;

import de.gruppe_D.features.documentlocation.interfaces.JdbcDocumentLocationRepository;

public class DocumentLocationService {

    private final JdbcDocumentLocationRepository documentLocationRepository;

    public DocumentLocationService(JdbcDocumentLocationRepository userRepository) {
        this.documentLocationRepository = userRepository;
    }

    public boolean login(String username, String password) {
        DocumentLocationModel documentLocationModel = documentLocationRepository.findByUsernameInDB(username);
        return documentLocationModel != null && documentLocationModel.checkPassword(password);
    }
}