package de.gruppe_D.features.fragelebenslauf;

import de.gruppe_D.app.Router;

import java.awt.event.ActionListener;

public class FrageLebenslaufController {
    private final FrageLebenslaufView view;
    private final FrageLebenslaufService frageLebenslaufService;
    private final Router router;

    public FrageLebenslaufController(FrageLebenslaufView view, FrageLebenslaufService frageLebenslaufService, Router router) {
        this.view = view;
        this.frageLebenslaufService = frageLebenslaufService;
        this.router = router;
        init();
    }

    private void init() {
        view.btnSave.addActionListener(benutzerinformationen());
        view.btnSkip.addActionListener(skip());
    }

    private ActionListener benutzerinformationen() {
        return e -> {
            frageLebenslaufService.benutzerinformationen(view.tfVorname.getText(), view.tfName.getText(), view.tfAdresse.getText());
//            todo Hier kommt noch die Route, es vollständig ausfüllen will
        };
    }

    private ActionListener skip() {
        return e -> {
//           todo Hier kommt noch die Route, wenn man überspringen will
        };
    }
}