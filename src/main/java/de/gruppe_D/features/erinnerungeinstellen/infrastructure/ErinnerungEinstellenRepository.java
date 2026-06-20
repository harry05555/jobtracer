package de.gruppe_D.features.erinnerungeinstellen.infrastructure;

import de.gruppe_D.features.erinnerungeinstellen.ErinnerungEinstellenModel;
import de.gruppe_D.features.erinnerungeinstellen.interfaces.JdbcErinnerungEinstellenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;

public class ErinnerungEinstellenRepository implements JdbcErinnerungEinstellenRepository {

    private final DataSource dataSource;

    public ErinnerungEinstellenRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(ErinnerungEinstellenModel erinnerungEinstellenModel) {
        ArrayList<ErinnerungEinstellenModel> erinnerungEinstellenModels = new ArrayList<>();
        erinnerungEinstellenModels.add(erinnerungEinstellenModel);
    }
}