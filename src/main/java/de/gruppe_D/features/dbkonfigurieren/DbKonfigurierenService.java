package de.gruppe_D.features.dbkonfigurieren;

import de.gruppe_D.features.dbkonfigurieren.interfaces.LokalDbKonfigurierenRepository;

public class DbKonfigurierenService {

    private final LokalDbKonfigurierenRepository dbKonfigurierenRepository;

    public DbKonfigurierenService(LokalDbKonfigurierenRepository dbKonfigurierenRepository) {
        this.dbKonfigurierenRepository = dbKonfigurierenRepository;
    }

    public void DBInfosSpeichern(String username, String password, String port, String hostname) {
        DbKonfigurierenModel  konfigurieren  = new DbKonfigurierenModel(username, password, port, hostname);
        dbKonfigurierenRepository.saveDatei(konfigurieren);
    }
}