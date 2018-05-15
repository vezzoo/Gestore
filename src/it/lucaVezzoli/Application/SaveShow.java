package it.lucaVezzoli.Application;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveShow implements Serializable {
    private ArrayList<JMenuItem> menuItems;

    public SaveShow(ArrayList<JMenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public ArrayList<JMenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public String toString() {
        return "SaveShow{" +
                "menuItems=" + menuItems +
                '}';
    }
}
