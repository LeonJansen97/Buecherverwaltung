package de.verwaltung.buch.domain;

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
        Buch buch = createTestBuch( "Titel", "Autor", "2022", "Beschreibung", false);
        Buch gespeichert = cut.save(buch);

        // assert
        assertEquals("Titel", gespeichert.getTitel());

        // assert
        Buch gefunden = cut.findById(gespeichert.getId()).orElse(null);
        assertNotNull(gefunden);
    }

    @Test
    public void testFindByGeloeschtTrue() {
        // arrange
        Buch buch = createTestBuch("Aktiv", "Autor", "2021", "A", false);
        Buch geloeschtBuch = createTestBuch( "Geloescht", "Autor", "2021", "B", true);
        cut.save(buch);
        cut.save(geloeschtBuch);

        // act
        List<Buch> ergebnis = cut.findByGeloeschtTrue();

        // assert
        assertEquals(1, ergebnis.size());
        assertTrue(ergebnis.getFirst().isGeloescht());
    }

    @Test
    public void testFindByGeloeschtFalse() {
        // arrange
        Buch buch = createTestBuch("Aktiv", "Autor", "2021", "A", false);
        Buch geloeschtBuch = createTestBuch( "Geloescht", "Autor", "2021", "B", true);
        cut.save(buch);
        cut.save(geloeschtBuch);

        // act
        List<Buch> ergebnis = cut.findByGeloeschtFalse();

        // assert
        assertEquals(1, ergebnis.size());
        assertFalse(ergebnis.getFirst().isGeloescht());
    }

    private Buch createTestBuch(
            String titel,
            String autor,
            String veroeffentlichungsJahr,
            String beschreibung,
            boolean geloescht) {
        Buch buch = new Buch();
        buch.setTitel(titel);
        buch.setAutor(autor);
        buch.setVeroeffentlichungsJahr(veroeffentlichungsJahr);
        buch.setBeschreibung(beschreibung);
        buch.setGeloescht(geloescht);
        return buch;
    }
}
