package it.lucaVezzoli.Application;

import javax.swing.*;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ICheckBox iCheckBox = (ICheckBox) o;
        return Objects.equals(id, iCheckBox.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ICheckBox{" +
                "id='" + id + '\'' +
                '}';
    }
}
