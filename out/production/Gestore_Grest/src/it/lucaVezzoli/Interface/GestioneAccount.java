package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import static it.lucaVezzoli.Application.Security.getEncoded;

public class GestioneAccount extends JPanel {
    private JPanel mainPanel;
    private JTextField nome;
    private JPasswordField password;
    private JPasswordField password2;
    private JButton salvaButton;
    private JButton modificaButton;
    private JTextField informationArea;
    private JPasswordField passwordVecchia;
    private boolean ok;

    public GestioneAccount(Interface interfaccia, JFrame frame) {
        informationArea.setText("Cliccare su modifica per modificare l'account");

        final JFrame frame1 = frame;
        final Interface interfaccia1 = interfaccia;

        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ok = false;
                    if (getEncoded(passwordVecchia.getText()).equals(interfaccia1.getAccount().getPassword())) {
                        if (password.getText().equals(password2.getText())) {
                            ObjectOutputStream writer
                                    = new ObjectOutputStream(new FileOutputStream(new File(
                                    "Data/AccountData.data")));
                            writer.writeObject(new Account(nome.getText(), getEncoded(password.getText()), ""));
                            writer.close();

                            informationArea.setText("Dati salvati correttamente!");
                            ok = true;

                            interfaccia1.getFrame().dispose();
                            frame1.dispose();

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    StartProgramInterface.createAndShowGUI();
                                }
                            });
                        } else {
                            informationArea.setText("Le password non corrispondono!");
                        }
                    } else {
                        informationArea.setText("La password vecchia è errata!");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    informationArea.setText("Impossibile salvare!");
                }

                if (ok) {
                    nome.setEditable(false);
                    password.setEditable(false);
                    password2.setEditable(false);
                    passwordVecchia.setEditable(false);
                    salvaButton.setEnabled(false);
                    modificaButton.setEnabled(true);
                    informationArea.setText("Cliccare su modifica per modificare l'account");
                    passwordVecchia.setText("");
                }
            }
        });

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome.setEditable(true);
                password.setEditable(true);
                password2.setEditable(true);
                passwordVecchia.setEditable(true);
                salvaButton.setEnabled(true);
                modificaButton.setEnabled(false);
                informationArea.setText("Modificare e fare click su salva per salvare i dati");
            }
        });
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setTexts(Interface interfaccia) {
        nome.setText(interfaccia.getAccount().getNome());
        informationArea.setText(interfaccia.getAccount().getErrors());
    }

    public static void createAndShowGUI(Interface interfaccia) {
        JFrame frame = new JFrame("Impostazioni");

        GestioneAccount gestioneAccount = new GestioneAccount(interfaccia, frame);

        JComponent newContentPane = gestioneAccount.getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        gestioneAccount.setTexts(interfaccia);
        frame.setVisible(true);
    }

}
