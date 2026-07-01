package de.gruppe_D.features.documentlocation;

import de.gruppe_D.features.documentlocation.interfaces.JdbcDocumentLocationRepository;

public class DocumentLocationService {

    private final JdbcDocumentLocationRepository documentLocationRepository;

    public DocumentLocationService(JdbcDocumentLocationRepository userRepository) {
        this.documentLocationRepository = userRepository;
    }

    public void savePath(String path) {
        documentLocationRepository.savePath(new DocumentLocationModel(path));
    }
}