package de.verwaltung.buch.application.service;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.persistence.entities.BuchEntity;
import de.verwaltung.buch.persistence.repositories.BuchRepository;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.mappers.Buchmapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuchService {
    private final BuchRepository buchRepository;
    private final Buchmapper buchmapper = new Buchmapper();
    public BuchService (BuchRepository buchRepository) {
        this.buchRepository = buchRepository;
    }

    public BuchDTO addBookToInventary(BuchDTO buchDTO) {
        Buch buchDomain = buchmapper.toDomain(buchDTO);

        BuchEntity savedEntity = buchRepository.save(buchmapper.toEntity(buchDomain));

        return buchmapper.toDTO(buchmapper.toDomain(savedEntity));
    }

    public BuchDTO updateBookInInventary(BuchDTO editedBuchDTO) {
        Buch editedBuchDomain = buchmapper.toDomain(editedBuchDTO);

        BuchEntity editedEntity = buchRepository.save(buchmapper.updateEntity(editedBuchDomain));

        return buchmapper.toDTO(buchmapper.toDomain(editedEntity));
    }

    public BuchDTO findBookById(Long id) {
        Optional<BuchEntity> optionalBuchEntity = buchRepository.findById(id);

        return optionalBuchEntity
                .map(buchmapper::toDomain)
                .map(buchmapper::toDTO)
                .orElse(null);
    }

    public BuchDTO findBuchByTitel(String titel) {
        BuchEntity buchEntity = buchRepository.findByTitel(titel);
        if (buchEntity == null) {
            return null;
        }
        return buchmapper.toDTO(buchmapper.toDomain(buchEntity));
    }

    public List<BuchDTO> findAllBooksNotDeleted() {
        return buchRepository.findByGeloeschtFalse()
                .stream()
                .map(buchmapper::toDomain)
                .map(buchmapper::toDTO)
                .toList();
    }

    public BuchDTO setDeleteFlag(Long id) {
        BuchEntity buchEntity = buchRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Buch mit der ID " + id + " nicht gefunden"));

        Buch buch = buchmapper.toDomain(buchEntity);
        buch.markiereAlsGeloescht();

        BuchEntity savedEntity = buchRepository.save(buchmapper.updateEntity(buch));
        return buchmapper.toDTO(buchmapper.toDomain(savedEntity));
    }

    public List<BuchDTO> findAllBooksDeleted() {
        return buchRepository.findByGeloeschtTrue()
                .stream()
                .map(buchmapper::toDomain)
                .map(buchmapper::toDTO)
                .toList();
    }

    public BuchDTO undoDelete(Long id) {
        BuchEntity buchEntity = buchRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Buch mit der ID " + id + " nicht gefunden"));

        Buch buch = buchmapper.toDomain(buchEntity);
        buch.reaktiviere();

        BuchEntity savedEntity = buchRepository.save(buchmapper.updateEntity(buch));
        return buchmapper.toDTO(buchmapper.toDomain(savedEntity));
    }
}
