package de.verwaltung.buch.persistence.entities;

import jakarta.persistence.*;

@Entity
public class BuchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public BuchEntity() {}

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
