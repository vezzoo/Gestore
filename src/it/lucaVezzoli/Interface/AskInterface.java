package it.lucaVezzoli.Interface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static it.lucaVezzoli.Application.Security.getEncoded;

public class AskInterface extends JPanel {
    private JPanel mainPanel;
    private JTextField userName;
    private JPasswordField password;
    private JButton confirmButton;
    private JButton cancelButton;

    public AskInterface(final JFrame frame, final Interface interfaccia) {
        Border defaultBorder = password.getBorder();

        userName.setText(interfaccia.getAccount().getNome());

        final Border border = defaultBorder;

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (AskInterface.checkCredential(userName.getText(), password.getText(), interfaccia)) {
                    password.setBorder(border);

                    frame.dispose();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            SwitchInterface.createAndShowGUI(interfaccia);
                        }
                    });
                } else {
                    password.setBorder(new LineBorder(Color.red, 2));
                    password.setText("");
                }
            }
        });
    }

    private static boolean checkCredential(String userName, String password, Interface interfaccia) {
        return userName.equals(interfaccia.getAccount().getNome())
                && getEncoded(password).equals(interfaccia.getAccount().getPassword());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(Interface interfaccia) {
        JFrame frame = new JFrame("");

        JComponent newContentPane = new AskInterface(frame, interfaccia).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
