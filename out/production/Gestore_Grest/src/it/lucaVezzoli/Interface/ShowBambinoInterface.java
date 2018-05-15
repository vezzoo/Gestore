package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.Bambino;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowBambinoInterface {
    private JPanel mainPanel;
    private JTextField cognomeJTF;
    private JTextField settimaneJTF;
    private JTextField nomeJTF;
    private JTextField giornoJTF;
    private JTextField meseJTF;
    private JTextField annoJTF;
    private JTextField classeJTF;
    private JTextField indirizzoJTF;
    private JTextField telefono1JTF;
    private JTextField telefono2JTF;
    private JTextField preferenzaJTF;
    private JTextField grestJTF;
    private JTextField tagliaJTF;
    private JTextField pitstopJTF;
    private JTextField maglietteJTF;
    private JTextField pagamentoJTF;
    private JTextField giornoSaldoJTF;
    private JTextField meseSaldoJTF;
    private JTextField annoSaldoJTF;
    private JTextField entrataAnticipataJTF;
    private JTextField pranzoJTF;
    private JButton modificaButton;

    public ShowBambinoInterface(final JFrame frame, final Bambino bambino, final Interface interfaccia) {
        cognomeJTF.setText(bambino.getCognome());
        nomeJTF.setText(bambino.getNome());
        giornoJTF.setText(Integer.toString(bambino.getDataDiNascita().getGiorno()));
        meseJTF.setText(Integer.toString(bambino.getDataDiNascita().getMese()));
        annoJTF.setText(Integer.toString(bambino.getDataDiNascita().getAnno()));
        classeJTF.setText(bambino.getClasse());
        indirizzoJTF.setText(bambino.getIndirizzo());
        telefono1JTF.setText(bambino.getTelefono1());
        telefono2JTF.setText(bambino.getTelefono2());
        preferenzaJTF.setText(bambino.getPreferenza());
        grestJTF.setText(bambino.isGrest().equals("Non specificato") ?
                "" : bambino.isGrest());
        settimaneJTF.setText(bambino.getSettimane().equals("Non specificato") ?
                "" : bambino.getSettimane());
        tagliaJTF.setText(bambino.getTaglia().equals("Non specificato") ?
                "" : bambino.getTaglia());
        pitstopJTF.setText(bambino.isPit_stop().equals("Non specificato") ?
                "" : bambino.isPit_stop());
        maglietteJTF.setText(bambino.getMaglietta());
        pagamentoJTF.setText(bambino.getPagamento().equals("Non specificato") ?
                "" : bambino.getPagamento());

        try {
            giornoSaldoJTF.setText(Integer.toString(bambino.getDataSaldo().getGiorno()));
            meseSaldoJTF.setText(Integer.toString(bambino.getDataSaldo().getMese()));
            annoSaldoJTF.setText(Integer.toString(bambino.getDataSaldo().getAnno()));
        } catch (Exception ex) {
            giornoSaldoJTF.setText("N/A");
            meseSaldoJTF.setText("N/A");
            annoSaldoJTF.setText("N/A");
        }

        entrataAnticipataJTF.setText(bambino.isEntrataAnticipata().equals("Non specificato") ?
                "" : bambino.isEntrataAnticipata());
        pranzoJTF.setText(bambino.isPranzo().equals("Non specificato") ?
                "" : bambino.isPranzo());

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.dispose();
                        AddBambinoInterface.createAndShowGUI(interfaccia, bambino.getId());
                    }
                });
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(Bambino bambino, Interface interfaccia) {
        JFrame frame = new JFrame("");

        JComponent newContentPane = new ShowBambinoInterface(frame, bambino, interfaccia).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
