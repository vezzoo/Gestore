package it.lucaVezzoli.Application;

import java.io.Serializable;
import java.util.List;

public class SaveArrayList implements Serializable {
    private List<ICheckBox> iCheckBoxes;

    public SaveArrayList(List<ICheckBox> iCheckBoxes) {
        this.iCheckBoxes = iCheckBoxes;
    }

    public List<ICheckBox> getiCheckBoxes() {
        return iCheckBoxes;
    }

    @Override
    public String toString() {
        return "SaveList{ " +
                "iCheckBoxes=" + iCheckBoxes +
                " }";
    }
}
