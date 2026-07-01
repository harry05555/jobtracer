package de.gruppe_D.features.fragelebenslauf.interfaces;

import de.gruppe_D.features.fragelebenslauf.FrageLebenslaufModel;

public interface JDBCFrageLebenslaufRepository {
    FrageLebenslaufModel findByUsername(String username);

    FrageLebenslaufModel findByUsernameInDB(String username);

    void save(FrageLebenslaufModel frageLebenslaufModel);
}