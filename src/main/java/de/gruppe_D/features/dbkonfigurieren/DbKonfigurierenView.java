package de.gruppe_D.features.dbkonfigurieren;

import javax.swing.*;

public class DbKonfigurierenView extends JPanel {
    public JTextField usernameField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JTextField portField = new JTextField(15);
    public JTextField hostnameField = new JTextField(15);
    public JButton loginButton = new JButton("Speichern");
    public JButton verbindungTestButton = new JButton("Teste verbindung");

    public DbKonfigurierenView() {
        setLayout(new java.awt.FlowLayout());
        add(new JLabel("DbKonfigurierenView Admin"));
        add(usernameField);

        add(new JLabel("Password"));
        add(passwordField);
        add(new JLabel("Port"));
        add(portField);
        add(new JLabel("hostname"));
        add(hostnameField);
        add(loginButton);
        add(verbindungTestButton);
    }
}