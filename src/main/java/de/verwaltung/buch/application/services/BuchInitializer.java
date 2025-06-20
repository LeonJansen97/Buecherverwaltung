package de.verwaltung.buch.application.services;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.mappers.Buchmapper;
import de.verwaltung.buch.persistence.entities.BuchEntity;
import de.verwaltung.buch.persistence.repositories.BuchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Transactional
public class BuchInitializer {
    @Bean
    CommandLineRunner init(BuchRepository buchRepository) {
        Buchmapper buchmapper = new Buchmapper();
        return args -> {
            if (buchRepository.count() == 0) {
                List<Buch> buecher = List.of(
                        new Buch("Structure and Interpretation of Computer Programs", "Harold Abelson, Gerald Jay Sussman", "1985", "Informatik", false),
                        new Buch("Concrete Mathematics: A Foundation for Computer Science", "Ronald L. Graham, Donald E. Knuth, Oren Patashnik", "1988", "Mathematik/Informatik", false),
                        new Buch("Compiler: Prinzipien, Techniken und Werkzeuge", "Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ullman", "1986", "Informatik", false),
                        new Buch("Einführung in die Allgemeine Betriebswirtschaftslehre", "Günter Wöhe", "1960", "Wirtschaft", false),
                        new Buch("Dubbel – Taschenbuch für den Maschinenbau", "Heinrich Dubbel", "1914", "Maschinenbau", false)
                );

                List<BuchEntity> entities = buecher.stream()
                        .map(buchmapper::toEntity)
                        .toList();

                buchRepository.saveAll(entities);
            }
        };
    }
}
