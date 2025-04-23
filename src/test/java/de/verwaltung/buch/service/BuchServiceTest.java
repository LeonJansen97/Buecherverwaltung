package de.verwaltung.buch.service;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.domain.BuchRepository;
import de.verwaltung.buch.dtos.BuchDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
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
        Buch gespeichert = createTestBuch();

        // when
        when(buchRepository.save(any())).thenReturn(gespeichert);

        BuchDTO ergebnis = cut.addBookToInventary(dto);

        // then
        verify(buchRepository, times(1)).save(any());

        assertEquals("Titel", ergebnis.getTitel());
        assertEquals("Autor", ergebnis.getAutor());
        assertEquals("2023", ergebnis.getVeroeffentlichungsJahr());
        assertEquals("Beschreibung", ergebnis.getBeschreibung());
        assertFalse(ergebnis.isGeloescht());
    }

    @Test
    public void testFindBookById_found() {
        // given
        Buch gespeichert = createTestBuch();

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
        Buch gespeichert = createTestBuch();

        // when
        when(buchRepository.findById(1L)).thenReturn(Optional.of(gespeichert));
        when(buchRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        BuchDTO ergebnis = cut.setDeleteFlag(1L);

        // then
        verify(buchRepository, times(1)).findById(anyLong());
        verify(buchRepository, times(1)).save(any());

        assertTrue(ergebnis.isGeloescht());
    }

    @Test
    public void testUndoDelete() {
        // given
        Buch gespeichert = createTestBuch();
        gespeichert.setGeloescht(true);

        // when
        when(buchRepository.findById(anyLong())).thenReturn(Optional.of(gespeichert));
        when(buchRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        BuchDTO ergebnis = cut.undoDelete(2L);

        // then
        verify(buchRepository, times(1)).findById(anyLong());
        verify(buchRepository, times(1)).save(any());

        assertFalse(ergebnis.isGeloescht());
    }

    @Test
    public void testFindAllBooksDeleted() {
        // given
        Buch gespeichert = createTestBuch();
        gespeichert.setGeloescht(true);
        List<Buch> geloeschteBuecher = List.of(gespeichert);

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
        List<Buch> aktiveBuecher = List.of(createTestBuch());

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
}
