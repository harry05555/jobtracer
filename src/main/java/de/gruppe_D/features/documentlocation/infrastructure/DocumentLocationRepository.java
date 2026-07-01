package de.gruppe_D.features.documentlocation.infrastructure;

import de.gruppe_D.features.documentlocation.DocumentLocationModel;
import de.gruppe_D.features.documentlocation.interfaces.JdbcDocumentLocationRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class DocumentLocationRepository implements JdbcDocumentLocationRepository {

    private final DataSource dataSource;
    private List<DocumentLocationModel> dummy = new ArrayList<>();

    public DocumentLocationRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DocumentLocationModel loadPath() {
        return null;
    }

    @Override
    public void savePath(DocumentLocationModel documentLocationModel) {
        dummy.add(documentLocationModel);
    }
}