package it.lucaVezzoli.Application;

import javax.swing.*;

public class ICheckBox extends JCheckBox{
    private String id;

    public ICheckBox(String text, String id) {
        super(text);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ICheckBox{" +
                "id='" + id + '\'' +
                '}';
    }
}
