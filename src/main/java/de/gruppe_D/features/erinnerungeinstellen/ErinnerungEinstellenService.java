package de.gruppe_D.features.erinnerungeinstellen;

import de.gruppe_D.features.erinnerungeinstellen.interfaces.JdbcErinnerungEinstellenRepository;

public class ErinnerungEinstellenService {

    private final JdbcErinnerungEinstellenRepository erinnerungEinstellenRepository;

    public ErinnerungEinstellenService(JdbcErinnerungEinstellenRepository erinnerungEinstellenRepository) {
        this.erinnerungEinstellenRepository = erinnerungEinstellenRepository;
    }

    public void save(Long wochen) {
        erinnerungEinstellenRepository.save(new ErinnerungEinstellenModel(wochen));
    }
}