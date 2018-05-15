package it.lucaVezzoli.Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupMenu extends JPopupMenu {
    public PopupMenu(Interface interfaccia, String type) {
        JMenuItem aggiungiRiga = new JMenuItem("Aggiungi riga");
        JMenuItem rimuoviSelezionati = new JMenuItem("Rimuovi righe selezionate");

        add(aggiungiRiga);
        add(new JSeparator());
        add(rimuoviSelezionati);

        final Interface interfaccia1 = interfaccia;
        final String type1 = type;

        aggiungiRiga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (type1.equals("animatore"))
                    AddInterface.createAndShowGUI(interfaccia1, -1);
                else if (type1.equals("bambino"))
                    AddBambinoInterface.createAndShowGUI(interfaccia1, -1);
            }
        });

        rimuoviSelezionati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (type1.equals("animatore"))
                    interfaccia1.removeRows(interfaccia1.getTableAnimatori().getSelectedRows(), "animatore");
                else if (type1.equals("bambino"))
                    interfaccia1.removeRows(interfaccia1.getTableBambini().getSelectedRows(), "bambino");
            }
        });
    }
}
