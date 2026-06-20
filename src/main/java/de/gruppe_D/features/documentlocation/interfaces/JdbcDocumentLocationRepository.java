package de.gruppe_D.features.documentlocation.interfaces;

import de.gruppe_D.features.documentlocation.DocumentLocationModel;

public interface JdbcDocumentLocationRepository {
    DocumentLocationModel findByUsername(String username);

    DocumentLocationModel findByUsernameInDB(String username);

    void save(DocumentLocationModel authModel);
}