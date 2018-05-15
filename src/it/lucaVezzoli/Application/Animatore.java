package it.lucaVezzoli.Application;

import java.io.Serializable;
import java.util.Arrays;

public class Animatore implements Serializable {
    private int id;
    private String cognome;
    private String nome;
    private int anno;
    private int abc_incontri_partecipato;
    private int abc_incontri_totali;
    private AnimazioneEstiva[] animazioneEstiva;
    private String impegni;
    private GruppiPreferenza[] gruppiPreferenza;
    private boolean responsabile;

    public Animatore() {
        this.id = -1;
    }

    public Animatore(String cognome, String nome, int anno,
                     int abc_incontri_partecipato, int abc_incontri_totali,
                     AnimazioneEstiva[] animazioneEstiva, String impegni,
                     GruppiPreferenza[] gruppiPreferenza, boolean responsabile) {
        this.cognome = cognome;
        this.nome = nome;
        this.anno = anno;
        this.abc_incontri_partecipato = abc_incontri_partecipato;
        this.abc_incontri_totali = abc_incontri_totali;
        this.animazioneEstiva = animazioneEstiva;
        this.impegni = impegni;
        this.gruppiPreferenza = gruppiPreferenza;
        this.responsabile = responsabile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getAnno() {
        return anno;
    }

    public int getAbc_incontri_partecipato() {
        return abc_incontri_partecipato;
    }

    public int getAbc_incontri_totali() {
        return abc_incontri_totali;
    }

    public AnimazioneEstiva[] getAnimazioneEstiva() {
        return animazioneEstiva;
    }

    public String getImpegni() {
        return impegni;
    }

    public GruppiPreferenza[] getGruppiPreferenza() {
        return gruppiPreferenza;
    }

    public boolean getResponsabile() {
        return responsabile;
    }

    @Override
    public String toString() {
        return "Animatore{" +
                "cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", anno=" + anno +
                ", abc_incontri_partecipato=" + abc_incontri_partecipato +
                ", abc_incontri_totali=" + abc_incontri_totali +
                ", animazioneEstiva=" + Arrays.toString(animazioneEstiva) +
                ", impegni='" + impegni + '\'' +
                ", gruppiPreferenza=" + Arrays.toString(gruppiPreferenza) +
                '}';
    }
}
