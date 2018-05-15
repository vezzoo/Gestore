package it.lucaVezzoli.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class ConfirmInterface {
    private JPanel mainPanel;
    private JButton annullaButton;
    private JButton confermaButton;

    public ConfirmInterface(JFrame frame, Interface interfaccia, String text) {
        final JFrame frame1 = frame;
        final Interface interfaccia1 = interfaccia;
        final String text1 = text;

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement preparedStatementAnimatori = interfaccia1.getConnection().prepareStatement(
                            "DROP TABLE Animatori_" + text1);

                    PreparedStatement preparedStatementBambini = interfaccia1.getConnection().prepareStatement(
                            "DROP TABLE Bambini_" + text1);

                    preparedStatementAnimatori.execute();

                    preparedStatementBambini.execute();

                    for (int k = 0; k < interfaccia1.getData().size(); k++)
                        if (interfaccia1.getData().get(k).getText().equals(text1))
                            interfaccia1.getData().remove(k);

                    interfaccia1.impostaAttivo(text1.equals(interfaccia1.getData().get(0).getText()) ?
                            interfaccia1.getData().get(1) : interfaccia1.getData().get(0));

                    frame1.dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            StartProgramInterface.createAndShowGUI();
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        StartProgramInterface.createAndShowGUI();
                    }
                });
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(Interface interfaccia, String text) {
        JFrame frame = new JFrame("Nuova tabella");

        JComponent newContentPane = new ConfirmInterface(frame, interfaccia, text).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
