package de.gruppe_D.features.documentlocation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;

import static de.gruppe_D.app.utils.UIUtils.*;

public class DocumentLocationView extends JPanel {
    public JTextField usernameField = new JTextField(15);
    public JPasswordField passwordField = new JPasswordField(15);
    public JButton loginButton = new JButton("Login");
    public JButton btnBrowse = new JButton("📁");
    public JButton btnNext = new JButton("Weiter");

    public String pdfSpeicherPfad = System.getProperty("user.home") + File.separator + "Documents";
    public JTextField tfPath = createTextField(pdfSpeicherPfad);
    public CardLayout rootCardLayout = new CardLayout();
    public JPanel rootPanel = new JPanel(rootCardLayout);

    public DocumentLocationView() {
        setLayout(new GridBagLayout());
        setBackground(BG_DARK);
        JPanel formBox = new JPanel();
        formBox.setLayout(new BoxLayout(formBox, BoxLayout.Y_AXIS));
        formBox.setBackground(SIDEBAR_BG);
        formBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_DARK_GRAY, 1, true), new EmptyBorder(40, 50, 40, 50)));

        JLabel title = createHeadTextLabel("Ersteinrichtung (1/2)");

        JLabel subtitle = new JLabel("Wo sollen Bewerbungsdokumente gespeichert werden?");
        subtitle.setForeground(TEXT_GREY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel pathPanel = new JPanel(new BorderLayout(10, 0));
        pathPanel.setBackground(SIDEBAR_BG);
        pathPanel.setMaximumSize(new Dimension(400, 35));

        tfPath.setMaximumSize(new Dimension(350, 35));

        styleActionButton(btnBrowse, COLOR_DARK_BLUE_GRAY, TEXT_WHITE);
        btnBrowse.setPreferredSize(new Dimension(45, 35));

        btnBrowse.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                tfPath.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        pathPanel.add(tfPath, BorderLayout.CENTER);
        pathPanel.add(btnBrowse, BorderLayout.EAST);

        styleActionButton(btnNext, ACCENT_COLOR, Color.WHITE);
        btnNext.setMaximumSize(new Dimension(200, 40));
        btnNext.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnNext.addActionListener(e -> {
            String path = tfPath.getText().trim();
            File checkDir = new File(path);
            if (!checkDir.exists() || !checkDir.isDirectory()) {
                JOptionPane.showMessageDialog(this, "Bitte wähle einen gültigen und existierenden Ordner aus!", "Fehler", JOptionPane.WARNING_MESSAGE);
                return;
            }
            pdfSpeicherPfad = path;
            rootCardLayout.show(rootPanel, "SETUP_REMINDER");
        });

        formBox.add(title);
        formBox.add(Box.createRigidArea(new Dimension(0, 10)));
        formBox.add(subtitle);
        formBox.add(Box.createRigidArea(new Dimension(0, 30)));
        formBox.add(createInputLabel("Standard PDF-Speicherpfad"));
        formBox.add(pathPanel);
        formBox.add(Box.createRigidArea(new Dimension(0, 30)));
        formBox.add(btnNext);

        add(formBox);
    }
}