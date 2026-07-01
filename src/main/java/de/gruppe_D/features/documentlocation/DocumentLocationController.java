package de.gruppe_D.features.documentlocation;

import de.gruppe_D.app.Router;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class DocumentLocationController {
    private final DocumentLocationView view;
    private final DocumentLocationService documentLocationService;
    private final Router router;

    public DocumentLocationController(DocumentLocationView view, DocumentLocationService documentLocationService, Router router) {
        this.view = view;
        this.documentLocationService = documentLocationService;
        this.router = router;
        init();
    }

    private void init() {
        view.btnBrowse.addActionListener(fileChooser());
        view.btnNext.addActionListener(savePath());
    }

    private ActionListener fileChooser() {
        return e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                view.tfPath.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        };
    }

    private ActionListener savePath() {
        return e -> {
            String path = view.tfPath.getText().trim();
            File checkDir = new File(path);
            if (!checkDir.exists() || !checkDir.isDirectory()) {
                JOptionPane.showMessageDialog(view, "Bitte wähle einen gültigen und existierenden Ordner aus!", "Fehler", JOptionPane.WARNING_MESSAGE);
                return;
            }
            documentLocationService.savePath(path);
        };
    }
}