package de.verwaltung.buch.domain;

public class Buch {

    private Long id;
    private String titel;
    private String beschreibung;
    private String veroeffentlichungsJahr;
    private String autor;
    private boolean geloescht = false;

    public Buch(
            String titel,
            String autor,
            String veroeffentlichungsJahr,
            String beschreibung,
            boolean geloescht) {
        this.titel = titel;
        this.autor = autor;
        this.veroeffentlichungsJahr = veroeffentlichungsJahr;
        this.beschreibung = beschreibung;
        this.geloescht = geloescht;
    }

    public Buch() {}

    public void markiereAlsGeloescht() {
        this.geloescht = true;
    }

    public void reaktiviere() {
        this.geloescht = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getVeroeffentlichungsJahr() {
        return veroeffentlichungsJahr;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isGeloescht() {
        return geloescht;
    }

    public void setGeloescht(boolean geloescht) {
        this.geloescht = geloescht;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setVeroeffentlichungsJahr(String number) {
        this.veroeffentlichungsJahr = number;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
