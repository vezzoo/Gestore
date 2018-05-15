package it.lucaVezzoli.Interface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class NewYearInterface {
    private JPanel mainPanel;
    private JButton salvaButton;
    private JButton annullaButton;
    private JTextField yearJTF;

    public NewYearInterface(final JFrame frame, final Interface interfaccia) {
        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Border defaultBorder = yearJTF.getBorder();

                try {
                    int anno = Integer.parseInt(yearJTF.getText());
                    yearJTF.setBorder(defaultBorder);
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Data/AnniABC.data")));
                        writer.write(Integer.toString(anno));
                        writer.close();

                        frame.dispose();
                        interfaccia.getFrame().dispose();

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                StartProgramInterface.createAndShowGUI();
                            }
                        });

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    yearJTF.setBorder(new LineBorder(Color.RED, 2));
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(Interface interfaccia) {
        JFrame frame = new JFrame();

        JComponent newContentPane = new NewYearInterface(frame, interfaccia).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
