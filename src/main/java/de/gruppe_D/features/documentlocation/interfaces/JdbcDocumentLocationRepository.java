package de.gruppe_D.features.documentlocation.interfaces;

import de.gruppe_D.features.documentlocation.DocumentLocationModel;

public interface JdbcDocumentLocationRepository {
    DocumentLocationModel loadPath();

    void savePath(DocumentLocationModel documentLocationModel);
}