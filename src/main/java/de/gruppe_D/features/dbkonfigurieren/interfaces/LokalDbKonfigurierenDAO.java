package de.gruppe_D.features.dbkonfigurieren.interfaces;

import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenModel;

public interface LokalDbKonfigurierenDAO {

    DbKonfigurierenModel loadDatei();
    void saveDatei(DbKonfigurierenModel dbKonfigurierenModel);
}