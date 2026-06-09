package de.gruppe_D.features.erinnerungeinstellen;

import de.gruppe_D.app.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ErinnerungEinstellenController {
    private final ErinnerungEinstellenView view;
    private final ErinnerungEinstellenService ErinnerungEinstellenService;
    private final Router router;

    public ErinnerungEinstellenController(ErinnerungEinstellenView view, ErinnerungEinstellenService ErinnerungEinstellenService, Router router) {
        this.view = view;
        this.ErinnerungEinstellenService = ErinnerungEinstellenService;
        this.router = router;
        init();
    }

    private void init() {
        view.btnBack.addActionListener(btnBack());
        view.btnFinish.addActionListener(btnFinish(view.panel));
    }

    private ActionListener btnBack() {
        return e -> {
            //        TODO hier kommt der Router für die view für die PDF Einstellung
            System.out.println("btnBack");
        };
    }

    private ActionListener btnFinish(Container parent) {
        return e -> {

            System.out.println("btnFinish");

            String input = view.tfReminder.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "Bitte gib eine Zahl ein (z.B. 2).", "Eingabe fehlt", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                ErinnerungEinstellenService.save(Long.parseLong(input));
                //        TODO hier kommt der Router für die view für die view "fragen Lebenslauf, Anschreiben und allen Bewerbungsunterlagen hochladen will"
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Bitte nur ganze Zahlen eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        };
    }
}