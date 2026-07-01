package de.gruppe_D.features.fragelebenslauf.infrastructure;

import de.gruppe_D.features.fragelebenslauf.FrageLebenslaufModel;
import de.gruppe_D.features.fragelebenslauf.interfaces.JDBCFrageLebenslaufRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class FrageLebenslaufRepository implements JDBCFrageLebenslaufRepository {

    private final DataSource dataSource;
    private final List<FrageLebenslaufModel> dummy = new ArrayList<>();

    public FrageLebenslaufRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(FrageLebenslaufModel frageLebenslaufModel) {
        dummy.add(frageLebenslaufModel);
    }

    @Override
    public FrageLebenslaufModel getFrageLebenslauf() {
        return null;
    }
}