package de.verwaltung.buch.mappers;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.dtos.BuchDTO;

public class Buchmapper {
    public BuchDTO mapToDTO(Buch buch) {
        return new BuchDTO(
                buch.getId(),
                buch.getTitel(),
                buch.getAutor(),
                buch.getVeroeffentlichungsJahr(),
                buch.getBeschreibung(),
                buch.isGeloescht()
        );
    }

    public Buch mapToModel(BuchDTO buchDTO) {
        Buch buch = new Buch();
        buch.setId(buchDTO.getId());
        buch.setTitel(buchDTO.getTitel());
        buch.setAutor(buchDTO.getAutor());
        buch.setVeroeffentlichungsJahr(buchDTO.getVeroeffentlichungsJahr());
        buch.setBeschreibung(buchDTO.getBeschreibung());
        buch.setGeloescht(buchDTO.isGeloescht());
        return buch;
    }
}
