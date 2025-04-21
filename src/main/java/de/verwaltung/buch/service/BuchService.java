package de.verwaltung.buch.service;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.domain.BuchRepository;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.mappers.Buchmapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class BuchService {
    private final BuchRepository buchRepository;
    private final Buchmapper buchmapper = new Buchmapper();
    public BuchService (BuchRepository buchRepository) {
        this.buchRepository = buchRepository;
    }

    public List<BuchDTO> findAllBooks() {
        return StreamSupport
                .stream(
                        buchRepository.findAll().spliterator(),
                        false)
                .map(buchmapper::mapToDTO)
                .toList();
    }

    public void addBookToInventary(BuchDTO buchDTO) {
        Buch buch = buchmapper.mapToModel(buchDTO);
        buchRepository.save(buch);
    }

    public BuchDTO findBookById(long id) {
        return buchRepository.findById(id)
                .map(buchmapper::mapToDTO)
                .orElse(null);
    }
}
