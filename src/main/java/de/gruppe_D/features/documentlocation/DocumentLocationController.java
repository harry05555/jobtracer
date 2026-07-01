package de.gruppe_D.features.documentlocation;

import de.gruppe_D.app.Router;

import javax.swing.*;
import java.awt.event.ActionListener;

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
}