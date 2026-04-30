package de.gruppe_D.features.auth;

import javax.swing.*;

public class AuthView extends JPanel {
    public JTextField usernameField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JButton loginButton = new JButton("Login");

    public AuthView() {
        setLayout(new java.awt.FlowLayout());
        add(new JLabel("Username"));
        add(usernameField);

        add(new JLabel("Password"));
        add(passwordField);

        add(loginButton);

    }
}