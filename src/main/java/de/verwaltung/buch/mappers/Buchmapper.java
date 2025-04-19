package de.verwaltung.buch.mappers;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.service.BuchDTO;

public class Buchmapper {
    public BuchDTO mapToDTO(Buch buch) {
        return new BuchDTO(
                buch.getId(),
                buch.getAutor(),
                buch.getTitel(),
                buch.getBeschreibung(),
                buch.getVeroeffentlichungsJahr()
        );
    }

    public Buch mapToModel(BuchDTO buchDTO) {
        return new Buch(
                buchDTO.getId(),
                buchDTO.getAutor(),
                buchDTO.getTitel(),
                buchDTO.getBeschreibung(),
                buchDTO.getVeroeffentlichungsJahr()
        );
    }
}
