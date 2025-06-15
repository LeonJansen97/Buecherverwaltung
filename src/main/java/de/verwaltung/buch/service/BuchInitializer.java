package de.verwaltung.buch.service;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.repositories.BuchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Transactional
public class BuchInitializer {
    @Bean
    CommandLineRunner init(BuchRepository buchRepository) {
        return args -> {
            if (buchRepository.count() == 0) {
                buchRepository.save(new Buch("Structure and Interpretation of Computer Programs", "Harold Abelson, Gerald Jay Sussman", "1985", "Informatik", false));
                buchRepository.save(new Buch("Concrete Mathematics: A Foundation for Computer Science", "Ronald L. Graham, Donald E. Knuth, Oren Patashnik", "1988", "Mathematik/Informatik", false));
                buchRepository.save(new Buch("Compiler: Prinzipien, Techniken und Werkzeuge", "Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ullman", "1986", "Informatik", false));
                buchRepository.save(new Buch("Einführung in die Allgemeine Betriebswirtschaftslehre", "Günter Wöhe", "1960", "Wirtschaft", false));
                buchRepository.save(new Buch("Dubbel – Taschenbuch für den Maschinenbau", "Heinrich Dubbel", "1914", "Maschinenbau", false));
            }
        };
    }
}
