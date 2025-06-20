package de.verwaltung.buch.application.service;

import de.verwaltung.buch.persistence.entities.BuchEntity;
import de.verwaltung.buch.persistence.repositories.BuchRepository;
import de.verwaltung.buch.dtos.BuchDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class BuchServiceTest {

    @Mock
    private BuchRepository buchRepository;

    // cut steht für class under test
    @InjectMocks
    private BuchService cut;

    @Test
    public void testAddBookToInventary() {
        // given
        BuchDTO dto = createTestBuchDTO();
        BuchEntity savedEntity = createTestEntity();

        // when
        when(buchRepository.save(any(BuchEntity.class))).thenReturn(savedEntity);

        BuchDTO ergebnis = cut.addBookToInventary(dto);

        // then
        verify(buchRepository, times(1)).save(any(BuchEntity.class));

        assertEquals("Titel", ergebnis.getTitel());
        assertEquals("Autor", ergebnis.getAutor());
        assertEquals("2023", ergebnis.getVeroeffentlichungsJahr());
        assertEquals("Beschreibung", ergebnis.getBeschreibung());
        assertFalse(ergebnis.isGeloescht());
    }

    @Test
    public void testFindBookById_found() {
        // given
        BuchEntity gespeichert = createTestEntity();

        // when
        when(buchRepository.findById(anyLong())).thenReturn(Optional.of(gespeichert));

        BuchDTO ergebnis = cut.findBookById(1L);

        // then
        verify(buchRepository, times(1)).findById(anyLong());

        assertNotNull(ergebnis);
        assertEquals("Titel", ergebnis.getTitel());
    }

    @Test
    public void testFindBookById_notFound() {
        // when
        when(buchRepository.findById(999L)).thenReturn(Optional.empty());
        BuchDTO result = cut.findBookById(999L);

        // then
        verify(buchRepository, times(1)).findById(anyLong());
        assertNull(result);
    }

    @Test
    public void testSetDeleteFlag() {
        // given
        BuchEntity gespeichert = createTestEntity();
        BuchEntity geloescht = createTestEntity();
        geloescht.setGeloescht(true);

        // when
        when(buchRepository.findById(1L)).thenReturn(Optional.of(gespeichert));
        when(buchRepository.save(any(BuchEntity.class))).thenReturn(geloescht);

        BuchDTO ergebnis = cut.setDeleteFlag(1L);

        // then
        verify(buchRepository, times(1)).findById(anyLong());
        verify(buchRepository, times(1)).save(any(BuchEntity.class));

        assertTrue(ergebnis.isGeloescht());
    }

    @Test
    public void testUndoDelete() {
        // given
        BuchEntity gespeichert = createTestEntity();
        gespeichert.setGeloescht(true);
        BuchEntity nichtGeloescht = createTestEntity();
        nichtGeloescht.setGeloescht(false);

        // when
        when(buchRepository.findById(anyLong())).thenReturn(Optional.of(gespeichert));
        when(buchRepository.save(any(BuchEntity.class))).thenReturn(nichtGeloescht);

        BuchDTO ergebnis = cut.undoDelete(2L);

        // then
        verify(buchRepository, times(1)).findById(anyLong());
        verify(buchRepository, times(1)).save(any(BuchEntity.class));

        assertFalse(ergebnis.isGeloescht());
    }

    @Test
    public void testFindAllBooksDeleted() {
        // given
        BuchEntity gespeichert = createTestEntity();
        gespeichert.setGeloescht(true);
        List<BuchEntity> geloeschteBuecher = List.of(gespeichert);

        // when
        when(buchRepository.findByGeloeschtTrue()).thenReturn(geloeschteBuecher);

        List<BuchDTO> ergebnis = cut.findAllBooksDeleted();

        // then
        verify(buchRepository, times(1)).findByGeloeschtTrue();

        assertEquals(1, ergebnis.size());
        assertTrue(ergebnis.getFirst().isGeloescht());
    }

    @Test
    public void testFindAllBooksNotDeleted() {
        // given
        List<BuchEntity> aktiveBuecher = List.of(createTestEntity());

        // when
        when(buchRepository.findByGeloeschtFalse()).thenReturn(aktiveBuecher);

        List<BuchDTO> result = cut.findAllBooksNotDeleted();

        // then
        verify(buchRepository, times(1)).findByGeloeschtFalse();

        assertEquals(1, result.size());
        assertFalse(result.getFirst().isGeloescht());
    }

    @Test
    public void testSetDeleteFlag_throwsWhenNotFound() {
        // when
        when(buchRepository.findById(anyLong())).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> cut.setDeleteFlag(99L));

        // then
        verify(buchRepository, times(1)).findById(anyLong());

        assertEquals("Buch mit der ID 99 nicht gefunden", ex.getMessage());
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

    private BuchEntity createTestEntity() {
        BuchEntity buchEntity = new BuchEntity();
        buchEntity.setId(1L);
        buchEntity.setTitel("Titel");
        buchEntity.setAutor("Autor");
        buchEntity.setVeroeffentlichungsJahr("2023");
        buchEntity.setBeschreibung("Beschreibung");
        buchEntity.setGeloescht(false);
        return buchEntity;
    }
}
