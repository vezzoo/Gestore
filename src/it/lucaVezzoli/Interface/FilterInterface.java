package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.ICheckBox;
import it.lucaVezzoli.Application.SaveArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class FilterInterface extends JPanel {
    private JButton salvaButton;
    private JButton resettaButton;
    private JPanel settimanePanel;
    private JPanel tagliaPanel;
    private JPanel magliettePanel;
    private JPanel pitStopPanel;
    private JPanel pagamentoPanel;
    private JPanel entrataAnticipataPanel;
    private JPanel pranzoPanel;
    private JPanel buttonsPanel;
    private ArrayList<ICheckBox> iCheckBoxes = new ArrayList<>();

    public FilterInterface(JFrame frame, Interface interfaccia, ArrayList<ArrayList<String>> filtri) {
        final JFrame frame1 = frame;
        final Interface interfaccia1 = interfaccia;

        setLayout(new GridLayout(0, 1));
        settimanePanel = new JPanel(new GridLayout(0, 3));
        tagliaPanel = new JPanel(new GridLayout(0, 3));
        magliettePanel = new JPanel(new GridLayout(0, 3));
        pitStopPanel = new JPanel(new GridLayout(0, 3));
        pagamentoPanel = new JPanel(new GridLayout(0, 3));
        entrataAnticipataPanel = new JPanel(new GridLayout(0, 3));
        pranzoPanel = new JPanel(new GridLayout(0, 3));
        buttonsPanel = new JPanel(new GridLayout(0, 5));

        settimanePanel.setBorder(BorderFactory.createTitledBorder("Settimane:"));
        tagliaPanel.setBorder(BorderFactory.createTitledBorder("Taglie:"));
        magliettePanel.setBorder(BorderFactory.createTitledBorder("Magliette:"));
        pitStopPanel.setBorder(BorderFactory.createTitledBorder("Pit stop:"));
        pagamentoPanel.setBorder(BorderFactory.createTitledBorder("Pagamento:"));
        entrataAnticipataPanel.setBorder(BorderFactory.createTitledBorder("Entrata anticipata:"));
        pranzoPanel.setBorder(BorderFactory.createTitledBorder("Pranzo:"));

        for (int i = 0; i < filtri.size(); i++)
            Collections.reverse(filtri.get(i));

        for (int i = 0; i < filtri.size(); i++)
            for (int k = 0; k < filtri.get(i).size(); k++)
                switch (i) {
                    case 0:
                        createAndShowCheckBox(filtri.get(i).get(k), settimanePanel, "settimanePanel");
                        break;
                    case 1:
                        createAndShowCheckBox(filtri.get(i).get(k), tagliaPanel, "tagliaPanel");
                        break;
                    case 2:
                        createAndShowCheckBox(filtri.get(i).get(k), magliettePanel, "magliettePanel");
                        break;
                    case 3:
                        createAndShowCheckBox(filtri.get(i).get(k), pitStopPanel, "pitStopPanel");
                        break;
                    case 4:
                        createAndShowCheckBox(filtri.get(i).get(k), pagamentoPanel, "pagamentoPanel");
                        break;
                    case 5:
                        createAndShowCheckBox(filtri.get(i).get(k), entrataAnticipataPanel, "entrataAnticipataPanel");
                        break;
                    case 6:
                        createAndShowCheckBox(filtri.get(i).get(k), pranzoPanel, "pranzoPanel");
                        break;
                }


        add(settimanePanel);
        add(tagliaPanel);
        add(magliettePanel);
        add(pitStopPanel);
        add(pagamentoPanel);
        add(entrataAnticipataPanel);
        add(pranzoPanel);

        resettaButton = new JButton("Reset");
        resettaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JCheckBox checkBox : iCheckBoxes)
                    checkBox.setSelected(false);
            }
        });

        salvaButton = new JButton("Salva");
        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(
                            new File("Data/Filter.data")));
                    writer.writeObject(new SaveArrayList(
                            FilterInterface.checkICheckBoxes(iCheckBoxes)));
                    writer.close();

                    frame1.dispose();
                    interfaccia1.getFrame().dispose();

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

        buttonsPanel.add(new JLabel());
        buttonsPanel.add(resettaButton);
        buttonsPanel.add(new JLabel());
        buttonsPanel.add(salvaButton);
        buttonsPanel.add(new JLabel());
        add(buttonsPanel);

        SaveArrayList saveArrayList = null;

        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(
                    new File("Data/Filter.data")));
            saveArrayList = (SaveArrayList) reader.readObject();
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (saveArrayList != null) {
            for (ICheckBox checkBox : iCheckBoxes)
                for (ICheckBox checkBox1 : saveArrayList.getiCheckBoxes())
                    if (checkBox.getId().equals(checkBox1.getId()))
                        checkBox.setSelected(checkBox1.isSelected());
        }
    }

    private void createAndShowCheckBox(String text, JPanel panel, String id) {
        ICheckBox checkBox = new ICheckBox(text, id + "_" + text);
        panel.add(checkBox);
        iCheckBoxes.add(checkBox);
    }

    private static ArrayList<ICheckBox> checkICheckBoxes(ArrayList<ICheckBox> iCheckBoxes) {
        ArrayList<ICheckBox> iCheckBoxArrayList = new ArrayList<>();

        for (ICheckBox iCheckBox : iCheckBoxes)
            if (iCheckBox.isSelected())
                iCheckBoxArrayList.add(iCheckBox);

        return iCheckBoxArrayList;
    }

    public ArrayList<ICheckBox> getiCheckBoxes() {
        return iCheckBoxes;
    }

    public static void createAndShowGUI(Interface interfaccia, ArrayList<ArrayList<String>> filtri) {
        JFrame frame = new JFrame("Gestione filtri");

        JComponent newContentPane = new FilterInterface(frame, interfaccia, filtri);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
