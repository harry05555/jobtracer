package de.gruppe_D.features.dashboard2;

import javax.swing.*;

public class Dashboard2View extends JPanel {
    public JTextField usernameField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JButton loginButton = new JButton("Login");

    public Dashboard2View() {
        setLayout(new java.awt.FlowLayout());
        add(new JLabel("TestUsername"));
        add(usernameField);

        add(new JLabel("Password"));
        add(passwordField);

        add(loginButton);

    }
}