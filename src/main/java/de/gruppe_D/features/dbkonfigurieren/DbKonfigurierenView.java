package de.gruppe_D.features.dbkonfigurieren;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static de.gruppe_D.app.utils.UIUtils.*;

public class DbKonfigurierenView extends JPanel {
    public JButton verbindungTestButton = new JButton("\uD83D\uDD0C Test");
    public JButton btnConnect = new JButton("Speichern");
    public JTextField tfUser = createTextField("root");
    public JTextField tfHost = createTextField("localhost");
    public JTextField tfPort = createTextField("3306");
    public JPasswordField pfPass = new JPasswordField();

    public DbKonfigurierenView() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG_DARK);
        setLayout(new GridBagLayout());
        setBackground(BG_DARK);
        JPanel formBox = new JPanel();
        formBox.setLayout(new BoxLayout(formBox, BoxLayout.Y_AXIS));
        formBox.setBackground(SIDEBAR_BG);
        formBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_DARK_GRAY, 1, true), new EmptyBorder(40, 50, 40, 50)));

        JLabel HeadText = createHeadTextLabel("Datenbank & Setup");
        pfPass.setBackground(INPUT_BG);
        pfPass.setForeground(TEXT_WHITE);
        pfPass.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_DARK_GRAY), new EmptyBorder(5, 8, 5, 8)));
        pfPass.setMaximumSize(new Dimension(260, 35));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(SIDEBAR_BG);
        btnPanel.setMaximumSize(new Dimension(300, 40));

        styleActionButton(verbindungTestButton, COLOR_DARK_BLUE_GRAY, TEXT_WHITE);
        verbindungTestButton.setPreferredSize(new Dimension(100, 35));

        styleActionButton(btnConnect, ACCENT_COLOR, TEXT_WHITE);
        btnConnect.setPreferredSize(new Dimension(150, 35));

        btnPanel.add(verbindungTestButton);
        btnPanel.add(btnConnect);

        formBox.add(HeadText);
        formBox.add(Box.createRigidArea(new Dimension(0, 30)));
        formBox.add(createInputLabel("Hostname / IP"));
        formBox.add(tfHost);
        formBox.add(Box.createRigidArea(new Dimension(0, 15)));
        formBox.add(createInputLabel("Port"));
        formBox.add(tfPort);
        formBox.add(Box.createRigidArea(new Dimension(0, 15)));
        formBox.add(createInputLabel("Benutzername"));
        formBox.add(tfUser);
        formBox.add(Box.createRigidArea(new Dimension(0, 15)));
        formBox.add(createInputLabel("Passwort"));
        formBox.add(pfPass);
        formBox.add(Box.createRigidArea(new Dimension(0, 30)));
        formBox.add(btnPanel);
        add(formBox);
    }
}