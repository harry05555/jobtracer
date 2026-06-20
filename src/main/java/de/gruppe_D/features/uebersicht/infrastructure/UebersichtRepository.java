package de.gruppe_D.features.uebersicht.infrastructure;

import de.gruppe_D.features.uebersicht.UebersichtMapper;
import de.gruppe_D.features.uebersicht.UebersichtModel;

import javax.sql.DataSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UebersichtRepository {

    private static final String DATA_FILE = "bewerbungen_data.csv";
    private final DataSource dataSource; // Für AppConfig Kompatibilität

    public UebersichtRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<UebersichtModel> loadAll() {
        List<UebersichtModel> list = new ArrayList<>();
        try {
            File f = new File(DATA_FILE);
            if (!f.exists()) return list;

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                UebersichtModel model = UebersichtMapper.fromCsvRow(line.split(";;;"));
                if (model != null) list.add(model);
            }
            br.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void saveAll(List<UebersichtModel> bewerbungen) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE));
            for (UebersichtModel b : bewerbungen) {
                bw.write(UebersichtMapper.toCsvRow(b));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}