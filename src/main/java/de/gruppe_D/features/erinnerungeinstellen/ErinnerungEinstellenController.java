package de.gruppe_D.features.erinnerungeinstellen;

import de.gruppe_D.app.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ErinnerungEinstellenController {
    private final ErinnerungEinstellenView view;
    private final ErinnerungEinstellenService erinnerungEinstellenService;
    private final Router router;

    public ErinnerungEinstellenController(ErinnerungEinstellenView view, ErinnerungEinstellenService erinnerungEinstellenService, Router router) {
        this.view = view;
        this.erinnerungEinstellenService = erinnerungEinstellenService;
        this.router = router;
        init();
    }

    private void init() {
        view.btnBack.addActionListener(btnBack());
        view.btnFinish.addActionListener(btnFinish());
    }

    private ActionListener btnBack() {
        return e -> {
            //        TODO hier kommt der Router für die view für die PDF Einstellung
        };
    }

    private ActionListener btnFinish() {
        return e -> {

            String input = view.tfReminder.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Bitte gib eine Zahl ein (z.B. 2).", "Eingabe fehlt", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                erinnerungEinstellenService.save(Long.parseLong(input));
                //        TODO hier kommt der Router für die view für die view "fragen Lebenslauf, Anschreiben und allen Bewerbungsunterlagen hochladen will"
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Bitte nur ganze Zahlen eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        };
    }
}