package de.verwaltung.buch.service;

import de.verwaltung.buch.domain.Buch;
import de.verwaltung.buch.domain.BuchRepository;
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
              buchRepository.save(new Buch("1984", "George Orwell", "1949", "Dystopie", false));
              buchRepository.save(new Buch("Der Prozess", "Franz Kafka", "1925", "Roman", false));
              buchRepository.save(new Buch("Faust", "Johann Wolfgang von Goethe", "1808", "Drama", false));
              buchRepository.save(new Buch("Die Verwandlung", "Franz Kafka", "1915", "Novelle", false));
              buchRepository.save(new Buch("Die Physiker", "Friedrich Dürrenmatt", "1962", "Komödie", false));
          }
        };
    }
}
