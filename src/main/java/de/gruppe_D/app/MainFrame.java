package de.gruppe_D.app;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("App");
        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void setView(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }
}