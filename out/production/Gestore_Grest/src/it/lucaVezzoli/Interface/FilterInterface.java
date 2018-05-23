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
import java.util.HashSet;
import java.util.Set;

public class FilterInterface extends JPanel {
    private JButton salvaButton;
    private JButton resettaButton;
    private JPanel buttonsPanel;
    private Set<ICheckBox> iCheckBoxes = new HashSet<>();

    public FilterInterface(JFrame frame, Interface interfaccia, ArrayList<ArrayList<String>> filtri, String type) {
        final JFrame frame1 = frame;
        final Interface interfaccia1 = interfaccia;

        if (type.equals("Bambini")) {
            setLayout(new GridLayout(0, 2));
            JPanel settimanePanel = new JPanel(new GridLayout(0, 3));
            JPanel tagliaPanel = new JPanel(new GridLayout(0, 3));
            JPanel magliettePanel = new JPanel(new GridLayout(0, 3));
            JPanel pitStopPanel = new JPanel(new GridLayout(0, 3));
            JPanel pagamentoPanel = new JPanel(new GridLayout(0, 3));
            JPanel entrataAnticipataPanel = new JPanel(new GridLayout(0, 3));
            JPanel pranzoPanel = new JPanel(new GridLayout(0, 3));
            buttonsPanel = new JPanel(new GridLayout(0, 5));

            settimanePanel.setBorder(BorderFactory.createTitledBorder("Settimane:"));
            tagliaPanel.setBorder(BorderFactory.createTitledBorder("Taglie:"));
            magliettePanel.setBorder(BorderFactory.createTitledBorder("Magliette:"));
            pitStopPanel.setBorder(BorderFactory.createTitledBorder("Pit stop:"));
            pagamentoPanel.setBorder(BorderFactory.createTitledBorder("Pagamento:"));
            entrataAnticipataPanel.setBorder(BorderFactory.createTitledBorder("Entrata anticipata:"));
            pranzoPanel.setBorder(BorderFactory.createTitledBorder("Pranzo:"));

            settimanePanel.setName("settimanePanel");
            tagliaPanel.setName("tagliaPanel");
            magliettePanel.setName("magliettePanel");
            pitStopPanel.setName("pitStopPanel");
            pagamentoPanel.setName("pagamentoPanel");
            entrataAnticipataPanel.setName("entrataAnticipataPanel");
            pranzoPanel.setName("pranzoPanel");

            for (int i = 0; i < filtri.size(); i++)
                Collections.reverse(filtri.get(i));

            for (int i = 0; i < filtri.size(); i++)
                for (int k = 0; k < filtri.get(i).size(); k++)
                    switch (i) {
                        case 0:
                            createCheckBox(filtri.get(i).get(k), settimanePanel.getName());
                            break;
                        case 1:
                            createCheckBox(filtri.get(i).get(k), tagliaPanel.getName());
                            break;
                        case 2:
                            createCheckBox(filtri.get(i).get(k), magliettePanel.getName());
                            break;
                        case 3:
                            createCheckBox(filtri.get(i).get(k), pitStopPanel.getName());
                            break;
                        case 4:
                            createCheckBox(filtri.get(i).get(k), pagamentoPanel.getName());
                            break;
                        case 5:
                            createCheckBox(filtri.get(i).get(k), entrataAnticipataPanel.getName());
                            break;
                        case 6:
                            createCheckBox(filtri.get(i).get(k), pranzoPanel.getName());
                            break;
                    }

            showCheckBox(settimanePanel);
            showCheckBox(tagliaPanel);
            showCheckBox(magliettePanel);
            showCheckBox(pitStopPanel);
            showCheckBox(pagamentoPanel);
            showCheckBox(entrataAnticipataPanel);
            showCheckBox(pranzoPanel);

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
                                new File("Data/FilterBambini.data")));
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
                        new File("Data/FilterBambini.data")));
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
        } else if (type.equals("Animatori")) {
            setLayout(new GridLayout(0, 2));

            JPanel responsabilePanel = new JPanel(new GridLayout(0, 3));
            JPanel grestPanel = new JPanel(new GridLayout(0, 3));
            JPanel minigrestPanel = new JPanel(new GridLayout(0, 3));
            JPanel campoAdoPanel = new JPanel(new GridLayout(0, 3));
            JPanel pit_stopPanel = new JPanel(new GridLayout(0, 3));
            JPanel slpaPanel = new JPanel(new GridLayout(0, 3));
            JPanel slPanel = new JPanel(new GridLayout(0, 3));
            buttonsPanel = new JPanel(new GridLayout(0, 5));

            responsabilePanel.setBorder(BorderFactory.createTitledBorder("Responsabile:"));
            grestPanel.setBorder(BorderFactory.createTitledBorder("Grest:"));
            minigrestPanel.setBorder(BorderFactory.createTitledBorder("Mini grest:"));
            campoAdoPanel.setBorder(BorderFactory.createTitledBorder("Campo ADO:"));
            pit_stopPanel.setBorder(BorderFactory.createTitledBorder("Pit stop:"));
            slpaPanel.setBorder(BorderFactory.createTitledBorder("San Luigi pre ADO:"));
            slPanel.setBorder(BorderFactory.createTitledBorder("San Luigi:"));

            responsabilePanel.setName("responsabilePanel");
            grestPanel.setName("grestPanel");
            minigrestPanel.setName("minigrestPanel");
            campoAdoPanel.setName("campoAdoPanel");
            pit_stopPanel.setName("pit-stopPanel");
            slpaPanel.setName("slpaPanel");
            slPanel.setName("slPanel");

            for (int i = 0; i < filtri.size(); i++)
                Collections.reverse(filtri.get(i));

            for (int i = 0; i < filtri.size(); i++)
                for (int k = 0; k < filtri.get(i).size(); k++)
                    switch (i) {
                        case 0:
                            createCheckBox(filtri.get(i).get(k), responsabilePanel.getName());
                            break;
                        case 1:
                            createCheckBox(filtri.get(i).get(k), grestPanel.getName());
                            break;
                        case 2:
                            createCheckBox(filtri.get(i).get(k), minigrestPanel.getName());
                            break;
                        case 3:
                            createCheckBox(filtri.get(i).get(k), campoAdoPanel.getName());
                            break;
                        case 4:
                            createCheckBox(filtri.get(i).get(k), pit_stopPanel.getName());
                            break;
                        case 5:
                            createCheckBox(filtri.get(i).get(k), slpaPanel.getName());
                            break;
                        case 6:
                            createCheckBox(filtri.get(i).get(k), slPanel.getName());
                            break;
                    }

            showCheckBox(responsabilePanel);
            showCheckBox(grestPanel);
            showCheckBox(minigrestPanel);
            showCheckBox(campoAdoPanel);
            showCheckBox(pit_stopPanel);
            showCheckBox(slpaPanel);
            showCheckBox(slPanel);

            add(responsabilePanel);
            add(grestPanel);
            add(minigrestPanel);
            add(campoAdoPanel);
            add(pit_stopPanel);
            add(slpaPanel);
            add(slPanel);

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
                                new File("Data/FilterAnimatori.data")));
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
                        new File("Data/FilterAnimatori.data")));
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
    }

    private void createCheckBox(String text, String panelName) {
        ICheckBox checkBox = new ICheckBox(text, panelName + "_" + text);
        iCheckBoxes.add(checkBox);
    }

    private void showCheckBox(JPanel panel) {
        for (ICheckBox checkBox : iCheckBoxes) {
            if (panel.getName().equals(checkBox.getId().split("_")[0])) {
                if (checkBox.getId().split("_")[1].equals("true"))
                    checkBox.setText("Sì");
                else if (checkBox.getId().split("_")[1].equals("false"))
                    checkBox.setText("No");
                panel.add(checkBox);
            }
        }
    }

    private static Set<ICheckBox> checkICheckBoxes(Set<ICheckBox> iCheckBoxes) {
        Set<ICheckBox> iCheckBoxArrayList = new HashSet<>();

        for (ICheckBox iCheckBox : iCheckBoxes)
            if (iCheckBox.isSelected())
                iCheckBoxArrayList.add(iCheckBox);

        return iCheckBoxArrayList;
    }

    public static void createAndShowGUI(Interface interfaccia, ArrayList<ArrayList<String>> filtri, String type) {
        JFrame frame = new JFrame("Gestione filtri");

        JComponent newContentPane = new FilterInterface(frame, interfaccia, filtri, type);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
