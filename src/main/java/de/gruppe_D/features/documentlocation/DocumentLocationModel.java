package de.gruppe_D.features.documentlocation;

public class DocumentLocationModel {
    private String pathLocation;

    public DocumentLocationModel(String pathLocation) {
        this.pathLocation = pathLocation;
    }

    public String getPathLocation() {
        return pathLocation;
    }

    public void setPathLocation(String pathLocation) {
        this.pathLocation = pathLocation;
    }
}