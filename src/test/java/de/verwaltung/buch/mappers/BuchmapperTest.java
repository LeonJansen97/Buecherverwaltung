package de.verwaltung.buch.mappers;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.persistence.entities.BuchEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuchmapperTest {
    // cut steht für class under test
    private final Buchmapper cut = new Buchmapper();

    @Test
    public void testMapBuchEntityToBuchDomainAndBack() {
        BuchEntity entity = createTestBuchEntity();
        Buch domain = cut.toDomain(entity);
        BuchEntity result = cut.toEntity(domain);

        assertEquals(entity.getTitel(), result.getTitel());
        assertEquals(entity.getAutor(), result.getAutor());
        assertEquals(entity.getVeroeffentlichungsJahr(), result.getVeroeffentlichungsJahr());
        assertEquals(entity.getBeschreibung(), result.getBeschreibung());
        assertEquals(entity.isGeloescht(), result.isGeloescht());
    }

    @Test
    public void testMapBuchDomainToBuchDTOAndBack() {
        Buch domain = createTestBuchDomain();
        BuchDTO dto = cut.toDTO(domain);
        Buch domainAgain = cut.toDomain(dto);

        assertEquals(domain.getTitel(), dto.getTitel());
        assertEquals(domain.getTitel(), domainAgain.getTitel());
        assertEquals(domain.isGeloescht(), domainAgain.isGeloescht());
    }

    @Test
    public void testMapBuchEntityToBuchDTOViaDomain() {
        BuchEntity entity = createTestBuchEntity();
        BuchDTO dto = cut.toDTO(cut.toDomain(entity));

        assertEquals(entity.getTitel(), dto.getTitel());
        assertEquals(entity.getAutor(), dto.getAutor());
    }

   private BuchEntity createTestBuchEntity() {
        BuchEntity buch = new BuchEntity();
        buch.setId(1L);
        buch.setTitel("Titel");
        buch.setAutor("Autor");
        buch.setVeroeffentlichungsJahr("2023");
        buch.setBeschreibung("Beschreibung");
        buch.setGeloescht(false);
        return buch;
    }

    private Buch createTestBuchDomain() {
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
