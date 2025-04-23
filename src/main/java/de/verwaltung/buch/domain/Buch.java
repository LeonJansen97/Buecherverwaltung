package de.verwaltung.buch.domain;

import jakarta.persistence.*;

@Entity
public class Buch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titel;
    @Column(nullable = false)
    private String beschreibung;
    @Column(nullable = false)
    private String veroeffentlichungsJahr;
    @Column(nullable = false)
    private String autor;
    @Column(nullable = false)
    private boolean geloescht = false;

    public Buch(
            long id,
            String titel,
            String autor,
            String veroeffentlichungsJahr,
            String beschreibung,
            boolean geloescht) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
        this.veroeffentlichungsJahr = veroeffentlichungsJahr;
        this.beschreibung = beschreibung;
        this.geloescht = geloescht;
    }

    public Buch() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
