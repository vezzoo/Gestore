package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SwitchInterface {
    private JPanel mainPanel;
    private JPanel downPanel;
    private JPanel upPanel;
    private JTextField nomeJTF;
    private JPasswordField passwordJPF;
    private JButton salvaButton;
    private JTextField hostJTF;
    private JTextField userJTF;
    private Interface interfaccia;
    private JFrame frame;

    public SwitchInterface(JFrame frame, Interface interfaccia) {
        this.interfaccia = interfaccia;
        this.frame = frame;

        final Interface interfaccia1 = interfaccia;
        final JFrame frame1 = frame;

        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interfaccia1.getDbConnection().setDbName(nomeJTF.getText());
                interfaccia1.getDbConnection().setDbHost(hostJTF.getText());
                interfaccia1.getDbConnection().setDbUser(userJTF.getText());
                interfaccia1.getDbConnection().setDbPassword(passwordJPF.getText());

                interfaccia1.salvaSuFile();

                interfaccia1.getFrame().dispose();
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

    public void setTexts() {
        nomeJTF.setText(interfaccia.getDbConnection().getDbName());
        hostJTF.setText(interfaccia.getDbConnection().getDbHost());
        userJTF.setText(interfaccia.getDbConnection().getDbUser());
        passwordJPF.setText(interfaccia.getDbConnection().getDbPassword());
    }

    public static void createAndShowGUI(Interface interfaccia) {
        JFrame frame = new JFrame("Impostazioni");

        SwitchInterface switchInterface = new SwitchInterface(frame, interfaccia);

        JComponent newContentPane = switchInterface.getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        switchInterface.setTexts();
        frame.setVisible(true);
    }

}
