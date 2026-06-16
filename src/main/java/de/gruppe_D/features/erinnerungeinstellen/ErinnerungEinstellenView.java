package de.gruppe_D.features.erinnerungeinstellen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static de.gruppe_D.app.utils.UIUtils.*;

public class ErinnerungEinstellenView extends JPanel {
    public JTextField tfReminder = createTextField("5");
    public JButton btnBack = new JButton("Zurück");
    public JButton btnFinish = new JButton("Setup abschließen");
    public JLabel subtitle = new JLabel("Nach wie vielen Wochen willst du dich noch einmal melden?");
    public JLabel title = createHeadTextLabel("Ersteinrichtung (2/2)");

    public ErinnerungEinstellenView() {
        setLayout(new GridBagLayout());
        setBackground(BG_DARK);

        JPanel formBox = new JPanel();
        formBox.setLayout(new BoxLayout(formBox, BoxLayout.Y_AXIS));
        formBox.setBackground(SIDEBAR_BG);
        formBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_DARK_GRAY, 1, true), new EmptyBorder(40, 50, 40, 50)));

        subtitle.setForeground(TEXT_GREY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        tfReminder.setMaximumSize(new Dimension(100, 35));
        tfReminder.setHorizontalAlignment(JTextField.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(SIDEBAR_BG);
        btnPanel.setMaximumSize(new Dimension(400, 40));

        styleActionButton(btnBack, COLOR_DARK_BLUE_GRAY, TEXT_WHITE);
        btnBack.setPreferredSize(new Dimension(100, 35));

        styleActionButton(btnFinish, COLOR_GREEN, Color.WHITE);
        btnFinish.setPreferredSize(new Dimension(200, 35));

        btnPanel.add(btnBack);
        btnPanel.add(btnFinish);

        formBox.add(title);
        formBox.add(Box.createRigidArea(new Dimension(0, 10)));
        formBox.add(subtitle);
        formBox.add(Box.createRigidArea(new Dimension(0, 30)));
        formBox.add(createInputLabel("Wochen"));
        formBox.add(tfReminder);
        formBox.add(Box.createRigidArea(new Dimension(0, 30)));
        formBox.add(btnPanel);
        add(formBox);
    }
}