package de.gruppe_D.features.fragelebenslauf;

import javax.swing.*;

public class FrageLebenslaufView extends JPanel {
    public JTextField usernameField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JButton loginButton = new JButton("Login");

    public FrageLebenslaufView() {
        setLayout(new java.awt.FlowLayout());
        add(new JLabel("cyyy"));
        add(usernameField);

        add(new JLabel("Password"));
        add(passwordField);

        add(loginButton);

    }
}