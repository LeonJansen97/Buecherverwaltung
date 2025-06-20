# Buecherverwaltung README

## Projekt starten - Schritt für Schritt

### Voraussetzungen

Stelle sicher, dass folgende Software installiert ist:

- Java 21
- Git (optional, falls das Projekt aus dem Repository geklont werden soll)
- IDE der Wahl (IntelliJ, Eclipse, VS Code mit Java Plugin)

### Projekt einrichten
1. Repository klonen (falls noch nicht vorhanden):
```bash
git clone https://github.com/LeonJansen97/Buecherverwaltung.git
cd buchverwaltung
```
2. Das Projekt in der IDE öffnen
- Projektordner in InteliJ oder Eclipse öffnen
- Als Maven-Projekt importieren
3. Abhängigkeiten auflösen
- Entweder die IDE lädt die Abhängigkeiten automatisch
- Oder es kann über die Konsole erledigt werden:
```bash
mvn clean install
```
4. Anwendung starten
- In der IDE: Starte die Klasse BuchverwaltungApplication
- Oder über die Konsole:
```bash
mvn spring-boot:run
```

#### Anwendung aufrufen
http://localhost:8080
Die Startseite zeigt eine Liste von Büchern und bietet Funktionen zum Hinzufügen, Bearbeiten und weichen Löschen (Soft-Delete).

#### Initialdaten
Beim ersten Start wird die Datenbank automatisch mit Beispieldaten gefüllt, falss sie leer ist. Diese befinden sich in der Klasse BuchInitializer.java.

#### Projektstruktur (Auszug)
```bash
src/main/java/de/verwaltung
├── base        # Technische UI-Komponenten wie Layout & ErrorHandler
│   └──  ui  
│        └──  view        # Enthält Fehlerbehandlung und Basislayout
│             ├── errorhandling   # Fehlerbehandlung
│             └── layout          # basislayout von Views  
├── buch        # Fachlogik zur Buchverwaltung
│   ├── application
│   │   └── services      # Geschäftslogik und Initialdaten
│   ├── domain        # Buch-Domänenobjekt
│   ├── dtos          # Datenobjekte zur Trennung von Oberfläche und Domäne
│   ├── mappers       # Mapper für das Mapping zum DTO und zurück zur Entity
│   ├── persistence   # enhält Entitäten und Repositories
│   │   ├── entities      # Buch-Entität
│   │   └── repositories  # Buch-Repository zum Datenbankzugriff 
│   └── ui            # Views und Komponenten
```

#### Weitere Hinweise
- Die Anwendung verwendet eine In-Memory H2-Datenbank (konfigurierbar über application.properties)
  - Zufriff auf die H2-Konsole:
  http://localhost:8080/h2-console  
  
- Vaadin Version: 24.7.2

#### Häufige Probleme
- Port 8080 ist belegt -> application.properties anpassen und einen anderen Port angeben oder die Anwendung, die auf Port 8080 läuft beenden
- UI wird nicht angezeigt -> Browser-Cache leeren oder Inkognito-Modus verwenden
- Java-Version inkompatibel -> Prüfen, ob auch wirklich Java-Version 21 verwendet wird