package de.gruppe_D.features.fragelebenslauf.interfaces;

import de.gruppe_D.features.fragelebenslauf.FrageLebenslaufModel;

public interface JDBCFrageLebenslaufRepository {

    void save(FrageLebenslaufModel frageLebenslaufModel);

    FrageLebenslaufModel getFrageLebenslauf();
}