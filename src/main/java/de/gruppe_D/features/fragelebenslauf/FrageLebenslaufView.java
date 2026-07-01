package de.gruppe_D.features.fragelebenslauf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static de.gruppe_D.app.utils.UIUtils.*;

public class FrageLebenslaufView extends JPanel {
    public JTextField usernameField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JButton loginButton = new JButton("Login");

    public JButton btnSave = new JButton("Daten speichern");
    public JButton btnSkip = new JButton("Überspringen");
    public JLabel title = createHeadTextLabel("Persönliche Daten (3/3)");
    public JTextField tfName = createTextField("");
    public JTextField tfVorname = createTextField("");
    public JTextField tfAdresse = createTextField("");

    public FrageLebenslaufView() {
        setLayout(new GridBagLayout());
        setBackground(BG_DARK);

        JPanel formBox = new JPanel();
        formBox.setLayout(new BoxLayout(formBox, BoxLayout.Y_AXIS));
        formBox.setBackground(SIDEBAR_BG);
        formBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_DARK_GRAY, 1, true), new EmptyBorder(40, 50, 40, 50)));

        setupPlaceholder(tfName, "Name");
        setupPlaceholder(tfVorname, "Vorname");
        setupPlaceholder(tfAdresse, "Straße, PLZ, Ort");

        styleActionButton(btnSave, COLOR_GREEN, Color.WHITE);
        btnSave.setMaximumSize(new Dimension(260, 35));

        styleActionButton(btnSkip, COLOR_DARK_BLUE_GRAY, TEXT_WHITE);
        btnSkip.setMaximumSize(new Dimension(260, 35));

        formBox.add(title);
        formBox.add(Box.createRigidArea(new Dimension(0, 20)));
        formBox.add(tfName);
        formBox.add(Box.createRigidArea(new Dimension(0, 5)));
        formBox.add(tfVorname);
        formBox.add(Box.createRigidArea(new Dimension(0, 5)));
        formBox.add(tfAdresse);
        formBox.add(Box.createRigidArea(new Dimension(0, 20)));
        formBox.add(btnSave);
        formBox.add(Box.createRigidArea(new Dimension(0, 10)));
        formBox.add(btnSkip);

        add(formBox);
    }
}