package it.lucaVezzoli.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

public class PrintInterface extends JFrame {
    private JPanel mainPanel;
    private JButton stampaButton;
    private JCheckBox headerBox;
    private JTextField headerField;
    private JCheckBox footerBox;
    private JTextField footerField;
    private JCheckBox fitWidthBox;
    private JCheckBox showPrintDialogBox;
    private JCheckBox interactiveBox;
    private JButton annullaButton;
    private String headerPrevious;
    private String footerPrevious;
    private JFrame frame;

    public PrintInterface(JFrame frame, Interface interfaccia, JTable table,
                          String type) {
        this.frame = frame;

        final JTable table1 = table;
        final JFrame frame1 = frame;

        stampaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printGradesTable(table1);
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

        headerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                headerField.setEnabled(headerBox.isSelected());
                if (!headerBox.isSelected())
                    headerPrevious = headerField.getText();
                headerField.setText(headerBox.isSelected() ? headerPrevious : "");
            }
        });

        footerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                footerField.setEnabled(footerBox.isSelected());
                if (!footerBox.isSelected())
                    footerPrevious = footerField.getText();
                footerField.setText(footerBox.isSelected() ? footerPrevious : "");
            }
        });

        showPrintDialogBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!showPrintDialogBox.isSelected()) {
                    JOptionPane.showMessageDialog(
                            PrintInterface.this,
                            "Se il dialogo di stampa è spento,"
                                    + " viene utilizzata la stampante di default.",
                            "Stato stampa",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        interactiveBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!interactiveBox.isSelected()) {
                    JOptionPane.showMessageDialog(
                            PrintInterface.this,
                            "Se l'interfaccia non è interattiva,"
                                    + " rimarrà bloccata durante il processo di stampa.",
                            "Stato stampa",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        headerField.setText("Oratorio di Capriolo, grest anno: " + interfaccia.getDbConnection().getDbTable());
        footerField.setText("Tabella " + type);
    }

    private void printGradesTable(JTable table) {
        MessageFormat header = null;

        if (headerBox.isSelected()) {
            header = new MessageFormat(headerField.getText());
        }

        MessageFormat footer = null;

        if (footerBox.isSelected()) {
            footer = new MessageFormat(footerField.getText());
        }

        boolean fitWidth = fitWidthBox.isSelected();
        boolean showPrintDialog = showPrintDialogBox.isSelected();
        boolean interactive = interactiveBox.isSelected();

        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH
                : JTable.PrintMode.NORMAL;

        try {
            boolean complete = table.print(mode, header, footer,
                    showPrintDialog, null,
                    interactive, null);

            if (complete) {
                JOptionPane.showMessageDialog(this,
                        "Stampa riuscita",
                        "Risultati stampa",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        StartProgramInterface.createAndShowGUI();
                    }
                });
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(this,
                    "Stampa fallita",
                    "Risultati stampa",
                    JOptionPane.ERROR_MESSAGE);
            pe.printStackTrace();
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(JTable table, Interface interfaccia, String type) {
        JFrame frame = new JFrame("Aggiunta animatore");

        JComponent newContentPane = new PrintInterface(frame, interfaccia, table, type).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
