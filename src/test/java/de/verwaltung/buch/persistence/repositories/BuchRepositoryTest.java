package de.verwaltung.buch.persistence.repositories;

import de.verwaltung.buch.persistence.entities.BuchEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class BuchRepositoryTest {
    // cut steht für class under test
    @Autowired
    private BuchRepository cut;

    @Test
    public void testSaveAndFindById() {
        // arrange
        BuchEntity buch = createTestBuch("Titel", "Autor", "2022", "Beschreibung", false);
        BuchEntity gespeichert = cut.save(buch);

        // assert
        assertEquals("Titel", gespeichert.getTitel());

        // assert
        BuchEntity gefunden = cut.findById(gespeichert.getId()).orElse(null);
        assertNotNull(gefunden);
        assertEquals("Titel", gefunden.getTitel());
    }

    @Test
    public void testFindByGeloeschtTrue() {
        // arrange
        BuchEntity aktiv = createTestBuch("Aktiv", "Autor", "2021", "A", false);
        BuchEntity geloescht = createTestBuch("Geloescht", "Autor", "2021", "B", true);
        cut.save(aktiv);
        cut.save(geloescht);

        // act
        List<BuchEntity> ergebnis = cut.findByGeloeschtTrue();

        // assert
        assertEquals(1, ergebnis.size());
        assertTrue(ergebnis.getFirst().isGeloescht());
    }

    @Test
    public void testFindByGeloeschtFalse() {
        // arrange
        BuchEntity aktiv = createTestBuch("Aktiv", "Autor", "2021", "A", false);
        BuchEntity geloescht = createTestBuch("Geloescht", "Autor", "2021", "B", true);
        cut.save(aktiv);
        cut.save(geloescht);

        // act
        List<BuchEntity> ergebnis = cut.findByGeloeschtFalse();

        // assert
        assertEquals(1, ergebnis.size());
        assertFalse(ergebnis.getFirst().isGeloescht());
    }

    private BuchEntity createTestBuch(
            String titel,
            String autor,
            String veroeffentlichungsJahr,
            String beschreibung,
            boolean geloescht) {
        BuchEntity buch = new BuchEntity();
        buch.setTitel(titel);
        buch.setAutor(autor);
        buch.setVeroeffentlichungsJahr(veroeffentlichungsJahr);
        buch.setBeschreibung(beschreibung);
        buch.setGeloescht(geloescht);
        return buch;
    }
}
