package it.lucaVezzoli.Application;

import java.io.Serializable;

public class DataPersonalizzata implements Serializable {
    private int giorno;
    private int mese;
    private int anno;

    public DataPersonalizzata(int giorno, int mese, int anno) {
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
    }

    public int getAnno() {
        return anno;
    }

    public int getGiorno() {
        return giorno;
    }

    public int getMese() {
        return mese;
    }

    @Override
    public String toString() {
        return "" + giorno +
                "/" + mese +
                "/" + anno;
    }
}
