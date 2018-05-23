package it.lucaVezzoli.Interface;

import it.lucaVezzoli.Application.Account;
import it.lucaVezzoli.Application.DBConnection;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

public class StartProgramInterface {
    private JLabel statusJTA;
    private JProgressBar progressBar1;
    private JLabel startIcon;
    private JLabel connectionIcon;
    private JLabel downloadIcon;
    private JLabel fileIcon;
    private Connection connection = null;
    private DBConnection dbConnection;
    private Account account;
    private JPanel mainPanel;
    private boolean tryToRead;

    public StartProgramInterface(final JFrame frame) {
        statusJTA.setText("Caricamento dati account ...");
        account = loadAccountData();

        statusJTA.setText("Caricamento dati connessione ...");
        dbConnection = loadDBData();

        statusJTA.setText("Conessione al database ...");
        doConnection();

        if (account != null && dbConnection != null) {
            startIcon.setIcon(new ImageIcon("Data/power-button-outline32green.png"));
            progressBar1.setValue(30);
        }

        if (connection != null) {
            connectionIcon.setIcon(new ImageIcon("Data/database32green.png"));
            progressBar1.setValue(70);
        }

        tryToRead = tryToRead();

        if (tryToRead) {
            fileIcon.setIcon(new ImageIcon("Data/file32green.png"));
            progressBar1.setValue(70);
        }

        downloadIcon.setIcon(new ImageIcon("Data/cloud-computing32green.png"));
        progressBar1.setValue(100);

        statusJTA.setText("Avvio terminato");

        new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (connection != null || tryToRead) {
                            Interface.createAndShowGUI(connection, dbConnection, account,
                                    StartProgramInterface.this);

                            frame.dispose();
                        }
                    }
                });
            }
        });
    }

    private boolean tryToRead() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Data/BackupAnimatori.data")));
            reader.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public void doConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbConnection.getDbHost() + "/" + dbConnection.getDbName() +
                    "?user=" + dbConnection.getDbUser() + "&password=" + dbConnection.getDbPassword());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Object fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = DatatypeConverter.parseBase64Binary(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    private DBConnection loadDBData() {
        try {
            ObjectInputStream reader
                    = new ObjectInputStream(new FileInputStream(new File(
                    "Data/DBData.data")));

            DBConnection dbConnection = (DBConnection) reader.readObject();

            dbConnection.setErrors("Dati caricati correttamente!");

            return dbConnection;
        } catch (Exception e) {
            e.printStackTrace();
            return new DBConnection("", "", "", "", "",
                    "Impossibile leggere il file");
        }
    }

    public Account loadAccountData() {
        try {
            ObjectInputStream reader
                    = new ObjectInputStream(new FileInputStream(new File(
                    "Data/AccountData.data")));

            Account account = (Account) reader.readObject();

            account.setErrors("Dati caricati correttamente!");

            return account;
        } catch (Exception e) {
            e.printStackTrace();
            return new Account("", "",
                    "Impossibile leggere il file");
        }
    }

    public Connection getConnection() {
        return connection;
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Avvio");

        JComponent newContentPane = new StartProgramInterface(frame).getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
