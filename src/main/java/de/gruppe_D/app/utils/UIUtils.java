package de.gruppe_D.app.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UIUtils {
    public static final Color BG_DARK = new Color(24, 25, 28);
    public static final Color SIDEBAR_BG = new Color(38, 39, 48);
    public static final Color INPUT_BG = new Color(20, 21, 24);
    public static final Color TABLE_HEADER_BG = new Color(45, 45, 55);
    public static final Color TEXT_WHITE = new Color(250, 250, 250);
    public static final Color TEXT_GREY = new Color(150, 150, 150);
    public static final Color ACCENT_COLOR = new Color(75, 110, 175);
    public static final Color COLOR_GREEN = new Color(92, 184, 92);
    public static final Color COLOR_YELLOW = new Color(240, 173, 78);
    public static final Color COLOR_RED = new Color(217, 83, 79);
    public static final Color COLOR_BLUE = new Color(64, 153, 255);
    public static final Color COLOR_DARK_GRAY = new Color(60, 60, 60);
    public static final Color COLOR_DARK_BLUE_GRAY = new Color(60, 65, 75);

    public static JTextField createTextField(String text) {
        JTextField tf = new JTextField(text);
        tf.setBackground(INPUT_BG);
        tf.setForeground(TEXT_WHITE);
        tf.setCaretColor(TEXT_WHITE);
        tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_DARK_GRAY), new EmptyBorder(5, 8, 5, 8)));
        tf.setMaximumSize(new Dimension(260, 35));
        return tf;
    }

    public static void styleActionButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
    }

    public static JLabel createInputLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(TEXT_GREY);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        return lbl;
    }

    public static JLabel createHeadTextLabel(String LabelText) {
        JLabel title = new JLabel(LabelText);
        title.setForeground(TEXT_WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    public static JComboBox<String> createDropdown(String[] items) {
        JComboBox<String> cb = new JComboBox<>(items);
        cb.setBackground(INPUT_BG);
        cb.setForeground(TEXT_WHITE);
        cb.setMaximumSize(new Dimension(260, 35));
        return cb;
    }

    public static JComboBox<String> createEditableDropdown(String[] items) {
        JComboBox<String> cb = new JComboBox<>(items);
        cb.setMaximumSize(new Dimension(260, 35));
        cb.setEditable(true);
        Component editor = cb.getEditor().getEditorComponent();
        if (editor instanceof JTextField) {
            JTextField tf = (JTextField) editor;
            tf.setBackground(INPUT_BG);
            tf.setForeground(TEXT_WHITE);
            tf.setCaretColor(TEXT_WHITE);
            tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_DARK_GRAY), new EmptyBorder(5, 8, 5, 8)));
        }
        return cb;
    }

    public static void setupPlaceholder(JTextField tf, String placeholder) {
        if (tf.getText().isEmpty()) {
            tf.setForeground(TEXT_GREY);
            tf.setText(placeholder);
        }
        tf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tf.getText().equals(placeholder)) {
                    tf.setText("");
                    tf.setForeground(TEXT_WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tf.getText().isEmpty()) {
                    tf.setForeground(TEXT_GREY);
                    tf.setText(placeholder);
                }
            }
        });
    }
}

