package de.gruppe_D.features.dbkonfigurieren.interfaces;

import de.gruppe_D.features.dbkonfigurieren.DbKonfigurierenModel;

public interface LokalDbKonfigurierenRepository {

    DbKonfigurierenModel loadDatei();
    void saveDatei(DbKonfigurierenModel dbKonfigurierenModel);
}