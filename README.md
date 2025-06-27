# Buecherverwaltung README

## Projekt einrichten und starten - Schritt für Schritt

### Voraussetzungen

Stelle sicher, dass folgende Software installiert ist:
- Java 21 oder höher
- Git (optional, falls das Projekt aus dem Repository geklont werden soll), es ist auch möglich das Projekt als zip-Datei herunterzuladen
- IDE der Wahl (IntelliJ oder VSCode)
- Maven (falls die Anwendung nachher per maven-Befehl gestartet werden soll)

### Projekt herunterladen
- Repository von Github klonen oder als zip herunterladen (falls noch nicht vorhanden):
```bash
git clone https://github.com/LeonJansen97/Buecherverwaltung.git
cd buchverwaltung
```

### Projekt einrichten
#### IntelliJ
1. Das Projekt in der IDE öffnen
- Projektordner öffnen
2. Abhängigkeiten auflösen
- Die IDE lädt die Abhängigkeiten automatisch

#### VSCode
1. Extensions herunterladen (Extension Pack for Java (von Microsoft)) 
2. Das Projekt in der IDE öffnen
- Projektordner öffnen
3. Abhängigkeiten auflösen
- Die IDE lädt die Abhängigkeiten automatisch

### Hinweis
Die Einrichtung funktioniert bei Eclipse leider nicht so gut, wegen nicht mehr aktueller Plugins

### Start der Anwendung
- In der IDE: Starte die Klasse BuchverwaltungApplication
- Oder über die Konsole (sofern maven installiert und die Umgebungsvariable zum maven-Verzecihnis gesetzt ist):
```bash
mvn spring-boot:run
```

### Anwendung aufrufen
http://localhost:8080

Die Startseite zeigt eine Liste von Büchern und bietet Funktionen zum Hinzufügen, Bearbeiten und weichen Löschen (Soft-Delete).

### Initialdaten
Beim ersten Start wird die Datenbank automatisch mit Beispieldaten gefüllt, falss sie leer ist. Diese befinden sich in der Klasse BuchInitializer.java.

## Projektstruktur (Auszug)
```bash
src/main/java/de/verwaltung
├── base        # Technische UI-Komponenten wie Layout & ErrorHandler
│   └──  ui  
│        └──  view        # Enthält Fehlerbehandlung und Basislayout
│             ├── errorhandling   # Fehlerbehandlung
│             └── layout          # Basislayout von Views  
├── buch        # Fachlogik zur Buchverwaltung
│   ├── application
│   │   └── services      # Geschäftslogik und Initialdaten
│   ├── domain        # Buch-Domänenobjekt
│   ├── dtos          # Datenobjekte zur Trennung von Oberfläche und Domäne
│   ├── mappers       # Mapper für das Mapping zum DTO, zur Domain und zur Entity
│   ├── persistence   # enhält Entitäten und Repositories
│   │   ├── entities      # Buch-Entität
│   │   └── repositories  # Buch-Repository zum Datenbankzugriff 
│   └── ui            # Views und Komponenten
```

## Weitere Hinweise
- Die Anwendung verwendet eine In-Memory H2-Datenbank (konfigurierbar über application.properties)
  - Zufriff auf die H2-Konsole:
  http://localhost:8080/h2-console  
  
- Vaadin Version: 24.7.2

## Häufige Probleme
- Port 8080 ist belegt -> application.properties anpassen und einen anderen Port angeben oder die Anwendung, die auf Port 8080 läuft beenden
- UI wird nicht angezeigt -> Browser-Cache leeren oder Inkognito-Modus verwenden
- Java-Version inkompatibel -> Prüfen, ob auch wirklich Java-Version 21 istalliert und in der IDE ausgewählt ist