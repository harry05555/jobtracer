package de.gruppe_D.features.dbkonfigurieren.interfaces;


import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenModel;

public interface JdbcDbKonfigurierenRepository {
    DbKonfigurierenModel findByUsername(String username);

    DbKonfigurierenModel findByUsernameInDB(String username);

    void save(DbKonfigurierenModel dbKonfigurierenModel);
}