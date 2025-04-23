package de.verwaltung.buch.service;

import de.verwaltung.buch.dtos.BuchDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
class BuchServiceIntegrationTest {

    @Autowired
    BuchService buchService;

    @Test
    public void testBuchHinzufuegen() {
        // arrange und act
        BuchDTO gespeichert = saveTestBuch("Hinzufuegen-Test");

        // assert
        assertEquals("Hinzufuegen-Test", gespeichert.getTitel());
        assertEquals("Anthony Horrowitz", gespeichert.getAutor());
        assertEquals("2010", gespeichert.getVeroeffentlichungsJahr());
        assertEquals("Testbeschreibung", gespeichert.getBeschreibung());
    }

    @Test
    public void testBuchAktualisieren() {
        // arrange
        BuchDTO original = saveTestBuch("Buch aktualisieren");
        original.setTitel("Buch aktualisiert");

        // act
        BuchDTO aktualisiert = buchService.addBookToInventary(original);

        // assert
        assertEquals("Buch aktualisiert", aktualisiert.getTitel());
    }

    @Test
    public void testSoftDelete() {
        // arrange
        BuchDTO gespeichert = saveTestBuch("SoftDelete-Test");

        // act
        BuchDTO geloescht = buchService.setDeleteFlag(gespeichert.getId());

        // assert
        assertTrue(geloescht.isGeloescht());
    }

    @Test
    public void testUndoDelete() {
        // arrange
        BuchDTO gespeichert = saveTestBuch("UndoDelete-Test");
        buchService.setDeleteFlag(gespeichert.getId());

        // act
        BuchDTO wiederhergestellt = buchService.undoDelete(gespeichert.getId());

        // assert
        assertFalse(wiederhergestellt.isGeloescht());
    }

    @Test
    public void testFindBookById() {
        // arrange
        BuchDTO gespeichert = saveTestBuch("FindTest");

        // act
        BuchDTO gefunden = buchService.findBookById(gespeichert.getId());

        // assert
        assertNotNull(gefunden);
        assertEquals("FindTest", gefunden.getTitel());
    }

    @Test
    public void testFindBookByIdNichtVorhanden() {
        // arrange, act und assert
        BuchDTO gefunden = buchService.findBookById(99999L);
        assertNull(gefunden);
    }

    @Test
    public void testFindAllBooksNotDeleted() {
        // arrange
        BuchDTO aktiv = saveTestBuch("Aktiv");
        BuchDTO geloescht = saveTestBuch("Gelöscht");
        buchService.setDeleteFlag(geloescht.getId());

        // act
        List<BuchDTO> aktiveBuecher = buchService.findAllBooksNotDeleted();

        // assert
        assertTrue(aktiveBuecher.stream().anyMatch(b -> b.getId() == aktiv.getId()));
        assertTrue(aktiveBuecher.stream().noneMatch(b -> b.getId() == geloescht.getId()));
    }

    private BuchDTO saveTestBuch(String titel) {
        BuchDTO dto = new BuchDTO();
        dto.setTitel(titel);
        dto.setAutor("Anthony Horrowitz");
        dto.setVeroeffentlichungsJahr("2010");
        dto.setBeschreibung("Testbeschreibung");
        return buchService.addBookToInventary(dto);
    }
}
