package de.gruppe_D.features.uebersicht;

public class UebersichtMapper {

    public static UebersichtModel fromCsvRow(String[] parts) {
        if (parts.length >= 15) {
            return new UebersichtModel(
                    parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],
                    parts[8], parts[9], parts[10], parts[11], parts[12], parts[13], parts[14]
            );
        }
        return null;
    }

    public static String toCsvRow(UebersichtModel model) {
        return "🔍;;;" + model.getFirma() + ";;;" + model.getStelle() + ";;;" + model.getOrt() + ";;;" +
                model.getPlattform() + ";;;" + model.getVersendetAm() + ";;;" + model.getFrist() + ";;;" +
                model.getStatus() + ";;;" + model.getAnsprechpartner() + ";;;" + model.getEmail() + ";;;" +
                model.getTelefon() + ";;;" + model.getAdresse() + ";;;" + model.getErinnerung() + ";;;" +
                model.getPdfPfad() + ";;;" + model.getPdfName();
    }
}