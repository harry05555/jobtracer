package de.gruppe_D.features.dbkonfigurieren;

public class DbKonfigurierenModel {
    private final String username;
    private final String password;
    private final String port;
    private final String hostname;

    public DbKonfigurierenModel(String username, String password, String port, String hostname) {
        this.username = username;
        this.password = password;
        this.port = port;
        this.hostname = hostname;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPort() {
        return this.port;
    }
    public String getHostname() {return this.hostname;}
}