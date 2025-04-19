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
                buch.getBeschreibung()
        );
    }

    public Buch mapToModel(BuchDTO buchDTO) {
        return new Buch(
                buchDTO.getId(),
                buchDTO.getTitel(),
                buchDTO.getAutor(),
                buchDTO.getVeroeffentlichungsJahr(),
                buchDTO.getBeschreibung()
        );
    }
}
