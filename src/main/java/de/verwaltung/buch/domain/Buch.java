package de.verwaltung.buch.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Buch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titel;
    private String beschreibung;
    private String veroeffentlichungsJahr;
    private String autor;

    public Buch(long id, String titel, String autor, String veroeffentlichungsJahr, String beschreibung) {
        this.id = id;
        this.titel = titel;
        this.autor = autor;
        this.veroeffentlichungsJahr = veroeffentlichungsJahr;
        this.beschreibung = beschreibung;
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
