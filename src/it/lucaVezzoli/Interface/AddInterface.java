package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

public class AddInterface {
    private JPanel mainPanel;
    public JTextField cognomeJTF;
    public JTextField nomeJTF;
    public JTextField annoJTF;
    public JTextField impegniJTF;
    private JPanel upPanel;
    private JPanel middlePanel;
    private JPanel downPanel;
    private JButton salvaButton;
    private JButton annullaButton;
    public JTextField incontri_ABC;
    public JTextField incontri_ABC_totali;
    private JButton mButton;
    private JRadioButton grest_siJRB;
    private JRadioButton grest_forseJRB;
    private JRadioButton grest_noJRB;
    private JRadioButton grest_nsJRB;
    private JRadioButton minigrest_siJRB;
    private JRadioButton minigrest_forseJRB;
    private JRadioButton minigrest_noJRB;
    private JRadioButton minigrest_nsJRB;
    private JRadioButton ca_siJRB;
    private JRadioButton ca_forseJRB;
    private JRadioButton ca_noJRB;
    private JRadioButton ca_nsJRB;
    private JRadioButton ps_siJRB;
    private JRadioButton ps_forseJRB;
    private JRadioButton ps_noJRB;
    private JRadioButton ps_nsJRB;
    private JRadioButton slpa_siJRB;
    private JRadioButton slpa_forseJRB;
    private JRadioButton slpa_noJRB;
    private JRadioButton slpa_nsJRB;
    private JRadioButton sl_siJRB;
    private JRadioButton sl_forseJRB;
    private JRadioButton sl_noJRB;
    private JRadioButton sl_nsJRB;
    private JPanel middlePanel2;
    private JTextField attivitaJTF;
    private JTextField animazioneJTF;
    private JTextField gigantiJTF;
    private JTextField giochiJTF;
    private JTextField festafinaleJTF;
    private JTextField preghieraJTF;
    private JTextField scenografiaJTF;
    private JTextField segreteriaJTF;
    private JTextField storiaJTF;
    private JTable tableAE;
    private JScrollPane scrollAE;
    private JTable tableGP;
    private JScrollPane scrollGP;
    private AnimazioneEstiva[] animazioniEstive;
    private GruppiPreferenza[] gruppiPreferenza;
    private ButtonGroup grestGroup = new ButtonGroup();
    private ButtonGroup miniGrestGroup = new ButtonGroup();
    private ButtonGroup caGroup = new ButtonGroup();
    private ButtonGroup psGroup = new ButtonGroup();
    private ButtonGroup slpaGroup = new ButtonGroup();
    private ButtonGroup slGroup = new ButtonGroup();

