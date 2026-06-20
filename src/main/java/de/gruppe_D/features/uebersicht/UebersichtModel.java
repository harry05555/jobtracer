package de.gruppe_D.features.uebersicht;

public class UebersichtModel {
    private String firma, stelle, ort, plattform, versendetAm, frist;
    private String status, ansprechpartner, email, telefon, adresse, erinnerung, pdfPfad, pdfName;

    public UebersichtModel(String firma, String stelle, String ort, String plattform, String versendetAm,
                           String frist, String status, String ansprechpartner, String email,
                           String telefon, String adresse, String erinnerung, String pdfPfad, String pdfName) {
        this.firma = firma; this.stelle = stelle; this.ort = ort; this.plattform = plattform;
        this.versendetAm = versendetAm; this.frist = frist; this.status = status;
        this.ansprechpartner = ansprechpartner; this.email = email; this.telefon = telefon;
        this.adresse = adresse; this.erinnerung = erinnerung; this.pdfPfad = pdfPfad; this.pdfName = pdfName;
    }

    // --- GETTER ---
    public String getFirma() { return firma; }
    public String getStelle() { return stelle; }
    public String getOrt() { return ort; }
    public String getPlattform() { return plattform; }
    public String getVersendetAm() { return versendetAm; }
    public String getFrist() { return frist; }
    public String getStatus() { return status; }
    public String getAnsprechpartner() { return ansprechpartner; }
    public String getEmail() { return email; }
    public String getTelefon() { return telefon; }
    public String getAdresse() { return adresse; }
    public String getErinnerung() { return erinnerung; }
    public String getPdfPfad() { return pdfPfad; }
    public String getPdfName() { return pdfName; }

    // --- SETTER ---
    public void setFirma(String firma) { this.firma = firma; }
    public void setStelle(String stelle) { this.stelle = stelle; }
    public void setOrt(String ort) { this.ort = ort; }
    public void setPlattform(String plattform) { this.plattform = plattform; }
    public void setFrist(String frist) { this.frist = frist; }
    public void setStatus(String status) { this.status = status; }
    public void setAnsprechpartner(String ansprechpartner) { this.ansprechpartner = ansprechpartner; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setErinnerung(String erinnerung) { this.erinnerung = erinnerung; }
}