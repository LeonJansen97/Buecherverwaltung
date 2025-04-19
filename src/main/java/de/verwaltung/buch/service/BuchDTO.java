package de.verwaltung.buch.service;

public class BuchDTO {
    private long id;
    private String autor;
    private String beschreibung;
    private String titel;
    private String veroeffentlichungsJahr;

    public BuchDTO(long id, String titel, String beschreibung, String veroeffentlichungsJahr, String autor) {
        this.id = id;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.veroeffentlichungsJahr = veroeffentlichungsJahr;
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getVeroeffentlichungsJahr() {
        return veroeffentlichungsJahr;
    }

    public void setVeroeffentlichungsJahr(String veroeffentlichungsJahr) {
        this.veroeffentlichungsJahr = veroeffentlichungsJahr;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
