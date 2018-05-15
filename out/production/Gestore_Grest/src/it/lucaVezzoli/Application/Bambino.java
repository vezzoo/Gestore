package it.lucaVezzoli.Application;

import java.io.Serializable;

public class Bambino implements Serializable{
    private int Id;
    private String cognome;
    private String nome;
    private DataPersonalizzata dataDiNascita;
    private String classe;
    private String indirizzo;
    private String telefono1;
    private String telefono2;
    private String preferenza;
    private String grest;
    private String settimane;
    private String taglia;
    private String pit_stop;
    private String maglietta;
    private String pagamento;
    private DataPersonalizzata dataSaldo;
    private String entrataAnticipata;
    private String pranzo;
    private String squadra;

    public Bambino(String cognome, String nome,
                   DataPersonalizzata dataDiNascita, String classe,
                   String indirizzo, String telefono1, String telefono2,
                   String preferenza, Object grest, Object settimane,
                   Object taglia, Object pit_stop, Object maglietta,
                   Object pagamento, DataPersonalizzata dataSaldo,
                   Object entrataAnticipata, Object pranzo, String squadra) {
        this.cognome = cognome;
        this.nome = nome;
        this.dataDiNascita = dataDiNascita;
        this.classe = classe;
        this.indirizzo = indirizzo;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.preferenza = preferenza;
        this.grest = (String) grest;
        this.settimane = (String) settimane;
        this.taglia = (String) taglia;
        this.pit_stop = (String) pit_stop;
        this.maglietta = (String) maglietta;
        this.pagamento = (String) pagamento;
        this.dataSaldo = dataSaldo;
        this.entrataAnticipata = (String) entrataAnticipata;
        this.pranzo = (String) pranzo;
        this.squadra = squadra;
    }

    public Bambino(String cognome, String nome,
                   DataPersonalizzata dataDiNascita, String classe,
                   String indirizzo, String telefono1, String telefono2,
                   String preferenza, Object grest, Object settimane,
                   Object taglia, Object pit_stop, Object maglietta,
                   Object pagamento, Object entrataAnticipata, Object pranzo, String squadra) {
        this.cognome = cognome;
        this.nome = nome;
        this.dataDiNascita = dataDiNascita;
        this.classe = classe;
        this.indirizzo = indirizzo;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.preferenza = preferenza;
        this.grest = (String) grest;
        this.settimane = (String) settimane;
        this.taglia = (String) taglia;
        this.pit_stop = (String) pit_stop;
        this.maglietta = (String) maglietta;
        this.pagamento = (String) pagamento;
        this.entrataAnticipata = (String) entrataAnticipata;
        this.pranzo = (String) pranzo;
        this.squadra = squadra;
    }

    public int getAnno() {
        return dataDiNascita.getAnno();
    }

    public int getId() {
        return Id;
    }

    public void setId(int ID) {
        this.Id = ID;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public DataPersonalizzata getDataDiNascita() {
        return dataDiNascita;
    }

    public String getClasse() {
        return classe;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public String getPreferenza() {
        return preferenza;
    }

    public String isGrest() {
        return grest;
    }

    public String getSettimane() {
        return settimane;
    }

    public String getTaglia() {
        return taglia;
    }

    public String isPit_stop() {
        return pit_stop;
    }

    public String getMaglietta() {
        return maglietta;
    }

    public String getPagamento() {
        return pagamento;
    }

    public DataPersonalizzata getDataSaldo() {
        return dataSaldo;
    }

    public String isEntrataAnticipata() {
        return entrataAnticipata;
    }

    public String isPranzo() {
        return pranzo;
    }

    public String getSquadra() {
        return squadra;
    }

    @Override
    public String toString() {
        return "Bambino{" +
                "Id=" + Id +
                ", cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", classe='" + classe + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", telefono1='" + telefono1 + '\'' +
                ", telefono2='" + telefono2 + '\'' +
                ", preferenza='" + preferenza + '\'' +
                ", grest='" + grest + '\'' +
                ", settimane='" + settimane + '\'' +
                ", taglia='" + taglia + '\'' +
                ", pit_stop='" + pit_stop + '\'' +
                ", maglietta='" + maglietta + '\'' +
                ", pagamento='" + pagamento + '\'' +
                ", dataSaldo=" + dataSaldo +
                ", entrataAnticipata='" + entrataAnticipata + '\'' +
                ", pranzo='" + pranzo + '\'' +
                ", squadra='" + squadra + '\'' +
                '}';
    }
}