    public AddInterface(final JFrame frame, final Interface interfaccia, final int id) {
        upPanel.setBorder(BorderFactory.createTitledBorder("Dati:"));
        middlePanel.setBorder(BorderFactory.createTitledBorder("Animazione estiva:"));
        middlePanel2.setBorder(BorderFactory.createTitledBorder("Gruppi preferenza:"));

        grest_nsJRB.setSelected(true);
        minigrest_nsJRB.setSelected(true);
        ca_nsJRB.setSelected(true);
        ps_nsJRB.setSelected(true);
        slpa_nsJRB.setSelected(true);
        sl_nsJRB.setSelected(true);

        grestGroup.add(grest_siJRB);
        grestGroup.add(grest_forseJRB);
        grestGroup.add(grest_noJRB);
        grestGroup.add(grest_nsJRB);

        miniGrestGroup.add(minigrest_siJRB);
        miniGrestGroup.add(minigrest_forseJRB);
        miniGrestGroup.add(minigrest_noJRB);
        miniGrestGroup.add(minigrest_nsJRB);

        caGroup.add(ca_siJRB);
        caGroup.add(ca_forseJRB);
        caGroup.add(ca_noJRB);
        caGroup.add(ca_nsJRB);

        psGroup.add(ps_siJRB);
        psGroup.add(ps_forseJRB);
        psGroup.add(ps_noJRB);
        psGroup.add(ps_nsJRB);

        slpaGroup.add(slpa_siJRB);
        slpaGroup.add(slpa_forseJRB);
        slpaGroup.add(slpa_noJRB);
        slpaGroup.add(slpa_nsJRB);

        slGroup.add(sl_siJRB);
        slGroup.add(sl_forseJRB);
        slGroup.add(sl_noJRB);
        slGroup.add(sl_nsJRB);

        Animatore animatore = null;

        if (id != -1) {
            try {
                ResultSet resultSet = interfaccia.getConnection().createStatement()
                        .executeQuery("SELECT * FROM Animatori_" +
                                interfaccia.getDbConnection().getDbTable() + " WHERE ID='" + id + "';");

                while (resultSet.next()) {
                    animatore =
                            (Animatore) StartProgramInterface.fromString(resultSet.getString("Animatore"));
                }

                if (animatore != null) {
                    cognomeJTF.setText(animatore.getCognome());
                    nomeJTF.setText(animatore.getNome());
                    annoJTF.setText("" + animatore.getAnno());
                    impegniJTF.setText(animatore.getImpegni());
                    incontri_ABC.setText(Integer.toString(animatore.getAbc_incontri_partecipato()));
                    incontri_ABC_totali.setText(Integer.toString(animatore.getAbc_incontri_totali()));

                    grestGroup.clearSelection();
                    miniGrestGroup.clearSelection();
                    caGroup.clearSelection();
                    psGroup.clearSelection();
                    slpaGroup.clearSelection();
                    slGroup.clearSelection();

                    switch (animatore.getAnimazioneEstiva()[0].getPartecipazione()) {
                        case "Si":
                            grest_siJRB.setSelected(true);
                            break;
                        case "Forse":
                            grest_forseJRB.setSelected(true);
                            break;
                        case "No":
                            grest_noJRB.setSelected(true);
                            break;
                        case "Non specificato":
                            grest_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (animatore.getAnimazioneEstiva()[1].getPartecipazione()) {
                        case "Si":
                            minigrest_siJRB.setSelected(true);
                            break;
                        case "Forse":
                            minigrest_forseJRB.setSelected(true);
                            break;
                        case "No":
                            minigrest_noJRB.setSelected(true);
                            break;
                        case "Non specificato":
                            minigrest_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (animatore.getAnimazioneEstiva()[2].getPartecipazione()) {
                        case "Si":
                            ca_siJRB.setSelected(true);
                            break;
                        case "Forse":
                            ca_forseJRB.setSelected(true);
                            break;
                        case "No":
                            ca_noJRB.setSelected(true);
                            break;
                        case "Non specificato":
                            ca_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (animatore.getAnimazioneEstiva()[3].getPartecipazione()) {
                        case "Si":
                            ps_siJRB.setSelected(true);
                            break;
                        case "Forse":
                            ps_forseJRB.setSelected(true);
                            break;
                        case "No":
                            ps_noJRB.setSelected(true);
                            break;
                        case "Non specificato":
                            ps_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (animatore.getAnimazioneEstiva()[4].getPartecipazione()) {
                        case "Si":
                            slpa_siJRB.setSelected(true);
                            break;
                        case "Forse":
                            slpa_forseJRB.setSelected(true);
                            break;
                        case "No":
                            slpa_noJRB.setSelected(true);
                            break;
                        case "Non specificato":
                            slpa_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (animatore.getAnimazioneEstiva()[5].getPartecipazione()) {
                        case "Si":
                            sl_siJRB.setSelected(true);
                            break;
                        case "Forse":
                            sl_forseJRB.setSelected(true);
                            break;
                        case "No":
                            sl_noJRB.setSelected(true);
                            break;
                        case "Non specificato":
                            sl_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    attivitaJTF.setText(animatore.getGruppiPreferenza()[0].getVoto());
                    animazioneJTF.setText(animatore.getGruppiPreferenza()[1].getVoto());
                    gigantiJTF.setText(animatore.getGruppiPreferenza()[2].getVoto());
                    giochiJTF.setText(animatore.getGruppiPreferenza()[3].getVoto());
                    festafinaleJTF.setText(animatore.getGruppiPreferenza()[4].getVoto());
                    preghieraJTF.setText(animatore.getGruppiPreferenza()[5].getVoto());
                    scenografiaJTF.setText(animatore.getGruppiPreferenza()[6].getVoto());
                    segreteriaJTF.setText(animatore.getGruppiPreferenza()[7].getVoto());
                    storiaJTF.setText(animatore.getGruppiPreferenza()[8].getVoto());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        final JFrame frame1 = frame;
        final Interface interfaccia1 = interfaccia;

        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
            }
        });

        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Border defaultBorder = cognomeJTF.getBorder();
                int anno = -1;
                int incontri = -1;
                int incontri_tot = Integer.parseInt(incontri_ABC_totali.getText());
                String attivita = null;
                String animazione = null;
                String giganti = null;
                String giochi = null;
                String festaFinale = null;
                String preghiera = null;
                String scenografia = null;
                String segreteria = null;
                String storia = null;

                try {
                    anno = Integer.parseInt(annoJTF.getText());
                    annoJTF.setBorder(defaultBorder);
                } catch (Exception ex) {
                    annoJTF.setBorder(new LineBorder(Color.RED, 2));
                    ex.printStackTrace();
                }

                try {
                    incontri = Integer.parseInt(incontri_ABC.getText());
                    incontri_ABC.setBorder(defaultBorder);
                } catch (Exception ex) {
                    incontri_ABC.setBorder(new LineBorder(Color.RED, 2));
                    ex.printStackTrace();
                }

                if (!attivitaJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(attivitaJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value attivita");

                        attivitaJTF.setBorder(defaultBorder);
                        attivita = attivitaJTF.getText();
                    } catch (Exception ex) {
                        attivitaJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    attivitaJTF.setBorder(defaultBorder);
                    attivita = "";
                }

                if (!animazioneJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(animazioneJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value animazione");

                        animazioneJTF.setBorder(defaultBorder);
                        animazione = animazioneJTF.getText();
                    } catch (Exception ex) {
                        animazioneJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    animazioneJTF.setBorder(defaultBorder);
                    animazione = "";
                }

                if (!gigantiJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(gigantiJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value giganti");

                        gigantiJTF.setBorder(defaultBorder);
                        giganti = gigantiJTF.getText();
                    } catch (Exception ex) {
                        gigantiJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    gigantiJTF.setBorder(defaultBorder);
                    giganti = "";
                }

                if (!giochiJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(giochiJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value giochi");

                        giochiJTF.setBorder(defaultBorder);
                        giochi = giochiJTF.getText();
                    } catch (Exception ex) {
                        giochiJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    giochiJTF.setBorder(defaultBorder);
                    giochi = "";
                }

                if (!festafinaleJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(festafinaleJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value festafinale");

                        festafinaleJTF.setBorder(defaultBorder);
                        festaFinale = festafinaleJTF.getText();
                    } catch (Exception ex) {
                        festafinaleJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    festafinaleJTF.setBorder(defaultBorder);
                    festaFinale = "";
                }

                if (!preghieraJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(preghieraJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value preghiera");

                        preghieraJTF.setBorder(defaultBorder);
                        preghiera = preghieraJTF.getText();
                    } catch (Exception ex) {
                        preghieraJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    preghieraJTF.setBorder(defaultBorder);
                    preghiera = "";
                }

                if (!scenografiaJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(scenografiaJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value scenografia");

                        scenografiaJTF.setBorder(defaultBorder);
                        scenografia = scenografiaJTF.getText();
                    } catch (Exception ex) {
                        scenografiaJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    scenografiaJTF.setBorder(defaultBorder);
                    scenografia = "";
                }

                if (!segreteriaJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(segreteriaJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value segreteria");

                        segreteriaJTF.setBorder(defaultBorder);
                        segreteria = segreteriaJTF.getText();
                    } catch (Exception ex) {
                        segreteriaJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    segreteriaJTF.setBorder(defaultBorder);
                    segreteria = "";
                }

                if (!storiaJTF.getText().equals("")) {
                    try {
                        int variabileInutile = Integer.parseInt(storiaJTF.getText());

                        if (variabileInutile < 0 || variabileInutile > 10)
                            throw new Exception("Wrong value storia");

                        storiaJTF.setBorder(defaultBorder);
                        storia = storiaJTF.getText();
                    } catch (Exception ex) {
                        storiaJTF.setBorder(new LineBorder(Color.RED, 2));
                        ex.printStackTrace();
                    }
                } else {
                    storiaJTF.setBorder(defaultBorder);
                    storia = "";
                }

                animazioniEstive = new AnimazioneEstiva[6];

                animazioniEstive[0] = new AnimazioneEstiva("Grest", getSelectedButtonText(grestGroup));
                animazioniEstive[1] = new AnimazioneEstiva("MiniGrest", getSelectedButtonText(miniGrestGroup));
                animazioniEstive[2] = new AnimazioneEstiva("CampoADO", getSelectedButtonText(caGroup));
                animazioniEstive[3] = new AnimazioneEstiva("PitStop", getSelectedButtonText(psGroup));
                animazioniEstive[4] = new AnimazioneEstiva("SanLuigiPreADO", getSelectedButtonText(slpaGroup));
                animazioniEstive[5] = new AnimazioneEstiva("SanLuigi", getSelectedButtonText(slGroup));

                boolean[] check = new boolean[9];
                gruppiPreferenza = new GruppiPreferenza[9];

                gruppiPreferenza[0] = new GruppiPreferenza("Attivita", attivita);
                gruppiPreferenza[1] = new GruppiPreferenza("Animazione", animazione);
                gruppiPreferenza[2] = new GruppiPreferenza("Giganti", giganti);
                gruppiPreferenza[3] = new GruppiPreferenza("Giochi", giochi);
                gruppiPreferenza[4] = new GruppiPreferenza("FestaFinale", festaFinale);
                gruppiPreferenza[5] = new GruppiPreferenza("Preghiera", preghiera);
                gruppiPreferenza[6] = new GruppiPreferenza("Scenografia", scenografia);
                gruppiPreferenza[7] = new GruppiPreferenza("Segreteria", segreteria);
                gruppiPreferenza[8] = new GruppiPreferenza("Storia", storia);

                for (int i = 0; i < gruppiPreferenza.length; i++) {
                    if (gruppiPreferenza[i].getVoto().equals(""))
                        check[i] = true;
                }

                if (anno != -1 && incontri != -1 && incontri_tot != -1) {
                    Animatore animatore =
                            new Animatore(cognomeJTF.getText(), nomeJTF.getText(),
                                    anno, incontri, incontri_tot, animazioniEstive,
                                    impegniJTF.getText(),
                                    gruppiPreferenza,
                                    AddInterface.checkArray(check));

                    try {
                        Connection connection = DriverManager.getConnection(
                                "jdbc:mysql://" + interfaccia1.getDbConnection().getDbHost() + "/"
                                        + interfaccia1.getDbConnection().getDbName()
                                        + "?user="
                                        + interfaccia1.getDbConnection().getDbUser() + "&password="
                                        + interfaccia1.getDbConnection().getDbPassword());

                        if (id != -1) {
                            PreparedStatement preparedstatement =
                                    connection.prepareStatement("DELETE FROM Animatori_" +
                                            interfaccia1.getDbConnection().getDbTable() +
                                            " WHERE ID='" + id + "';");

                            preparedstatement.execute();
                        }

                        PreparedStatement preparedstatement =
                                connection.prepareStatement("INSERT INTO Animatori_"
                                        + interfaccia1.getDbConnection().getDbTable() +
                                        " (cognomenome, animatore)" +
                                        " values (?,?)");

                        preparedstatement.setString(1, animatore.getCognome() + " " + animatore.getNome()
                                + " " + animatore.getAnno());
                        preparedstatement.setString(2, AddInterface.toString(animatore));
                        preparedstatement.execute();

                        interfaccia1.getFrame().dispose();
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
            }
        });
        mButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                NewYearInterface.createAndShowGUI(interfaccia1);
            }
        });

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Data/AnniABC.data")));
            incontri_ABC_totali.setText(reader.readLine());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public static boolean checkArray(boolean[] array) {
        for (boolean element : array)
            if (!element)
                return false;

        return true;
    }

    private static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return DatatypeConverter.printBase64Binary(baos.toByteArray());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI(Interface interfaccia, int id) {
        JFrame frame = new JFrame("Aggiunta animatore");

        JComponent newContentPane = new AddInterface(frame, interfaccia, id).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
