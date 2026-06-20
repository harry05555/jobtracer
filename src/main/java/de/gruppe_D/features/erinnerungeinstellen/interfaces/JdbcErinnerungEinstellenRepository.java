package de.gruppe_D.features.erinnerungeinstellen.interfaces;

import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenModel;

public interface JdbcErinnerungEinstellenRepository {
    void save(ErinnerungEinstellenModel erinnerungEinstellenModel);
}