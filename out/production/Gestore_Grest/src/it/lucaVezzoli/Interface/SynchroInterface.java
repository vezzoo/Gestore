package it.lucaVezzoli.Interface;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Enumeration;

public class SynchroInterface {

    private JPanel mainPanel;
    private JTextField ipJTF;

    public SynchroInterface() {
        try {
            InetAddress address = getIP();
            String ip = address.getHostAddress();
            ipJTF.setText(ip);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static InetAddress getIP() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;

            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress())
                        if (inetAddr.isSiteLocalAddress())
                            return inetAddr;
                        else if (candidateAddress == null)
                            candidateAddress = inetAddr;
                }
            }

            if (candidateAddress != null)
                return candidateAddress;

            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null)
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");

            return jdkSuppliedAddress;
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("");

        JComponent newContentPane = new SynchroInterface().getMainPanel();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
