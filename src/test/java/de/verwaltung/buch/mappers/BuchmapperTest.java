package de.verwaltung.buch.mappers;


import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.dtos.BuchDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuchmapperTest {
    // cut steht für class under test
    private final Buchmapper cut = new Buchmapper();

    @Test
    public void testMapToDTO() {
        // arrange
        Buch gespeichert = createTestBuch();

        // act
        BuchDTO dto = cut.mapToDTO(gespeichert);

        // assert
        assertEquals(gespeichert.getId(), dto.getId());
        assertEquals(gespeichert.getTitel(), dto.getTitel());
        assertEquals(gespeichert.getAutor(), dto.getAutor());
        assertEquals(gespeichert.getVeroeffentlichungsJahr(), dto.getVeroeffentlichungsJahr());
        assertEquals(gespeichert.getBeschreibung(), dto.getBeschreibung());
        assertFalse(dto.isGeloescht());
    }

    @Test
    public void testMapToModel() {
        // arrange
        BuchDTO zuspeichern = createTestBuchDTO();

        // act
        Buch gespeichert = cut.mapToModel(zuspeichern);

        // assert
        assertEquals(zuspeichern.getId(), gespeichert.getId());
        assertEquals(zuspeichern.getTitel(), gespeichert.getTitel());
        assertEquals(zuspeichern.getAutor(), gespeichert.getAutor());
        assertEquals(zuspeichern.getVeroeffentlichungsJahr(), gespeichert.getVeroeffentlichungsJahr());
        assertEquals(zuspeichern.getBeschreibung(), gespeichert.getBeschreibung());
        assertFalse(gespeichert.isGeloescht());
    }

    private Buch createTestBuch() {
        Buch buch = new Buch();
        buch.setId(1L);
        buch.setTitel("Titel");
        buch.setAutor("Autor");
        buch.setVeroeffentlichungsJahr("2023");
        buch.setBeschreibung("Beschreibung");
        buch.setGeloescht(false);
        return buch;
    }

    private BuchDTO createTestBuchDTO() {
        BuchDTO dto = new BuchDTO();
        dto.setId(1L);
        dto.setTitel("Titel");
        dto.setAutor("Autor");
        dto.setVeroeffentlichungsJahr("2023");
        dto.setBeschreibung("Beschreibung");
        dto.setGeloescht(false);
        return dto;
    }
}
