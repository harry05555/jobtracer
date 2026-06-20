package de.gruppe_D.features.uebersicht;

import de.gruppe_D.features.uebersicht.infrastructure.UebersichtRepository;
import java.util.List;

public class UebersichtService {

    private final UebersichtRepository repository;

    public UebersichtService(UebersichtRepository repository) {
        this.repository = repository;
    }

    public List<UebersichtModel> getAllBewerbungen() {
        return repository.loadAll();
    }

    public void updateBewerbungen(List<UebersichtModel> bewerbungen) {
        repository.saveAll(bewerbungen);
    }
}