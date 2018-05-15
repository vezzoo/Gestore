package it.lucaVezzoli.Application;

import java.io.Serializable;

public class AnimazioneEstiva implements Serializable{
    private String nomeAttivita;
    private String partecipazione;

    public AnimazioneEstiva(String nomeAttivita, String partecipazione) {
        this.nomeAttivita = nomeAttivita;
        this.partecipazione = partecipazione;
    }

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    public String getPartecipazione() {
        return partecipazione;
    }

    @Override
    public String toString() {
        return "AnimazioneEstiva{" +
                "nomeAttivita='" + nomeAttivita + '\'' +
                ", partecipazione='" + partecipazione + '\'' +
                '}';
    }
}
