package de.gruppe_D.features.dashboard2.infrastructure;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dashboard2Repository {

    private final DataSource dataSource;

    // Erwartet DataSource, um die AppConfig glücklich zu machen
    public Dashboard2Repository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Liest temporär die CSV, bis eure Datenbank-Tabellen stehen
    public List<String[]> fetchRawApplicationData() {
        List<String[]> rawData = new ArrayList<>();
        try {
            File f = new File("bewerbungen_data.csv");
            if (!f.exists()) return rawData;

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                rawData.add(line.split(";;;"));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawData;
    }
}