package de.verwaltung.buch.repositories;

import de.verwaltung.buch.domain.Buch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuchRepository extends CrudRepository<Buch, Long> {
    List<Buch> findByGeloeschtFalse();

    List<Buch> findByGeloeschtTrue();

    Buch findByTitel(String titel);
}
