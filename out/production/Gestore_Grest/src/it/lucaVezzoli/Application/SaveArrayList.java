package it.lucaVezzoli.Application;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class SaveArrayList implements Serializable {
    private Set<ICheckBox> iCheckBoxes;

    public SaveArrayList(Set<ICheckBox> iCheckBoxes) {
        this.iCheckBoxes = iCheckBoxes;
    }

    public Set<ICheckBox> getiCheckBoxes() {
        return iCheckBoxes;
    }

    @Override
    public String toString() {
        return "SaveList{ " +
                "iCheckBoxes=" + iCheckBoxes +
                " }";
    }
}
