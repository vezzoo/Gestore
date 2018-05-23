package it.lucaVezzoli.Interface;

import com.sun.org.apache.xpath.internal.operations.Bool;
import it.lucaVezzoli.Application.*;
import sun.plugin.com.AmbientProperty;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Interface extends JPanel {
    private JPanel upPanel;
    private JPanel downPanel;
    private JPanel tabAnimatoriPanel;
    private JPanel tabAnimatoriPanelUp;
    private JPanel tableAnimatoriStampa;
    private JPanel tabBambiniPanel;
    private JPanel tabBambiniPanelUp;
    private JPanel tableBambiniStampa;
    private JPanel downMiddlePanelInA;
    private JPanel downMiddlePanelInB;
    private JTable tableAnimatori;
    private JTable tableBambini;
    private JScrollPane scrollA;
    private JScrollPane scrollB;
    private JMenuBar menuBar;
    private JMenu impostazioni;
    private JMenu filters;
    private JMenuItem connettivita;
    private JMenuItem gestioneAccount;
    private JMenuItem filtriAttivi;
    private JLabel filtriAnimatoriAttivi;
    private JLabel filtriBambiniAttivi;
    private JMenu showFilters;
    private JMenuItem animatoriShowFilters;
    private JMenuItem bambiniShowFilters;
    private JCheckBoxMenuItem showPreferenze;
    private JCheckBoxMenuItem showMagliette;
    private JCheckBoxMenuItem showTaglia;
    private JMenuItem showIP;
    private JMenuItem exportToCSV;
    private JMenu visualizzaAnni;
    private JTextField cercaAnimatori;
    private JTextField cercaBambini;
    private DBConnection dbConnection;
    private Account account;
    private JLabel spazioL;
    private JLabel spazioR;
    private JLabel spazioLInA;
    private JLabel spazioLInB;
    private JLabel spazioRInA;
    private JLabel spazioRInB;
    private JButton newRowA;
    private JButton newRowB;
    private Connection connection;
    private JFrame frame;
    private ArrayList<Animatore> animatori = new ArrayList<>();
    private ArrayList<Bambino> bambini = new ArrayList<>();
    private JLabel dbNameLBL1;
    private JLabel dbNameLBL2;
    private ImageIcon icon;
    private JTabbedPane tab;
    private ArrayList<JMenuItem> data = new ArrayList<>();
    private JButton stampaAnimatori;
    private JButton stampaBambini;
    private ArrayList<ArrayList<String>> filtri = new ArrayList<>();
    private ArrayList<ArrayList<String>> filtriAnimatori = new ArrayList<>();
    private ArrayList<String> filtroSettimane = new ArrayList<>();
    private ArrayList<String> filtroTaglia = new ArrayList<>();
    private ArrayList<String> filtroPitStop = new ArrayList<>();
    private ArrayList<String> filtroMagliette = new ArrayList<>();
    private ArrayList<String> filtroPagamento = new ArrayList<>();
    private ArrayList<String> filtroEntrataAnticipata = new ArrayList<>();
    private ArrayList<String> filtroPranzo = new ArrayList<>();
    private ArrayList<String> filtrogrest = new ArrayList<>();
    private ArrayList<String> filtrominigrest = new ArrayList<>();
    private ArrayList<String> filtrocampoADO = new ArrayList<>();
    private ArrayList<String> filtroPit_Stop = new ArrayList<>();
    private ArrayList<String> filtroslpa = new ArrayList<>();
    private ArrayList<String> filtrosl = new ArrayList<>();
    private ArrayList<String> filtroResponsabile = new ArrayList<>();
    private StartProgramInterface startProgramInterface;
    private int indexCognome = 1;
    private int indexNome = 2;
    private int indexAnno = 3;

    public Interface(JFrame frame2, Connection connection1, DBConnection dbConnection1, Account account1,
                     StartProgramInterface startProgramInterface) {
        showPreferenze = new JCheckBoxMenuItem("Mostra preferenze");
        showPreferenze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAssets("Preferenze", showPreferenze);
            }
        });

        showMagliette = new JCheckBoxMenuItem("Mostra magliette");
        showMagliette.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAssets("Magliette", showMagliette);
            }
        });

        showTaglia = new JCheckBoxMenuItem("Mostra taglia");
        showTaglia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAssets("Taglia", showTaglia);
            }
        });

        showPreferenze.setSelected(loadAssets("Preferenze"));
        showMagliette.setSelected(loadAssets("Magliette"));
        showTaglia.setSelected(loadAssets("Taglia"));

        animatori.clear();
        bambini.clear();

        this.frame = frame2;
        this.connection = connection1;
        this.dbConnection = dbConnection1;
        this.account = account1;
        this.tableAnimatori = loadData("Animatori");
        this.tableBambini = loadData("Bambini");
        this.scrollA = new JScrollPane(tableAnimatori);
        this.scrollB = new JScrollPane(tableBambini);
        this.startProgramInterface = startProgramInterface;

        tableAnimatori.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2)
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Animatore animatore = null;
                            int selectedRow = tableAnimatori.getSelectedRow();

                            int id = Integer.parseInt(((String) tableAnimatori.getValueAt(selectedRow, 4))
                                    .replace("(", "").replace(")", "").replace("   ", ""));

                            for (Animatore animatore1 : animatori) {
                                if (animatore1.getId() == id) {
                                    animatore = animatore1;
                                    break;
                                }
                            }

                            if (animatore != null)
                                ShowInterface.createAndShowGUI(
                                        animatore, Interface.this);
                        }
                    });
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        tableAnimatori.setComponentPopupMenu(
                new PopupMenu(Interface.this, "animatore"));

        tableBambini.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2)
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Bambino bambino = null;
                            int selectedRow = tableBambini.getSelectedRow();

                            int id = Integer.parseInt(((String) tableBambini.getValueAt(selectedRow, tableBambini.getColumnCount() - 1))
                                    .replace("(", "").replace(")", "").replace("   ", ""));

                            for (Bambino bambino1 : bambini) {
                                if (bambino1.getId() == id) {
                                    bambino = bambino1;
                                    break;
                                }
                            }

                            if (bambino != null)
                                ShowBambinoInterface.createAndShowGUI(
                                        bambino, Interface.this);
                        }
                    });
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        tableBambini.setComponentPopupMenu(
                new PopupMenu(Interface.this, "bambino"));

        setLayout(new BorderLayout());
        upPanel = new JPanel(new BorderLayout());
        downPanel = new JPanel(new BorderLayout());
        tabAnimatoriPanel = new JPanel(new BorderLayout());
        tabAnimatoriPanelUp = new JPanel(new GridLayout(1, 3));
        tableAnimatoriStampa = new JPanel(new BorderLayout());
        tabBambiniPanel = new JPanel(new BorderLayout());
        tabBambiniPanelUp = new JPanel(new GridLayout(1, 3));
        tableBambiniStampa = new JPanel(new BorderLayout());
        downMiddlePanelInA = new JPanel(new FlowLayout());
        downMiddlePanelInB = new JPanel(new FlowLayout());

        tab = new JTabbedPane();
        icon = new ImageIcon("Data/document16.png");

        spazioL = new JLabel("                                       ");
        spazioR = new JLabel("                                       ");

        downPanel.add(spazioL, BorderLayout.LINE_START);
        downPanel.add(spazioR, BorderLayout.LINE_END);
        downPanel.add(tab, BorderLayout.CENTER);

        menuBar = new JMenuBar();
        impostazioni = new JMenu("Impostazioni");
        impostazioni.setMnemonic(KeyEvent.VK_I);

        connettivita = new JMenuItem("Connettivita");
        connettivita.setMnemonic(KeyEvent.VK_O);
        connettivita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AskInterface.createAndShowGUI(Interface.this);

                    }
                });
            }
        });

        gestioneAccount = new JMenuItem("Gestione account");
        gestioneAccount.setMnemonic(KeyEvent.VK_G);
        gestioneAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GestioneAccount.createAndShowGUI(Interface.this);
                    }
                });
            }
        });

        visualizzaAnni = new JMenu("Visualizza tutti gli anni");
        visualizzaAnni.setMnemonic(KeyEvent.VK_V);

        showIP = new JMenuItem("Mostra indirizzo IP");
        showIP.setMnemonic(KeyEvent.VK_S);
        showIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SynchroInterface.createAndShowGUI();
            }
        });

        exportToCSV = new JMenuItem("Esporta in .CSV");
        exportToCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();

                String fileA = loadFile();
                if (fileA == null)
                    fileA = System.getProperty("user.home") + File.separator + "Desktop";

                chooser.setCurrentDirectory(new File(fileA));

                int returnVal = chooser.showOpenDialog(frame.getParent());
                String file = null;

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile().toString();
                }

                try {
                    if (file == null)
                        throw new FileNotFoundException("Directory errata");

                    saveFile(file);
                    bambiniToCSV(file);
                    JOptionPane.showMessageDialog(null, "Salvataggio riuscito!\n" + file,
                            "Completamento operazione", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException | UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Impossibile esportare!",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        impostazioni.add(connettivita);
        impostazioni.add(gestioneAccount);
        impostazioni.add(visualizzaAnni);
        impostazioni.add(exportToCSV);
        impostazioni.add(showIP);
        menuBar.add(impostazioni);

        upPanel.add(menuBar, BorderLayout.PAGE_START);

        add(upPanel, BorderLayout.PAGE_START);

        add(downPanel, BorderLayout.CENTER);

        stampaAnimatori = new

                JButton(new ImageIcon("Data/printer-tool32.png"));
        stampaAnimatori.setMnemonic(KeyEvent.VK_P);
        final JFrame frame1 = frame;
        stampaAnimatori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JTable table = tableAnimatori;
                table.setFont(new Font("", Font.PLAIN, 45));
                table.getTableHeader().setFont(new Font("", Font.PLAIN, 60));
                table.getTableHeader().setSize(new Dimension(100, 80));
                table.setRowHeight(55);

                frame1.dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PrintInterface.createAndShowGUI(table,
                                Interface.this, "animatori");
                    }
                });
            }
        });

        stampaAnimatori.setVisible(false);

        tableAnimatoriStampa.add(Box.createRigidArea(new

                Dimension(150, 0)), BorderLayout.LINE_START);
        tableAnimatoriStampa.add(stampaAnimatori, BorderLayout.CENTER);
        tableAnimatoriStampa.add(Box.createRigidArea(new

                Dimension(150, 0)), BorderLayout.LINE_END);

        tabAnimatoriPanelUp.add(tableAnimatoriStampa);

        dbNameLBL1 = new

                JLabel("Anno database:     " + dbConnection.getDbTable());
        dbNameLBL1.setHorizontalAlignment(SwingConstants.CENTER);
        dbNameLBL1.setFont(new

                Font("Verdana", Font.PLAIN, 20));
        dbNameLBL1.setForeground(new

                Color(255, 0, 0));
        tabAnimatoriPanelUp.add(dbNameLBL1);

        String text = "Ricerca COGNOME, NOME, ANNO";

        cercaAnimatori = new

                JTextField();
        cercaAnimatori.setText(text);
        cercaAnimatori.setFont(new

                Font("", Font.ITALIC, 11));
        cercaAnimatori.setForeground(Color.LIGHT_GRAY);
        cercaAnimatori.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cercaAnimatori.setText("");
                cercaAnimatori.setFont(new Font("", Font.PLAIN, 14));
                cercaAnimatori.setForeground(Color.BLACK);
            }
        });
        cercaAnimatori.getDocument().

                addDocumentListener(new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        doResearch();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        doResearch();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        doResearch();
                    }

                    public void doResearch() {
                        search(cercaAnimatori.getText()
                                .replace(",", "").replace(" ", "%"), "Animatori");
                    }
                });
        cercaAnimatori.setVisible(false);
        tabAnimatoriPanelUp.add(cercaAnimatori);

        stampaBambini = new

                JButton(new ImageIcon("Data/printer-tool32.png"));
        stampaBambini.setMnemonic(KeyEvent.VK_P);
        stampaBambini.addActionListener(new

                                                ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        final JTable table = tableBambini;
                                                        table.setFont(new Font("", Font.PLAIN, 45));
                                                        table.getTableHeader().setFont(new Font("", Font.PLAIN, 60));
                                                        table.getTableHeader().setSize(new Dimension(100, 80));
                                                        table.setRowHeight(55);

                                                        frame1.dispose();

                                                        SwingUtilities.invokeLater(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                PrintInterface.createAndShowGUI(table,
                                                                        Interface.this, "bambini");
                                                            }
                                                        });
                                                    }
                                                });
        stampaBambini.setVisible(false);

        tableBambiniStampa.add(Box.createRigidArea(new

                Dimension(150, 0)), BorderLayout.LINE_START);
        tableBambiniStampa.add(stampaBambini, BorderLayout.CENTER);
        tableBambiniStampa.add(Box.createRigidArea(new

                Dimension(150, 0)), BorderLayout.LINE_END);

        tabBambiniPanelUp.add(tableBambiniStampa);

        dbNameLBL2 = new

                JLabel("Anno database:     " + dbConnection.getDbTable());
        dbNameLBL2.setHorizontalAlignment(SwingConstants.CENTER);
        dbNameLBL2.setFont(new

                Font("Verdana", Font.PLAIN, 20));
        dbNameLBL2.setForeground(new

                Color(255, 0, 0));
        tabBambiniPanelUp.add(dbNameLBL2);

        cercaBambini = new

                JTextField();
        cercaBambini.setText(text);
        cercaBambini.setFont(new

                Font("", Font.ITALIC, 11));
        cercaBambini.setForeground(Color.LIGHT_GRAY);
        cercaBambini.addMouseListener(new

                                              MouseAdapter() {
                                                  @Override
                                                  public void mouseClicked(MouseEvent e) {
                                                      cercaBambini.setText("");
                                                      cercaBambini.setFont(new Font("", Font.PLAIN, 14));
                                                      cercaBambini.setForeground(Color.BLACK);
                                                  }
                                              });
        cercaBambini.getDocument().

                addDocumentListener(new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        doResearch();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        doResearch();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        doResearch();
                    }

                    public void doResearch() {
                        search(cercaBambini.getText()
                                .replace(",", "").replace(" ", "%"), "Bambini");
                    }
                });
        cercaBambini.setVisible(false);
        tabBambiniPanelUp.add(cercaBambini);

        tabAnimatoriPanel.add(tabAnimatoriPanelUp, BorderLayout.PAGE_START);
        tabBambiniPanel.add(tabBambiniPanelUp, BorderLayout.PAGE_START);

        tab.addTab("Animatori", icon, tabAnimatoriPanel,
                "Passa alla tabella animatori");
        tab.setMnemonicAt(0, KeyEvent.VK_1);

        tab.addTab("Bambini", icon, tabBambiniPanel,
                "Passa alla tabella bambini");
        tab.setMnemonicAt(1, KeyEvent.VK_2);

        boolean ok1;
        boolean ok2;

        try

        {
            FileReader fileReader = new FileReader(new File("Data/AccountData.data"));
            fileReader.read();
            fileReader.close();
            ok1 = true;
        } catch (
                Exception ex)

        {
            ex.printStackTrace();
            ok1 = false;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    CreateAccount.createAndShowGUI(Interface.this);
                }
            });
        }

        if (ok1)

        {
            try {
                FileReader fileReader = new FileReader(new File("Data/DBData.data"));
                fileReader.read();
                fileReader.close();
                ok2 = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                ok2 = false;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AskInterface.createAndShowGUI(Interface.this);
                    }
                });
            }

            if (ok1 && ok2) {
                setTable();
                caricaAnni();

                if (!dbConnection.getDbTable().equals("") &&
                        tableAnimatori != null && tableBambini != null) {
                    stampaAnimatori.setVisible(true);
                    cercaAnimatori.setVisible(true);
                    stampaBambini.setVisible(true);
                    cercaBambini.setVisible(true);

                    stampaAnimatori.setEnabled(tableAnimatori.getRowCount() != 0);
                    cercaAnimatori.setEnabled(tableAnimatori.getRowCount() != 0);
                    stampaBambini.setEnabled(tableBambini.getRowCount() != 0);
                    cercaBambini.setEnabled(tableBambini.getRowCount() != 0);
                }
            }
        }

        for (int i = 0; i < bambini.size(); i++) {
            if (!bambini.get(i).getSettimane().equals("Non specificato"))
                if (filtroSettimane.size() != 0) {
                    for (int k = 0; k < filtroSettimane.size(); k++)
                        if (!filtroSettimane.get(k).equals(bambini.get(i).getSettimane()))
                            filtroSettimane.add(bambini.get(i).getSettimane());
                } else
                    filtroSettimane.add(bambini.get(i).getSettimane());

            if (!bambini.get(i).getTaglia().equals("Non specificato"))
                if (filtroTaglia.size() != 0) {
                    for (int k = 0; k < filtroTaglia.size(); k++)
                        if (!filtroTaglia.get(k).equals(bambini.get(i).getTaglia()))
                            filtroTaglia.add(bambini.get(i).getTaglia());
                } else
                    filtroTaglia.add(bambini.get(i).getTaglia());

            if (!bambini.get(i).getMaglietta().equals(""))
                if (filtroMagliette.size() != 0) {
                    for (int k = 0; k < filtroMagliette.size(); k++)
                        if (!filtroMagliette.get(k).equals(bambini.get(i).getMaglietta()))
                            filtroMagliette.add(bambini.get(i).getMaglietta());
                } else
                    filtroMagliette.add(bambini.get(i).getMaglietta());

            if (!bambini.get(i).isPit_stop().equals("Non specificato"))
                if (filtroPitStop.size() != 0) {
                    for (int k = 0; k < filtroPitStop.size(); k++)
                        if (!filtroPitStop.get(k).equals(bambini.get(i).isPit_stop()))
                            filtroPitStop.add(bambini.get(i).isPit_stop());
                } else
                    filtroPitStop.add(bambini.get(i).isPit_stop());

            if (!bambini.get(i).getPagamento().equals("Non specificato"))
                if (filtroPagamento.size() != 0) {
                    for (int k = 0; k < filtroPagamento.size(); k++)
                        if (!filtroPagamento.get(k).equals(bambini.get(i).getPagamento()))
                            filtroPagamento.add(bambini.get(i).getPagamento());
                } else
                    filtroPagamento.add(bambini.get(i).getPagamento());

            if (!bambini.get(i).isEntrataAnticipata().equals("Non specificato"))
                if (filtroEntrataAnticipata.size() != 0) {
                    for (int k = 0; k < filtroEntrataAnticipata.size(); k++)
                        if (!filtroEntrataAnticipata.get(k).equals(bambini.get(i).isEntrataAnticipata()))
                            filtroEntrataAnticipata.add(bambini.get(i).isEntrataAnticipata());
                } else
                    filtroEntrataAnticipata.add(bambini.get(i).isEntrataAnticipata());

            if (!bambini.get(i).isPranzo().equals("Non specificato"))
                if (filtroPranzo.size() != 0) {
                    for (int k = 0; k < filtroPranzo.size(); k++)
                        if (!filtroPranzo.get(k).equals(bambini.get(i).isPranzo()))
                            filtroPranzo.add(bambini.get(i).isPranzo());
                } else
                    filtroPranzo.add(bambini.get(i).isPranzo());
        }

        filtri.add(filtroSettimane);
        filtri.add(filtroTaglia);
        filtri.add(filtroMagliette);
        filtri.add(filtroPitStop);
        filtri.add(filtroPagamento);
        filtri.add(filtroEntrataAnticipata);
        filtri.add(filtroPranzo);

        filters = new JMenu("Filtri");

        showFilters = new JMenu("Gestione filtri");

        animatoriShowFilters = new JMenuItem("Animatori");
        animatoriShowFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterInterface.createAndShowGUI(Interface.this, filtriAnimatori, "Animatori");
            }
        });

        bambiniShowFilters = new JMenuItem("Bambini");
        bambiniShowFilters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterInterface.createAndShowGUI(Interface.this, filtri, "Bambini");
            }
        });

        showFilters.add(animatoriShowFilters);
        showFilters.add(bambiniShowFilters);

        filters.add(showPreferenze);
        filters.add(showMagliette);
        filters.add(showTaglia);
        filters.add(showFilters);
        menuBar.add(filters);

        Set<ICheckBox> iCheckBoxes = null;
        ArrayList<String> settimane = new ArrayList<>();
        ArrayList<String> taglia = new ArrayList<>();
        ArrayList<String> maglietta = new ArrayList<>();
        ArrayList<String> pitStop = new ArrayList<>();
        ArrayList<String> pagamento = new ArrayList<>();
        ArrayList<String> entrataAnticipata = new ArrayList<>();
        ArrayList<String> pranzo = new ArrayList<>();

        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(
                    new File("Data/FilterBambini.data")));
            iCheckBoxes = ((SaveArrayList) reader.readObject()).getiCheckBoxes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (iCheckBoxes != null) {
            for (ICheckBox iCheckBox : iCheckBoxes)
                switch (iCheckBox.getId().split("_")[0]) {
                    case "settimanePanel":
                        settimane.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "tagliaPanel":
                        taglia.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "magliettePanel":
                        maglietta.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "pitStopPanel":
                        pitStop.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "pagamentoPanel":
                        pagamento.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "entrataAnticipataPanel":
                        entrataAnticipata.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "pranzoPanel":
                        pranzo.add(iCheckBox.getId().split("_")[1]);
                        break;
                }

            boolean[] okB = new boolean[7];

            if (!(settimane.size() == 0 && taglia.size() == 0 && maglietta.size() == 0 &&
                    pitStop.size() == 0 && pagamento.size() == 0 && entrataAnticipata.size() == 0 &&
                    pranzo.size() == 0)) {

                Set<Bambino> bambiniFiltrati = new HashSet<>();

                for (Bambino bambino : bambini) {
                    okB = resetOk(okB);

                    for (String settimana : settimane)
                        if (bambino.getSettimane().equals(settimana))
                            okB[0] = true;
                    if (settimane.size() == 0)
                        okB[0] = true;
                    for (String taglia1 : taglia)
                        if (bambino.getTaglia().equals(taglia1))
                            okB[1] = true;
                    if (taglia.size() == 0)
                        okB[1] = true;
                    for (String maglia : maglietta)
                        if (bambino.getMaglietta().equals(maglia))
                            okB[2] = true;
                    if (maglietta.size() == 0)
                        okB[2] = true;
                    for (String pit : pitStop)
                        if (bambino.isPit_stop().equals(pit))
                            okB[3] = true;
                    if (pitStop.size() == 0)
                        okB[3] = true;
                    for (String pag : pagamento)
                        if (bambino.getPagamento().equals(pag))
                            okB[4] = true;
                    if (pagamento.size() == 0)
                        okB[4] = true;
                    for (String ea : entrataAnticipata)
                        if (bambino.isEntrataAnticipata().equals(ea))
                            okB[5] = true;
                    if (entrataAnticipata.size() == 0)
                        okB[5] = true;
                    for (String pra : pranzo)
                        if (bambino.isPranzo().equals(pra))
                            okB[6] = true;
                    if (pranzo.size() == 0)
                        okB[6] = true;

                    if (checkOk(okB))
                        bambiniFiltrati.add(bambino);
                }

                int lenCol = 5;

                if (showPreferenze.isSelected())
                    lenCol++;
                if (showMagliette.isSelected())
                    lenCol++;
                if (showTaglia.isSelected())
                    lenCol++;

                Object[][] data = new Object[bambiniFiltrati.size()][lenCol];

                int i = 0;

                for (Bambino bambino : bambiniFiltrati) {
                    data[i][indexCognome] = "   " + bambino.getCognome();
                    data[i][indexNome] = "   " + bambino.getNome();
                    data[i][indexAnno] = "   " + bambino.getAnno();
                    data[i][lenCol - 1] = "   (" + bambino.getId() + ")";

                    if (showPreferenze.isSelected() && !showMagliette.isSelected() && !showTaglia.isSelected()) {
                        data[i][4] = "   " + bambino.getPreferenza();
                    } else if (!showPreferenze.isSelected() && showMagliette.isSelected() && !showTaglia.isSelected()) {
                        data[i][4] = "   " + bambino.getMaglietta();
                    } else if (!showPreferenze.isSelected() && !showMagliette.isSelected() && showTaglia.isSelected()) {
                        data[i][4] = "   " + bambino.getTaglia();
                    } else if (showPreferenze.isSelected() && showMagliette.isSelected() && !showTaglia.isSelected()) {
                        data[i][4] = "   " + bambino.getPreferenza();
                        data[i][5] = "   " + bambino.getMaglietta();
                    } else if (!showPreferenze.isSelected() && showMagliette.isSelected() && showTaglia.isSelected()) {
                        data[i][4] = "   " + bambino.getMaglietta();
                        data[i][5] = "   " + bambino.getTaglia();
                    } else if (showPreferenze.isSelected() && !showMagliette.isSelected() && showTaglia.isSelected()) {
                        data[i][4] = "   " + bambino.getPreferenza();
                        data[i][5] = "   " + bambino.getTaglia();
                    } else if (showPreferenze.isSelected() && showMagliette.isSelected() && showTaglia.isSelected()) {
                        data[i][4] = "   " + bambino.getPreferenza();
                        data[i][5] = "   " + bambino.getMaglietta();
                        data[i][6] = "   " + bambino.getTaglia();
                    }

                    data[i][0] = ++i;
                }

                Object[] array = new Object[data.length];

                for (int d = 0; d < array.length; d++)
                    array[d] = data[d][indexCognome];

                Arrays.sort(array, 0, array.length);
                Object[] sup;
                Bambino bambinoSup1;
                Bambino bambinoSup2;

                for (int e = 0; e < array.length; e++) {
                    for (int k = e; k < data.length; k++) {
                        if (array[e].equals(data[k][indexCognome])) {
                            sup = data[k];
                            data[k] = data[e];
                            data[e] = sup;

                            bambinoSup1 = bambini.get(k);
                            bambinoSup2 = bambini.get(e);
                            bambini.remove(k);
                            bambini.add(k, bambinoSup2);
                            bambini.remove(e);
                            bambini.add(e, bambinoSup1);
                        }
                    }
                }

                for (int f = 0; f < data.length; f++)
                    data[f][0] = f + 1;

                createAndShowTable(data, tableBambini);

                bambini.clear();

                for (Bambino bambino : bambiniFiltrati)
                    bambini.add(bambino);
            }
        }

        for (int i = 0; i < animatori.size(); i++) {
            if (filtroResponsabile.size() != 0) {
                for (int k = 0; k < filtroResponsabile.size(); k++)
                    if (!filtroResponsabile.get(k).equals(Boolean.toString(animatori.get(i).getResponsabile())))
                        filtroResponsabile.add(Boolean.toString(animatori.get(i).getResponsabile()));
            } else
                filtroResponsabile.add(Boolean.toString(animatori.get(i).getResponsabile()));

            if (filtrogrest.size() != 0) {
                for (int k = 0; k < filtrogrest.size(); k++)
                    for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                        if (animazioneEstiva.getNomeAttivita().equals("Grest") &&
                                !animazioneEstiva.getPartecipazione().equals("Non specificato") &&
                                !animazioneEstiva.getPartecipazione().equals(filtrogrest.get(k)))
                            filtrogrest.add(animazioneEstiva.getPartecipazione());
            } else {
                for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                    if (animazioneEstiva.getNomeAttivita().equals("Grest") &&
                            !animazioneEstiva.getPartecipazione().equals("Non specificato"))
                        filtrogrest.add(animazioneEstiva.getPartecipazione());
            }

            if (filtrominigrest.size() != 0) {
                for (int k = 0; k < filtrominigrest.size(); k++)
                    for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                        if (animazioneEstiva.getNomeAttivita().equals("MiniGrest") &&
                                !animazioneEstiva.getPartecipazione().equals("Non specificato") &&
                                !animazioneEstiva.getPartecipazione().equals(filtrominigrest.get(k)))
                            filtrominigrest.add(animazioneEstiva.getPartecipazione());
            } else {
                for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                    if (animazioneEstiva.getNomeAttivita().equals("MiniGrest") &&
                            !animazioneEstiva.getPartecipazione().equals("Non specificato"))
                        filtrominigrest.add(animazioneEstiva.getPartecipazione());
            }

            if (filtrocampoADO.size() != 0) {
                for (int k = 0; k < filtrocampoADO.size(); k++)
                    for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                        if (animazioneEstiva.getNomeAttivita().equals("CampoADO") &&
                                !animazioneEstiva.getPartecipazione().equals("Non specificato") &&
                                !animazioneEstiva.getPartecipazione().equals(filtrocampoADO.get(k)))
                            filtrocampoADO.add(animazioneEstiva.getPartecipazione());
            } else {
                for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                    if (animazioneEstiva.getNomeAttivita().equals("CampoADO") &&
                            !animazioneEstiva.getPartecipazione().equals("Non specificato"))
                        filtrocampoADO.add(animazioneEstiva.getPartecipazione());
            }

            if (filtroPit_Stop.size() != 0) {
                for (int k = 0; k < filtroPit_Stop.size(); k++)
                    for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                        if (animazioneEstiva.getNomeAttivita().equals("PitStop") &&
                                !animazioneEstiva.getPartecipazione().equals("Non specificato") &&
                                !animazioneEstiva.getPartecipazione().equals(filtroPit_Stop.get(k)))
                            filtroPit_Stop.add(animazioneEstiva.getPartecipazione());
            } else {
                for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                    if (animazioneEstiva.getNomeAttivita().equals("PitStop") &&
                            !animazioneEstiva.getPartecipazione().equals("Non specificato"))
                        filtroPit_Stop.add(animazioneEstiva.getPartecipazione());
            }

            if (filtroslpa.size() != 0) {
                for (int k = 0; k < filtroslpa.size(); k++)
                    for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                        if (animazioneEstiva.getNomeAttivita().equals("SanLuigiPreADO") &&
                                !animazioneEstiva.getPartecipazione().equals("Non specificato") &&
                                !animazioneEstiva.getPartecipazione().equals(filtroslpa.get(k)))
                            filtroslpa.add(animazioneEstiva.getPartecipazione());
            } else {
                for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                    if (animazioneEstiva.getNomeAttivita().equals("SanLuigiPreADO") &&
                            !animazioneEstiva.getPartecipazione().equals("Non specificato"))
                        filtroslpa.add(animazioneEstiva.getPartecipazione());
            }

            if (filtrosl.size() != 0) {
                for (int k = 0; k < filtrosl.size(); k++)
                    for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                        if (animazioneEstiva.getNomeAttivita().equals("SanLuigi") &&
                                !animazioneEstiva.getPartecipazione().equals("Non specificato") &&
                                !animazioneEstiva.getPartecipazione().equals(filtrosl.get(k)))
                            filtrosl.add(animazioneEstiva.getPartecipazione());
            } else {
                for (AnimazioneEstiva animazioneEstiva : animatori.get(i).getAnimazioneEstiva())
                    if (animazioneEstiva.getNomeAttivita().equals("SanLuigi") &&
                            !animazioneEstiva.getPartecipazione().equals("Non specificato"))
                        filtrosl.add(animazioneEstiva.getPartecipazione());
            }
        }

        filtriAnimatori.add(filtroResponsabile);
        filtriAnimatori.add(filtrogrest);
        filtriAnimatori.add(filtrominigrest);
        filtriAnimatori.add(filtrocampoADO);
        filtriAnimatori.add(filtroPit_Stop);
        filtriAnimatori.add(filtroslpa);
        filtriAnimatori.add(filtrosl);

        Set<ICheckBox> iCheckBoxesAnimatori = null;
        ArrayList<String> responsabile = new ArrayList<>();
        ArrayList<String> grest = new ArrayList<>();
        ArrayList<String> minigrest = new ArrayList<>();
        ArrayList<String> campoADO = new ArrayList<>();
        ArrayList<String> pit_stop = new ArrayList<>();
        ArrayList<String> slpa = new ArrayList<>();
        ArrayList<String> sl = new ArrayList<>();

        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(
                    new File("Data/FilterAnimatori.data")));
            iCheckBoxesAnimatori = ((SaveArrayList) reader.readObject()).getiCheckBoxes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (iCheckBoxesAnimatori != null) {
            for (ICheckBox iCheckBox : iCheckBoxesAnimatori)
                switch (iCheckBox.getId().split("_")[0]) {
                    case "responsabilePanel":
                        responsabile.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "grestPanel":
                        grest.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "minigrestPanel":
                        minigrest.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "campoAdoPanel":
                        campoADO.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "pit-stopPanel":
                        pit_stop.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "slpaPanel":
                        slpa.add(iCheckBox.getId().split("_")[1]);
                        break;
                    case "slPanel":
                        sl.add(iCheckBox.getId().split("_")[1]);
                        break;
                }

            boolean[] ok = new boolean[7];

            if (!(responsabile.size() == 0 && grest.size() == 0 && minigrest.size() == 0 &&
                    campoADO.size() == 0 && pit_stop.size() == 0 && slpa.size() == 0 && sl.size() == 0)) {

                Set<Animatore> animatoriFiltrati = new HashSet<>();

                for (Animatore animatore : animatori) {
                    ok = resetOk(ok);

                    for (String res : responsabile)
                        if (res.replace("Sì", "true").replace("No", "false")
                                .equals(Boolean.toString(animatore.getResponsabile())))
                            ok[0] = true;
                    if (responsabile.size() == 0)
                        ok[0] = true;
                    for (String gre : grest)
                        for (AnimazioneEstiva animazioneEstiva : animatore.getAnimazioneEstiva())
                            if (animazioneEstiva.getNomeAttivita().equals("Grest"))
                                if (gre.equals(animazioneEstiva.getPartecipazione()))
                                    ok[1] = true;
                    if (grest.size() == 0)
                        ok[1] = true;
                    for (String gre : minigrest)
                        for (AnimazioneEstiva animazioneEstiva : animatore.getAnimazioneEstiva())
                            if (animazioneEstiva.getNomeAttivita().equals("MiniGrest"))
                                if (gre.equals(animazioneEstiva.getPartecipazione()))
                                    ok[2] = true;
                    if (minigrest.size() == 0)
                        ok[2] = true;
                    for (String gre : campoADO)
                        for (AnimazioneEstiva animazioneEstiva : animatore.getAnimazioneEstiva())
                            if (animazioneEstiva.getNomeAttivita().equals("CampoADO"))
                                if (gre.equals(animazioneEstiva.getPartecipazione()))
                                    ok[3] = true;
                    if (campoADO.size() == 0)
                        ok[3] = true;
                    for (String gre : pit_stop)
                        for (AnimazioneEstiva animazioneEstiva : animatore.getAnimazioneEstiva())
                            if (animazioneEstiva.getNomeAttivita().equals("PitStop"))
                                if (gre.equals(animazioneEstiva.getPartecipazione()))
                                    ok[4] = true;
                    if (pit_stop.size() == 0)
                        ok[4] = true;
                    for (String gre : slpa)
                        for (AnimazioneEstiva animazioneEstiva : animatore.getAnimazioneEstiva())
                            if (animazioneEstiva.getNomeAttivita().equals("SanLuigiPreADO"))
                                if (gre.equals(animazioneEstiva.getPartecipazione()))
                                    ok[5] = true;
                    if (slpa.size() == 0)
                        ok[5] = true;
                    for (String gre : sl)
                        for (AnimazioneEstiva animazioneEstiva : animatore.getAnimazioneEstiva())
                            if (animazioneEstiva.getNomeAttivita().equals("SanLuigi"))
                                if (gre.equals(animazioneEstiva.getPartecipazione()))
                                    ok[6] = true;
                    if (sl.size() == 0)
                        ok[6] = true;

                    if (checkOk(ok))
                        animatoriFiltrati.add(animatore);
                }

                Object[][] data = new Object[animatoriFiltrati.size()][5];

                int i = 0;

                for (Animatore animatore : animatoriFiltrati) {
                    data[i][indexCognome] = "   " + animatore.getCognome();
                    data[i][indexNome] = "   " + animatore.getNome();
                    data[i][indexAnno] = "   " + animatore.getAnno();
                    data[i][4] = "   (" + animatore.getId() + ")";
                    data[i][0] = ++i;
                }

                Object[] array = new Object[data.length];

                for (int d = 0; d < array.length; d++)
                    array[d] = data[d][indexCognome];

                Arrays.sort(array, 0, array.length);
                Object[] sup;
                Animatore bambinoSup1;
                Animatore bambinoSup2;

                for (int e = 0; e < array.length; e++) {
                    for (int k = e; k < data.length; k++) {
                        if (array[e].equals(data[k][indexCognome])) {
                            sup = data[k];
                            data[k] = data[e];
                            data[e] = sup;

                            bambinoSup1 = animatori.get(k);
                            bambinoSup2 = animatori.get(e);
                            animatori.remove(k);
                            animatori.add(k, bambinoSup2);
                            animatori.remove(e);
                            animatori.add(e, bambinoSup1);
                        }
                    }
                }

                for (int f = 0; f < data.length; f++)
                    data[f][0] = f + 1;

                createAndShowTable(data, tableAnimatori);

                animatori.clear();

                for (Animatore animatore : animatoriFiltrati)
                    animatori.add(animatore);
            }
        }


        ImageIcon statusIconAnimatori = new ImageIcon((iCheckBoxesAnimatori.size() != 0) ? "Data/quadrato_verde.png" : "Data/quadrato_rosso.png");
        ImageIcon statusIconBambini = new ImageIcon((iCheckBoxes.size() != 0) ? "Data/quadrato_verde.png" : "Data/quadrato_rosso.png");

        filtriAnimatoriAttivi = new JLabel("Filtri animatori   ");
        filtriAnimatoriAttivi.setIcon(statusIconAnimatori);
        filtriBambiniAttivi = new JLabel("Filtri bambini   ");
        filtriBambiniAttivi.setIcon(statusIconBambini);

        filtriAttivi = new JMenu("Stato filtri");

        menuBar.add(filtriAnimatoriAttivi);
        menuBar.add(filtriBambiniAttivi);
    }

    private boolean[] resetOk(boolean[] ok) {
        boolean[] okReturn = new boolean[ok.length];

        for (int i = 0; i < okReturn.length; i++)
            okReturn[i] = false;

        return okReturn;
    }

    private boolean checkOk(boolean[] ok) {
        for (boolean type : ok)
            if (!type)
                return false;

        return true;
    }

    public JTable loadData(String type) {
        JTable table;

        try {
            String request = "SELECT * FROM " + type + "_" + dbConnection.getDbTable();

            ResultSet resultSet =
                    connection.createStatement().executeQuery(
                            request);

            int len = 0;
            if (resultSet.last())
                len = resultSet.getRow();

            resultSet =
                    connection.createStatement().executeQuery(
                            request);

            Object[][] data = load(resultSet, len, type, animatori, bambini);

            table = createTable(data, type);
        } catch (Exception exception) {
            exception.printStackTrace();
            table = new JTable();
        }

        return table;
    }

    private void saveFile(String file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Data/Dir.data")));
            writer.write(file);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String loadFile() {
        String read = null;
        String readAll = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Data/Dir.data")));
            while ((read = reader.readLine()) != null)
                readAll += read;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readAll;
    }

    private JTable createTable(Object[][] data, String type) {
        JTable table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        table.setFont(new Font("Verdana", Font.PLAIN, 15));
        table.setRowHeight(25);

        ((DefaultTableModel) table.getModel()).addColumn("N");
        ((DefaultTableModel) table.getModel()).addColumn("Cognome");
        ((DefaultTableModel) table.getModel()).addColumn("Nome");
        ((DefaultTableModel) table.getModel()).addColumn("Anno di nascita");
        if (type.equals("Bambini")) {
            if (showPreferenze.isSelected())
                ((DefaultTableModel) table.getModel()).addColumn("Preferenze");
            if (showMagliette.isSelected())
                ((DefaultTableModel) table.getModel()).addColumn("Magliette");
            if (showTaglia.isSelected())
                ((DefaultTableModel) table.getModel()).addColumn("Taglia");
        }
        ((DefaultTableModel) table.getModel()).addColumn("ID");

        table = createAndShowTable(data, table);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        if (type.equals("Bambini") && table.getColumnCount() == 6) {
            table.getColumnModel().getColumn(1).setPreferredWidth(350);
            table.getColumnModel().getColumn(2).setPreferredWidth(350);
            table.getColumnModel().getColumn(4).setPreferredWidth(300);
        } else if (type.equals("Bambini") && table.getColumnCount() == 7) {
            table.getColumnModel().getColumn(1).setPreferredWidth(350);
            table.getColumnModel().getColumn(2).setPreferredWidth(350);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
            table.getColumnModel().getColumn(5).setPreferredWidth(150);
        } else if (type.equals("Bambini") && table.getColumnCount() == 8) {
            table.getColumnModel().getColumn(1).setPreferredWidth(275);
            table.getColumnModel().getColumn(2).setPreferredWidth(275);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
            table.getColumnModel().getColumn(5).setPreferredWidth(150);
            table.getColumnModel().getColumn(6).setPreferredWidth(150);
        } else {
            table.getColumnModel().getColumn(1).setPreferredWidth(500);
            table.getColumnModel().getColumn(2).setPreferredWidth(500);
        }

        return table;
    }

    private boolean loadAssets(String name) {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(new File("Data/show.data")));
            SaveShow saveShow = (SaveShow) reader.readObject();
            reader.close();

            boolean status = false;

            for (JMenuItem jMenuItem : saveShow.getMenuItems())
                if (jMenuItem.getText().replace("Mostra ", "").equals(name.toLowerCase()))
                    status = jMenuItem.isSelected();

            return status;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void saveAssets(String name, JMenuItem item) {
        frame.dispose();
        File file = new File("Data/show.data");

        if (!file.exists())
            try {
                file.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));

            ArrayList<JMenuItem> menuItems = new ArrayList<>();

            menuItems.add(showPreferenze);
            menuItems.add(showMagliette);
            menuItems.add(showTaglia);

            writer.writeObject(new SaveShow(menuItems));
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        StartProgramInterface.createAndShowGUI();
    }

    public JTable createAndShowTable(Object[][] data, JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        tableModel.setRowCount(0);

        for (int i = 0; i < data.length; i++)
            tableModel.addRow(data[i]);

        tableModel.fireTableDataChanged();
        table.setModel(tableModel);
        table.repaint();

        return table;
    }

    public Object[][] load(ResultSet resultSet, int len, String
            type, ArrayList<Animatore> animatori, ArrayList<Bambino> bambini) {
        try {
            if (type.equals("Animatori")) {
                Object[][] data = new Object[len][5];

                int index = 0;

                while (resultSet.next()) {
                    Animatore animatore =
                            (Animatore) StartProgramInterface.fromString(resultSet.getString("Animatore"));

                    animatore.setId(Integer.parseInt(resultSet.getString("ID")));

                    data[index][0] = " ";
                    data[index][indexCognome] = "   " + animatore.getCognome();
                    data[index][indexNome] = "   " + animatore.getNome();
                    data[index][indexAnno] = "   " + animatore.getAnno();
                    data[index][4] = "   (" + animatore.getId() + ")";

                    animatori.add(animatore);

                    index++;
                }

                Object[] array = new Object[data.length];

                for (int i = 0; i < array.length; i++)
                    array[i] = data[i][indexCognome];

                Arrays.sort(array, 0, array.length);
                Object[] sup;
                Animatore animatoreSup1;
                Animatore animatoreSup2;

                for (int i = 0; i < array.length; i++) {
                    for (int k = i; k < data.length; k++) {
                        if (array[i].equals(data[k][indexCognome])) {
                            sup = data[k];
                            data[k] = data[i];
                            data[i] = sup;

                            animatoreSup1 = animatori.get(k);
                            animatoreSup2 = animatori.get(i);
                            animatori.remove(k);
                            animatori.add(k, animatoreSup2);
                            animatori.remove(i);
                            animatori.add(i, animatoreSup1);
                        }
                    }
                }

                for (int i = 0; i < data.length; i++)
                    data[i][0] = i + 1;

                return data;
            } else if (type.equals("Bambini")) {
                Object[][] data;
                int lenCol = 5;

                if (showPreferenze.isSelected())
                    lenCol++;
                if (showMagliette.isSelected())
                    lenCol++;
                if (showTaglia.isSelected())
                    lenCol++;

                data = new Object[len][lenCol];

                int index = 0;

                while (resultSet.next()) {
                    Bambino bambino =
                            (Bambino) StartProgramInterface.fromString(resultSet.getString("Bambino"));

                    bambino.setId(Integer.parseInt(resultSet.getString("ID")));

                    data[index][0] = " ";
                    data[index][indexCognome] = "   " + bambino.getCognome();
                    data[index][indexNome] = "   " + bambino.getNome();
                    data[index][3] = "   " + bambino.getAnno();

                    if (showPreferenze.isSelected() && !showMagliette.isSelected() && !showTaglia.isSelected())
                        data[index][4] = "   " + bambino.getPreferenza();
                    if (!showPreferenze.isSelected() && showMagliette.isSelected() && !showTaglia.isSelected())
                        data[index][4] = "   " + bambino.getMaglietta();
                    if (!showPreferenze.isSelected() && !showMagliette.isSelected() && showTaglia.isSelected())
                        data[index][4] = "   " + bambino.getTaglia();
                    if (showPreferenze.isSelected() && showMagliette.isSelected() && !showTaglia.isSelected()) {
                        data[index][4] = "   " + bambino.getPreferenza();
                        data[index][5] = "   " + bambino.getMaglietta();
                    }
                    if (showPreferenze.isSelected() && showMagliette.isSelected() && showTaglia.isSelected()) {
                        data[index][4] = "   " + bambino.getPreferenza();
                        data[index][5] = "   " + bambino.getMaglietta();
                        data[index][6] = "   " + bambino.getTaglia();
                    }
                    if (!showPreferenze.isSelected() && showMagliette.isSelected() && showTaglia.isSelected()) {
                        data[index][4] = "   " + bambino.getMaglietta();
                        data[index][5] = "   " + bambino.getTaglia();
                    }
                    if (showPreferenze.isSelected() && !showMagliette.isSelected() && showTaglia.isSelected()) {
                        data[index][4] = "   " + bambino.getPreferenza();
                        data[index][5] = "   " + bambino.getTaglia();
                    }

                    data[index][lenCol - 1] = "   (" + bambino.getId() + ")";

                    bambini.add(bambino);

                    index++;
                }

                Object[] array = new Object[data.length];

                for (int i = 0; i < array.length; i++)
                    array[i] = data[i][indexCognome];

                Arrays.sort(array, 0, array.length);
                Object[] sup;
                Bambino bambinoSup1;
                Bambino bambinoSup2;

                for (int i = 0; i < array.length; i++) {
                    for (int k = i; k < data.length; k++) {
                        if (array[i].equals(data[k][indexCognome])) {
                            sup = data[k];
                            data[k] = data[i];
                            data[i] = sup;

                            bambinoSup1 = bambini.get(k);
                            bambinoSup2 = bambini.get(i);
                            bambini.remove(k);
                            bambini.add(k, bambinoSup2);
                            bambini.remove(i);
                            bambini.add(i, bambinoSup1);
                        }
                    }
                }

                for (int i = 0; i < data.length; i++)
                    data[i][0] = i + 1;

                return data;
            }
            return new Object[1][1];
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Object[1][1];
        }
    }

    public void setAttivo(String text) {
        for (int i = 0; i < data.size(); i++)
            if (data.get(i).getText().equals(text))
                impostaAttivo(data.get(i));
    }

    private void caricaAnni() {
        JMenuItem nuovoAnno = new JMenuItem("Nuovo anno");
        nuovoAnno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        NewTableInterface.createAndShowGUI(Interface.this, "Aggiungi");
                    }
                });
            }
        });
        visualizzaAnni.add(nuovoAnno);

        JMenuItem rimuoviSelezionato = new JMenuItem("Rimuovi anno");
        rimuoviSelezionato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        NewTableInterface.createAndShowGUI(Interface.this, "Rimuovi");
                    }
                });
            }
        });
        visualizzaAnni.add(rimuoviSelezionato);
        visualizzaAnni.add(new JSeparator());

        ArrayList<String> dati = loadAnniData();
        for (
                int i = 0; i < dati.size(); i++)

            createAndShowItem(dati.get(i));
    }

    public void createAndShowItem(String text) {
        JMenuItem anno = new JMenuItem(text);

        if (dbConnection.getDbTable().equals(text))
            anno.setEnabled(false);

        final JMenuItem anno1 = anno;

        anno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                impostaAttivo(anno1);

                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        StartProgramInterface.createAndShowGUI();
                    }
                });
            }
        });

        data.add(anno);
        visualizzaAnni.add(anno);
    }

    public void impostaAttivo(JMenuItem item) {
        dbConnection.setDbTable(item.getText());

        item.setEnabled(true);

        salvaSuFile();
    }

    public void salvaSuFile() {
        try {
            ObjectOutputStream writer
                    = new ObjectOutputStream(new FileOutputStream(new File(
                    "Data/DBData.data")));
            writer.writeObject(dbConnection);
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private ArrayList<String> loadAnniData() {
        ArrayList<String> tables = new ArrayList<>();
        ArrayList<String> tablesAnni = new ArrayList<>();

        try {
            connection = startProgramInterface.getConnection();
            ResultSet resultSet = connection.getMetaData().getTables(
                    null, null, "%", null);

            while (resultSet.next())
                tables.add(resultSet.getString(3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < tables.size() / 2; i++) {
            if (checkTables(i, tables.get(i), tables))
                if (tablesAnni.size() == 0)
                    tablesAnni.add(tables.get(i).split("_")[1]);
                else {
                    int counter = 0;
                    for (int k = 0; k < tablesAnni.size(); k++)
                        if (!checkTables(k, tables.get(i).split("_")[1], tablesAnni))
                            counter++;

                    if (counter != 0)
                        tablesAnni.add(tables.get(i).split("_")[1]);

                }
        }

        return tablesAnni;
    }

    private boolean checkTables(int index, String table, ArrayList<String> tables) {
        for (int i = index; i < tables.size(); i++)
            if (table.equals(tables.get(i)))
                return true;

        return false;
    }

    public void removeRows(int[] selectedRows, String type) {
        try {
            PreparedStatement preparedStatement;

            if (type.equals("animatore")) {
                ArrayList<Animatore> animatoriLocal = new ArrayList<>();

                for (int k : selectedRows) {
                    for (int i = 0; i < animatori.size(); i++) {
                        int id = Integer.parseInt(
                                ((String) tableAnimatori.getValueAt(k, (tableAnimatori.getColumnCount() - 1)))
                                        .replace("   ", "").replace("(", "")
                                        .replace(")", ""));

                        if (id == animatori.get(i).getId()) {
                            animatoriLocal.add(animatori.get(i));
                        }
                    }
                }

                for (int i = 0; i < animatoriLocal.size(); i++) {
                    preparedStatement = connection.prepareStatement("DELETE FROM Animatori_"
                            + dbConnection.getDbTable() + " WHERE ID = '"
                            + Integer.toString(animatoriLocal.get(i).getId())
                            + "';");
                    preparedStatement.execute();
                }
            } else if (type.equals("bambino")) {
                ArrayList<Bambino> bambiniLocal = new ArrayList<>();

                for (int k : selectedRows) {
                    for (int i = 0; i < bambini.size(); i++) {
                        int id = Integer.parseInt(
                                ((String) tableBambini.getValueAt(k, (tableBambini.getColumnCount() - 1)))
                                        .replace("   ", "").replace("(", "")
                                        .replace(")", ""));

                        if (id == bambini.get(i).getId()) {
                            bambiniLocal.add(bambini.get(i));
                        }
                    }
                }

                for (int i = 0; i < bambiniLocal.size(); i++) {
                    preparedStatement = connection.prepareStatement("DELETE FROM Bambini_"
                            + dbConnection.getDbTable() + " WHERE ID = '"
                            + Integer.toString(bambiniLocal.get(i).getId())
                            + "';");
                    preparedStatement.execute();
                }
            }

            frame.dispose();

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

    public void search(String text, String type) {
        try {
            if (type.equals("Animatori")) {
                String tableAnimatoriS = "Animatori_" + dbConnection.getDbTable();
                String questionMark = "%" + text + "%";

                PreparedStatement preparedStatementAnimatori = connection.prepareStatement(
                        "SELECT * FROM " + tableAnimatoriS + " WHERE cognomenome LIKE ?");
                preparedStatementAnimatori.setString(1, questionMark);

                ResultSet resultSetAnimatori = preparedStatementAnimatori.executeQuery();

                int lenAnimatori = 0;
                if (resultSetAnimatori.last())
                    lenAnimatori = resultSetAnimatori.getRow();

                resultSetAnimatori = preparedStatementAnimatori.executeQuery();

                createAndShowTable(load(resultSetAnimatori, lenAnimatori, "Animatori", animatori, bambini), tableAnimatori);
            } else if (type.equals("Bambini")) {
                String tableBambiniS = "Bambini_" + dbConnection.getDbTable();
                String questionMark = "%" + text + "%";

                PreparedStatement preparedStatementBambini = connection.prepareStatement(
                        "SELECT * FROM " + tableBambiniS + " WHERE cognomenome LIKE ?");
                preparedStatementBambini.setString(1, questionMark);

                ResultSet resultSetBambini = preparedStatementBambini.executeQuery();

                int lenBambini = 0;
                if (resultSetBambini.last())
                    lenBambini = resultSetBambini.getRow();

                resultSetBambini = preparedStatementBambini.executeQuery();

                createAndShowTable(load(resultSetBambini, lenBambini, "Bambini", animatori, bambini), tableBambini);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setTable() {
        if (animatori.size() != 0)
            tabAnimatoriPanel.add(scrollA, BorderLayout.CENTER);
        else {
            newRowA = new JButton(new ImageIcon("Data/user32.png"));
            newRowA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddInterface.createAndShowGUI(Interface.this, -1);
                }
            });

            spazioLInA = new JLabel("                    ");
            spazioRInA = new JLabel("                    ");

            downMiddlePanelInA.add(spazioLInA);
            downMiddlePanelInA.add(newRowA);
            downMiddlePanelInA.add(spazioRInA);

            tabAnimatoriPanel.add(downMiddlePanelInA, BorderLayout.CENTER);
        }

        if (bambini.size() != 0)
            tabBambiniPanel.add(scrollB, BorderLayout.CENTER);
        else {
            newRowB = new JButton(new ImageIcon("Data/user32.png"));
            newRowB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddBambinoInterface.createAndShowGUI(Interface.this, -1);
                }
            });

            spazioLInB = new JLabel("                    ");
            spazioRInB = new JLabel("                    ");

            downMiddlePanelInB.add(spazioLInB);
            downMiddlePanelInB.add(newRowB);
            downMiddlePanelInB.add(spazioRInB);

            tabBambiniPanel.add(downMiddlePanelInB, BorderLayout.CENTER);
        }
    }

    private void bambiniToCSV(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(new File(fileName), "UTF-8");

        writer.print("Id, Cognome, Nome, Data di nascita, Classe, Tel 1, Tel2");
        writer.print("\n\n");

        for (Bambino bambino : bambini)
            writer.println(bambino.getId() + "," + bambino.getCognome() + "," +
                    bambino.getNome() + "," + bambino.getDataDiNascita() + "," +
                    bambino.getClasse() + "," + bambino.getTelefono1() + "," + bambino.getTelefono2());

        writer.close();
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public ArrayList<JMenuItem> getData() {
        return data;
    }

    public JTable getTableAnimatori() {
        return tableAnimatori;
    }

    public JTable getTableBambini() {
        return tableBambini;
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Account getAccount() {
        return account;
    }

    public static void createAndShowGUI(Connection connection, DBConnection dbConnection, Account account,
                                        StartProgramInterface startProgramInterface) {
        JFrame frame = new JFrame("Gestore");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JComponent newContentPane = new Interface(frame, connection, dbConnection, account,
                startProgramInterface);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
