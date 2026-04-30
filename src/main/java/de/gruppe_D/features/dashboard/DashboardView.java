package de.gruppe_D.features.dashboard;

import javax.swing.*;

public class DashboardView extends JPanel {
    public JButton logoutButton = new JButton("Logout");

    public DashboardView() {
        add(new JLabel("Willkommen im Dashboard"));
        add(logoutButton);
    }

}