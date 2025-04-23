package de.verwaltung.buch.service;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.domain.BuchRepository;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.mappers.Buchmapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BuchService {
    private final BuchRepository buchRepository;
    private final Buchmapper buchmapper = new Buchmapper();
    public BuchService (BuchRepository buchRepository) {
        this.buchRepository = buchRepository;
    }

    public BuchDTO addBookToInventary(BuchDTO buchDTO) {
        Buch buch = buchmapper.mapToModel(buchDTO);
        return buchmapper.mapToDTO(buchRepository.save(buch));
    }

    public BuchDTO findBookById(long id) {
        return buchRepository.findById(id)
                .map(buchmapper::mapToDTO)
                .orElse(null);
    }

    public List<BuchDTO> findAllBooksNotDeleted() {
        return buchRepository.findByGeloeschtFalse()
                .stream()
                .map(buchmapper::mapToDTO)
                .toList();
    }

    public BuchDTO setDeleteFlag(long id) {
        Buch buch = buchRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Buch mit der ID " + id + " nicht gefunden"));
        buch.setGeloescht(true);
        return buchmapper.mapToDTO(buchRepository.save(buch));
    }

    public List<BuchDTO> findAllBooksDeleted() {
        return buchRepository.findByGeloeschtTrue()
                .stream()
                .map(buchmapper::mapToDTO)
                .toList();
    }

    public BuchDTO undoDelete(long id) {
        Buch buch = buchRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Buch mit der ID " + id + " nicht gefunden"));
        buch.setGeloescht(false);
        return buchmapper.mapToDTO(buchRepository.save(buch));
    }
}
