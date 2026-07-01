package de.gruppe_D.features.fragelebenslauf;

import de.gruppe_D.features.fragelebenslauf.interfaces.JDBCFrageLebenslaufRepository;

public class FrageLebenslaufService {

    private final JDBCFrageLebenslaufRepository frageLebenslaufRepository;

    public FrageLebenslaufService(JDBCFrageLebenslaufRepository frageLebenslaufRepository) {
        this.frageLebenslaufRepository = frageLebenslaufRepository;
    }

    public void benutzerinformationen(String vorname, String nachname, String adresse) {
        frageLebenslaufRepository.save(new FrageLebenslaufModel(nachname, vorname, adresse.split(",")[0], adresse.split(",")[1], adresse.split(",")[2]));
    }
}