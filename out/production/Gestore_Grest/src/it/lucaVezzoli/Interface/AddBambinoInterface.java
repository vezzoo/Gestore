package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

public class AddBambinoInterface {
    private JPanel mainPanel;
    private JButton salvaButton;
    private JButton annullaButton;
    private JTextField cognomeJTF;
    private JTextField nomeJTF;
    private JTextField giornoJTF;
    private JTextField meseJTF;
    private JTextField annoJTF;
    private JTextField indirizzoJTF;
    private JTextField classeJTF;
    private JTextField telefono1JTF;
    private JTextField telefono2JTF;
    private JTextField preferenzaJTF;
    private JTextField giornoSaldoJTF;
    private JTextField meseSaldoJTF;
    private JTextField annoSaldoJTF;
    private JRadioButton grest_siJRB;
    private JRadioButton grest_noJRB;
    private JRadioButton settimane_1JRB;
    private JRadioButton settimane_3JRB;
    private JRadioButton settimane_2JRB;
    private JRadioButton grest_nsJRB;
    private JRadioButton settimane_nsJRB;
    private JRadioButton taglia_56JRB;
    private JRadioButton taglia_78JRB;
    private JRadioButton taglia_911JRB;
    private JRadioButton taglia_1213JRB;
    private JRadioButton taglia_1415JRB;
    private JRadioButton taglia_nsJRB;
    private JRadioButton ps_siJRB;
    private JRadioButton ps_noJRB;
    private JRadioButton ps_nsJRB;
    private JRadioButton pagamento_nsJRB;
    private JRadioButton pagamento_accontoJRB;
    private JRadioButton pagamento_saldoJRB;
    private JRadioButton ea_nsJRB;
    private JRadioButton ea_noJRB;
    private JRadioButton ea_siJRB;
    private JTextField maglietteJTF;
    private JRadioButton pranzo_siJRB;
    private JRadioButton pranzo_noJRB;
    private JRadioButton pranzo_nsJRB;
    private ButtonGroup grestGroup = new ButtonGroup();
    private ButtonGroup settimaneGroup = new ButtonGroup();
    private ButtonGroup tagliaGroup = new ButtonGroup();
    private ButtonGroup psGroup = new ButtonGroup();
    private ButtonGroup pagamentoGroup = new ButtonGroup();
    private ButtonGroup eaGroup = new ButtonGroup();
    private ButtonGroup pranzoGroup = new ButtonGroup();

