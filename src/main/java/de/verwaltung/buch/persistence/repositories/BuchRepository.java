package de.verwaltung.buch.persistence.repositories;

import de.verwaltung.buch.persistence.entities.BuchEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuchRepository extends CrudRepository<BuchEntity, Long> {
    List<BuchEntity> findByGeloeschtFalse();

    List<BuchEntity> findByGeloeschtTrue();

    BuchEntity findByTitel(String titel);
}
