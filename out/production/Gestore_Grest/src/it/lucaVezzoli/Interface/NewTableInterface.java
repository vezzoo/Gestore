package it.lucaVezzoli.Interface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class NewTableInterface {
    private JPanel mainPanel;
    private JTextField annoJTF;

    public NewTableInterface(JFrame frame, Interface interfaccia, String type) {
        final Interface interfaccia1 = interfaccia;
        final JFrame frame1 = frame;
        final String type1 = type;

        annoJTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ok = false;

                for (int i = 0; i < interfaccia1.getData().size(); i++)
                    if (interfaccia1.getData().get(i).getText().equals(annoJTF.getText())) {
                        ok = true;
                        break;
                    }

                String anno = annoJTF.getText().replace(" ", "");

                if (type1.equals("Aggiungi")) {
                    try {
                        if (!ok) {
                            PreparedStatement preparedStatementAnimatori = interfaccia1.getConnection().prepareStatement(
                                    "CREATE TABLE Animatori_" + anno
                                            + " (ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
                                            + " cognomenome VARCHAR(15000),"
                                            + " animatore VARCHAR(15000));");
                            preparedStatementAnimatori.execute();

                            PreparedStatement preparedStatementBambini = interfaccia1.getConnection().prepareStatement(
                                    "CREATE TABLE Bambini_" + anno
                                            + " (ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
                                            + " cognomenome VARCHAR(15000),"
                                            + " bambino VARCHAR(15000));");
                            preparedStatementBambini.execute();

                            interfaccia1.createAndShowItem(annoJTF.getText());
                            interfaccia1.getFrame().dispose();
                            interfaccia1.setAttivo(annoJTF.getText());

                            frame1.dispose();
                            interfaccia1.getFrame().dispose();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    StartProgramInterface.createAndShowGUI();
                                }
                            });
                        }

                        frame1.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (type1.equals("Rimuovi")) {
                    Border defaultBorder = annoJTF.getBorder();
                    try {
                        if (ok) {
                            annoJTF.setBorder(defaultBorder);
                            frame1.dispose();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    ConfirmInterface.createAndShowGUI(interfaccia1,
                                            annoJTF.getText());
                                }
                            });
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        annoJTF.setBorder(new LineBorder(Color.red, 2));
                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(Interface interfaccia, String type) {
        JFrame frame = new JFrame("Nuova tabella");

        JComponent newContentPane = new NewTableInterface(frame, interfaccia, type).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
