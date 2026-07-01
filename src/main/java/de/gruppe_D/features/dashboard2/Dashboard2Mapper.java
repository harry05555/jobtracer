package de.gruppe_D.features.dashboard2;

public class Dashboard2Mapper {

    // Kleines DTO (Data Transfer Object) für eine einzelne Bewerbung
    public static class BewerbungsEintrag {
        public String firma;
        public String versendetAm;
        public String frist;
        public String status;
        public String erinnerung;
    }

    // Mappt eine rohe CSV-Zeile in ein Java-Objekt
    public static BewerbungsEintrag mapCsvRowToEintrag(String[] parts) {
        BewerbungsEintrag eintrag = new BewerbungsEintrag();
        if (parts.length >= 15) {
            eintrag.firma = parts[1];
            eintrag.versendetAm = parts[5];
            eintrag.frist = parts[6];
            eintrag.status = parts[7];
            eintrag.erinnerung = parts[12];
        }
        return eintrag;
    }
}