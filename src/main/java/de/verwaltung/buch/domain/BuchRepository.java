package de.verwaltung.buch.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuchRepository extends CrudRepository<Buch, Long> {}
