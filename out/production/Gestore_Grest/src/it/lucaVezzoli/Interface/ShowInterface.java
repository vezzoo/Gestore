package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.Animatore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowInterface {
    private JPanel mainPanel;
    private JPanel upPanel;
    private JPanel downPanel;
    private JTextField cognomeJTF;
    private JTextField nomeJTF;
    private JTextField annoJTF;
    private JTextField impegniJTF;
    private JTextField responsabileJTF;
    private JTextField incontri_ABC_JTF;
    private JTextField incontri_ABC_totali_JTF;
    private JButton modificaButton;
    private JTextField grestJTF;
    private JTextField miniGrestJTF;
    private JTextField caJTF;
    private JTextField psJTF;
    private JTextField slpaJTF;
    private JTextField slJTF;
    private JPanel animazioneEstivaPanel;
    private JPanel gruppiPreferenzaPanel;
    private JTextField attivitaJTF;
    private JTextField animazioneJTF;
    private JTextField gigantiJTF;
    private JTextField giochiJTF;
    private JTextField festaFinaleJTF;
    private JTextField preghieraJTF;
    private JTextField scenografiaJTF;
    private JTextField storiaJTF;
    private JTextField segreteriaJTF;

    public ShowInterface(final Animatore animatore, final JFrame frame, final Interface interfaccia) {
        upPanel.setBorder(BorderFactory.createTitledBorder("Dati:"));
        animazioneEstivaPanel.setBorder(BorderFactory.createTitledBorder("Animazione estiva:"));
        gruppiPreferenzaPanel.setBorder(BorderFactory.createTitledBorder("Gruppi preferenza:"));

        cognomeJTF.setText(animatore.getCognome());
        nomeJTF.setText(animatore.getNome());
        annoJTF.setText("" + animatore.getAnno());
        impegniJTF.setText(animatore.getImpegni());
        incontri_ABC_JTF.setText(Integer.toString(animatore.getAbc_incontri_partecipato()));
        incontri_ABC_totali_JTF.setText(Integer.toString(animatore.getAbc_incontri_totali()));
        responsabileJTF.setText(animatore.getResponsabile() ? "Sì" : "No");

        grestJTF.setText(animatore.getAnimazioneEstiva()[0].getPartecipazione());
        miniGrestJTF.setText(animatore.getAnimazioneEstiva()[1].getPartecipazione());
        caJTF.setText(animatore.getAnimazioneEstiva()[2].getPartecipazione());
        psJTF.setText(animatore.getAnimazioneEstiva()[3].getPartecipazione());
        slpaJTF.setText(animatore.getAnimazioneEstiva()[4].getPartecipazione());
        slJTF.setText(animatore.getAnimazioneEstiva()[5].getPartecipazione());

        attivitaJTF.setText(animatore.getGruppiPreferenza()[0].getVoto());
        animazioneJTF.setText(animatore.getGruppiPreferenza()[1].getVoto());
        gigantiJTF.setText(animatore.getGruppiPreferenza()[2].getVoto());
        giochiJTF.setText(animatore.getGruppiPreferenza()[3].getVoto());
        festaFinaleJTF.setText(animatore.getGruppiPreferenza()[4].getVoto());
        preghieraJTF.setText(animatore.getGruppiPreferenza()[5].getVoto());
        scenografiaJTF.setText(animatore.getGruppiPreferenza()[6].getVoto());
        segreteriaJTF.setText(animatore.getGruppiPreferenza()[7].getVoto());
        storiaJTF.setText(animatore.getGruppiPreferenza()[8].getVoto());

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AddInterface.createAndShowGUI(interfaccia, animatore.getId());
                    }
                });
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(Animatore animatore, Interface interfaccia) {
        JFrame frame = new JFrame("");

        JComponent newContentPane = new ShowInterface(animatore, frame, interfaccia).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
