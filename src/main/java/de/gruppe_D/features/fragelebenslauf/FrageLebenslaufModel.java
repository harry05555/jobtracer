package de.gruppe_D.features.fragelebenslauf;

public class FrageLebenslaufModel {
    private Long id;
    private final String username;
    private final String password;

    public FrageLebenslaufModel(Long id, String username, String password) {
        this.id = id;
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username darf nicht leer sein");
        }
        this.username = username;
        this.password = password;
    }

    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    public String getUsername() {
        return username;
    }
}