    public AddBambinoInterface(JFrame frame, Interface interfaccia, final int id) {
        final Border defaultBorder = giornoJTF.getBorder();
        final Interface interfaccia1 = interfaccia;
        final JFrame frame1 = frame;

        grestGroup.add(grest_siJRB);
        grestGroup.add(grest_noJRB);
        grestGroup.add(grest_nsJRB);

        settimaneGroup.add(settimane_1JRB);
        settimaneGroup.add(settimane_2JRB);
        settimaneGroup.add(settimane_3JRB);
        settimaneGroup.add(settimane_nsJRB);

        tagliaGroup.add(taglia_56JRB);
        tagliaGroup.add(taglia_78JRB);
        tagliaGroup.add(taglia_911JRB);
        tagliaGroup.add(taglia_1213JRB);
        tagliaGroup.add(taglia_1415JRB);
        tagliaGroup.add(taglia_nsJRB);

        psGroup.add(ps_siJRB);
        psGroup.add(ps_noJRB);
        psGroup.add(ps_nsJRB);

        pagamentoGroup.add(pagamento_saldoJRB);
        pagamentoGroup.add(pagamento_accontoJRB);
        pagamentoGroup.add(pagamento_nsJRB);

        eaGroup.add(ea_siJRB);
        eaGroup.add(ea_noJRB);
        eaGroup.add(ea_nsJRB);

        pranzoGroup.add(pranzo_siJRB);
        pranzoGroup.add(pranzo_noJRB);
        pranzoGroup.add(pranzo_nsJRB);

        Bambino bambino = null;

        if (id != -1) {
            try {
                ResultSet resultSet = interfaccia.getConnection().createStatement()
                        .executeQuery("SELECT * FROM Bambini_" +
                                interfaccia.getDbConnection().getDbTable() + " WHERE ID='" + id + "';");

                while (resultSet.next()) {
                    bambino =
                            (Bambino) StartProgramInterface.fromString(resultSet.getString("Bambino"));
                }

                if (bambino != null) {
                    cognomeJTF.setText(bambino.getCognome());
                    nomeJTF.setText(bambino.getNome());
                    giornoJTF.setText(Integer.toString(bambino.getDataDiNascita().getGiorno()));
                    meseJTF.setText(Integer.toString(bambino.getDataDiNascita().getMese()));
                    annoJTF.setText(Integer.toString(bambino.getDataDiNascita().getAnno()));
                    classeJTF.setText(bambino.getClasse());
                    indirizzoJTF.setText(bambino.getIndirizzo());
                    telefono1JTF.setText(bambino.getTelefono1());
                    telefono2JTF.setText(bambino.getTelefono2());
                    preferenzaJTF.setText(bambino.getPreferenza());
                    maglietteJTF.setText(bambino.getMaglietta());

                    grestGroup.clearSelection();
                    settimaneGroup.clearSelection();
                    tagliaGroup.clearSelection();
                    psGroup.clearSelection();
                    pagamentoGroup.clearSelection();
                    eaGroup.clearSelection();
                    pranzoGroup.clearSelection();

                    switch (bambino.isGrest()) {
                        case "Si":
                            grest_siJRB.setSelected(true);
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

                    switch (bambino.getSettimane()) {
                        case "1":
                            settimane_1JRB.setSelected(true);
                            break;
                        case "2":
                            settimane_2JRB.setSelected(true);
                            break;
                        case "3":
                            settimane_3JRB.setSelected(true);
                            break;
                        case "Non specificato":
                            settimane_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (bambino.getTaglia()) {
                        case "5 - 6":
                            taglia_56JRB.setSelected(true);
                            break;
                        case "7 - 8":
                            taglia_78JRB.setSelected(true);
                            break;
                        case "9 - 11":
                            taglia_911JRB.setSelected(true);
                            break;
                        case "12 - 13":
                            taglia_1213JRB.setSelected(true);
                            break;
                        case "M (14 - 15)":
                            taglia_1415JRB.setSelected(true);
                            break;
                        case "Non specificato":
                            taglia_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (bambino.isPit_stop()) {
                        case "Si":
                            ps_siJRB.setSelected(true);
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

                    switch (bambino.getPagamento()) {
                        case "Acconto":
                            pagamento_accontoJRB.setSelected(true);
                            break;
                        case "Saldo":
                            pagamento_saldoJRB.setSelected(true);
                            giornoSaldoJTF.setEnabled(true);
                            meseSaldoJTF.setEnabled(true);
                            annoSaldoJTF.setEnabled(true);
                            giornoSaldoJTF.setText(Integer.toString(bambino.getDataSaldo().getGiorno()));
                            meseSaldoJTF.setText(Integer.toString(bambino.getDataSaldo().getMese()));
                            annoSaldoJTF.setText(Integer.toString(bambino.getDataSaldo().getAnno()));
                            break;
                        case "Non specificato":
                            pagamento_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (bambino.isEntrataAnticipata()) {
                        case "Si":
                            ea_siJRB.setSelected(true);
                            break;
                        case "No":
                            ea_noJRB.setSelected(true);
                            break;
                        case "Non specificato":
                            ea_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }

                    switch (bambino.isPranzo()) {
                        case "Si":
                            pranzo_siJRB.setSelected(true);
                            break;
                        case "No":
                            pranzo_noJRB.setSelected(true);
                            break;
                        case "Non specificato":
                            pranzo_nsJRB.setSelected(true);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
            }
        });

        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salva(id, interfaccia1, frame1, defaultBorder);
            }
        });
        salvaButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n')
                    salva(id, interfaccia1, frame1, defaultBorder);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        pagamento_saldoJRB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                giornoSaldoJTF.setEnabled(pagamento_saldoJRB.isSelected());
                giornoSaldoJTF.setBorder(defaultBorder);
                meseSaldoJTF.setEnabled(pagamento_saldoJRB.isSelected());
                meseSaldoJTF.setBorder(defaultBorder);
                annoSaldoJTF.setEnabled(pagamento_saldoJRB.isSelected());
                annoSaldoJTF.setBorder(defaultBorder);
            }
        });
    }

