package it.lucaVezzoli.Application;

import it.lucaVezzoli.Interface.StartProgramInterface;

import javax.swing.*;

public class EntryPoint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartProgramInterface.createAndShowGUI();
            }
        });
    }
}
