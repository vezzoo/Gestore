package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.Account;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import static it.lucaVezzoli.Application.Security.getEncoded;

public class CreateAccount {
    private JPanel mainPanel;
    private JButton salvaButton;
    private JTextField nomeUtenteJTF;
    private JPasswordField password1;
    private JPasswordField password2;

    public CreateAccount(Interface interfaccia, JFrame frame) {
        final Border defaultBorder = nomeUtenteJTF.getBorder();
        final JFrame frame1 = frame;
        final Interface interfaccia1 = interfaccia;


        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nomeUtenteJTF.getText().equals("")) {
                    nomeUtenteJTF.setBorder(defaultBorder);
                    if (password1.getText().equals(password2.getText())) {
                        password1.setBorder(defaultBorder);
                        password2.setBorder(defaultBorder);

                        try {
                            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(
                                    new File("Data/AccountData.data")));
                            writer.writeObject(new Account(nomeUtenteJTF.getText(), getEncoded(password1.getText()), ""));
                            writer.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        frame1.dispose();
                        interfaccia1.getFrame().dispose();

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                StartProgramInterface.createAndShowGUI();
                            }
                        });
                    } else {
                        password1.setBorder(new LineBorder(Color.RED, 2));
                        password2.setBorder(new LineBorder(Color.RED, 2));
                    }
                } else
                    nomeUtenteJTF.setBorder(new LineBorder(Color.RED, 2));
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(Interface interfaccia) {
        JFrame frame = new JFrame("Impostazioni");

        JComponent newContentPane = new CreateAccount(interfaccia, frame).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