    private void salva(int id, Interface interfaccia1, JFrame frame1, Border defaultBorder) {
        int giorno = -1;
        int mese = -1;
        int anno = -1;
        int giornoSaldo = -1;
        int meseSaldo = -1;
        int annoSaldo = -1;

        String magliette = null;

        try {
            giorno = Integer.parseInt(giornoJTF.getText());
            giornoJTF.setBorder(defaultBorder);
        } catch (Exception ex) {
            giornoJTF.setBorder(new LineBorder(Color.RED, 2));
            ex.printStackTrace();
        }

        try {
            mese = Integer.parseInt(meseJTF.getText());
            meseJTF.setBorder(defaultBorder);
        } catch (Exception ex) {
            meseJTF.setBorder(new LineBorder(Color.RED, 2));
            ex.printStackTrace();
        }

        try {
            anno = Integer.parseInt(annoJTF.getText());
            annoJTF.setBorder(defaultBorder);
        } catch (Exception ex) {
            annoJTF.setBorder(new LineBorder(Color.RED, 2));
            ex.printStackTrace();
        }

        if (giornoSaldoJTF.isEnabled()) {
            try {
                giornoSaldo = Integer.parseInt(giornoSaldoJTF.getText());
                giornoSaldoJTF.setBorder(defaultBorder);
            } catch (Exception ex) {
                giornoSaldoJTF.setBorder(new LineBorder(Color.RED, 2));
                ex.printStackTrace();
            }

            try {
                meseSaldo = Integer.parseInt(meseSaldoJTF.getText());
                meseSaldoJTF.setBorder(defaultBorder);
            } catch (Exception ex) {
                meseSaldoJTF.setBorder(new LineBorder(Color.RED, 2));
                ex.printStackTrace();
            }

            try {
                annoSaldo = Integer.parseInt(annoSaldoJTF.getText());
                annoSaldoJTF.setBorder(defaultBorder);
            } catch (Exception ex) {
                annoSaldoJTF.setBorder(new LineBorder(Color.RED, 2));
                ex.printStackTrace();
            }
        }

        if (!maglietteJTF.getText().equals("")) {
            try {
                int variabileInutile = Integer.parseInt(maglietteJTF.getText());

                if (variabileInutile < 0)
                    throw new Exception("Wrong value storia");

                maglietteJTF.setBorder(defaultBorder);
                magliette = maglietteJTF.getText();
            } catch (Exception ex) {
                maglietteJTF.setBorder(new LineBorder(Color.RED, 2));
                ex.printStackTrace();
            }
        } else {
            maglietteJTF.setBorder(defaultBorder);
            magliette = "";
        }

        Bambino bambino = null;

        if (giorno != -1 && mese != -1 && anno != -1
                && giornoSaldo != -1 && meseSaldo != -1
                && annoSaldo != -1 && magliette != null) {

            bambino = new Bambino(cognomeJTF.getText(), nomeJTF.getText(),
                    new DataPersonalizzata(giorno, mese, anno), classeJTF.getText(),
                    indirizzoJTF.getText(), telefono1JTF.getText(), telefono2JTF.getText(),
                    preferenzaJTF.getText(), getSelectedButtonText(grestGroup),
                    getSelectedButtonText(settimaneGroup), getSelectedButtonText(tagliaGroup),
                    getSelectedButtonText(psGroup), magliette,
                    getSelectedButtonText(pagamentoGroup),
                    new DataPersonalizzata(giornoSaldo, meseSaldo, annoSaldo),
                    getSelectedButtonText(eaGroup), getSelectedButtonText(pranzoGroup), "");
        } else if (giorno != -1 && mese != -1 && anno != -1
                && !giornoSaldoJTF.isEnabled() && magliette != null) {
            bambino = new Bambino(cognomeJTF.getText(), nomeJTF.getText(),
                    new DataPersonalizzata(giorno, mese, anno), classeJTF.getText(),
                    indirizzoJTF.getText(), telefono1JTF.getText(), telefono2JTF.getText(),
                    preferenzaJTF.getText(), getSelectedButtonText(grestGroup),
                    getSelectedButtonText(settimaneGroup), getSelectedButtonText(tagliaGroup),
                    getSelectedButtonText(psGroup), magliette,
                    getSelectedButtonText(pagamentoGroup),
                    getSelectedButtonText(eaGroup), getSelectedButtonText(pranzoGroup), "");
        }

        if (bambino != null) {
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://"
                                + interfaccia1.getDbConnection().getDbHost() + "/"
                                + interfaccia1.getDbConnection().getDbName()
                                + "?user="
                                + interfaccia1.getDbConnection().getDbUser() + "&password="
                                + interfaccia1.getDbConnection().getDbPassword());

                if (id != -1) {
                    PreparedStatement preparedstatement =
                            connection.prepareStatement("DELETE FROM Bambini_" +
                                    interfaccia1.getDbConnection().getDbTable() +
                                    " WHERE ID='" + id + "';");

                    preparedstatement.execute();
                }

                PreparedStatement preparedstatement =
                        connection.prepareStatement("INSERT INTO Bambini_"
                                + interfaccia1.getDbConnection().getDbTable() +
                                " (cognomenome, bambino)" +
                                " values (?,?)");

                preparedstatement.setString(1,
                        bambino.getCognome() + " " + bambino.getNome()
                                + " " + bambino.getAnno());
                preparedstatement.setString(2, AddBambinoInterface.toString(bambino));
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

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
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
        JFrame frame = new JFrame("Aggiunta bambino");

        JComponent newContentPane = new AddBambinoInterface(frame, interfaccia, id).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
