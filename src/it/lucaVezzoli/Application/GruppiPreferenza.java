package it.lucaVezzoli.Application;

import java.io.Serializable;

public class GruppiPreferenza implements Serializable{
    private String gruppo;
    private String voto;

    public GruppiPreferenza(String gruppo, String voto) {
        this.gruppo = gruppo;
        this.voto = voto;
    }

    public String getGruppo() {
        return gruppo;
    }

    public String getVoto() {
        return voto;
    }

    @Override
    public String toString() {
        return "GruppiPreferenza{" +
                "gruppo='" + gruppo + '\'' +
                ", voto=" + voto +
                '}';
    }
}
