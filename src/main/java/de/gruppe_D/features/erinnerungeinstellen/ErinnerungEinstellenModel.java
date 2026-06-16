package de.gruppe_D.features.erinnerungeinstellen;

public class ErinnerungEinstellenModel {
    private Long wochen;

    public ErinnerungEinstellenModel(Long wochen) {
        this.wochen = wochen;
        if (wochen == null || wochen <= 0) {
            throw new IllegalArgumentException("Wochen dürfen nicht leer oder 0 sein");
        }
    }

    public Long getWochen() {
        return wochen;
    }
}