package de.verwaltung.buch.dtos;

public class BuchDTO {
    private long id;
    private String autor;
    private String beschreibung;
    private String titel;
    private String veroeffentlichungsJahr;
    private boolean geloescht;

    public BuchDTO(
            long id,
            String titel,
            String autor,
            String veroeffentlichungsJahr,
            String beschreibung,
            boolean geloescht) {
        this.id = id;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.veroeffentlichungsJahr = veroeffentlichungsJahr;
        this.autor = autor;
        this.geloescht = geloescht;
    }

    public BuchDTO() {}

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

    public boolean isGeloescht() {
        return geloescht;
    }

    public void setGeloescht(boolean geloescht) {
        this.geloescht = geloescht;
    }
}
