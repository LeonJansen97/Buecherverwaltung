package de.verwaltung.buch.mappers;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.persistence.entities.BuchEntity;

public class Buchmapper {

    public Buch toDomain(BuchDTO buchDTO) {
        Buch buch = new Buch();
        buch.setId(buchDTO.getId());
        buch.setTitel(buchDTO.getTitel());
        buch.setAutor(buchDTO.getAutor());
        buch.setVeroeffentlichungsJahr(buchDTO.getVeroeffentlichungsJahr());
        buch.setBeschreibung(buchDTO.getBeschreibung());
        buch.setGeloescht(buchDTO.isGeloescht());
        return buch;
    }

    public BuchDTO toDTO(Buch buchDomain) {
        return new BuchDTO(
                buchDomain.getId(),
                buchDomain.getTitel(),
                buchDomain.getAutor(),
                buchDomain.getVeroeffentlichungsJahr(),
                buchDomain.getBeschreibung(),
                buchDomain.isGeloescht()
        );
    }

    public Buch toDomain(BuchEntity buchEntity) {
        Buch buch = new Buch();
        buch.setId(buchEntity.getId());
        buch.setTitel(buchEntity.getTitel());
        buch.setAutor(buchEntity.getAutor());
        buch.setVeroeffentlichungsJahr(buchEntity.getVeroeffentlichungsJahr());
        buch.setBeschreibung(buchEntity.getBeschreibung());
        buch.setGeloescht(buchEntity.isGeloescht());
        return buch;
    }

    public BuchEntity updateEntity(Buch buchDomain) {
        BuchEntity buchEntity = new BuchEntity();
        buchEntity.setId(buchDomain.getId());
        buchEntity.setTitel(buchDomain.getTitel());
        buchEntity.setAutor(buchDomain.getAutor());
        buchEntity.setVeroeffentlichungsJahr(buchDomain.getVeroeffentlichungsJahr());
        buchEntity.setBeschreibung(buchDomain.getBeschreibung());
        buchEntity.setGeloescht(buchDomain.isGeloescht());
        return buchEntity;
    }

    public BuchEntity toEntity(Buch buchDomain) {
        BuchEntity buchEntity = new BuchEntity();
        buchEntity.setTitel(buchDomain.getTitel());
        buchEntity.setAutor(buchDomain.getAutor());
        buchEntity.setVeroeffentlichungsJahr(buchDomain.getVeroeffentlichungsJahr());
        buchEntity.setBeschreibung(buchDomain.getBeschreibung());
        buchEntity.setGeloescht(buchDomain.isGeloescht());
        return buchEntity;
    }
}
