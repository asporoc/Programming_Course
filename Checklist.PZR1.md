# Beleg PZR1 (93)
Checkboxen befüllen und _kursiv_ gesetzten Text durch entsprechende Angaben ersetzten.
Bei keiner Angabe wird nur Entwurf, Testqualität, Testabdeckung GL, Fehlerfreiheit und Basisfunktionalität bewertet.
Die Zahl in der Klammer sind die jeweiligen Punkte für die Bewertung.
Die empfohlenen Realisierungen zum Bestehen der Prüfung sind **fett** gesetzt.
Ergänzende Anmerkungen bitte immer _kursiv_ setzen. Andere Änderungen sind nicht zulässig.

## Vorrausetzungen für das Bestehen
- [ ] Quellen angegeben
- [ ] zip Archiv mit dem Projekt im root
- [ ] IntelliJ-Projekt (kein Gradle, Maven o.ä.)
- [ ] keine weiteren Bibliotheken außer JUnit5, Mockito und JavaFX
- [ ] keine Umlaute, Sonderzeichen, etc. in Datei- und Pfadnamen
- [ ] mindestens sechs modules (zu jeder Basisfunktionalität außer I/O + belegProg3)
- [ ] Trennung zwischen Test- und Produktiv-Code
- [ ] kompilierbar
- [ ] geforderte main-Methoden nur im default package des module belegProg3

## Entwurf (10)
- [ ] **Schichtenaufteilung** (2)
- [ ] **Architekturdiagramm** (1)
- [ ] Zuständigkeit (2)
- [ ] Paketierung (2)
- [ ] Benennung (2)
- [X] keine Duplikate (1) _Laut IntelliJ Inspection nur zwischen Simulation 1 und 2_

## Tests (28)
- [X] **Testqualität** (7)
- [X] **Testabdeckung GL (inkl. Abhängigkeiten)** (7) _Laut. IntelliJ 100%_
- [ ] **Testabdeckung Rest** (6)
  - [X] Einfügen von Kund*innen über das CLI _getestete Klassen angeben_
  - [X] Anzeigen von Kund*innen über das CLI _getestete Klassen angeben_
  - [X] ein Beobachter bzw. dessen alternative Implementierung _LagerObserver_
  - [ ] deterministische Funktionalität der Simulationen _getestete Klassen angeben_
  - [X] Speichern via JOS oder JBP _JOSItemSerializationUtils_
  - [X] Laden via JOS oder JBP _JOSItemSerializationUtils_
- [X] **mindestens 5 Unittests, die Mockito verwenden** (4)
- [X] mindestens 4 Spy- / Verhaltens-Tests (3)
- [X] keine unbeabsichtigt fehlschlagenden Test (1)

## Fehlerfreiheit (10)
- [ ] **Kapselung** (5)
- [X] **keine Ablauffehler** (5)

## Basisfunktionalität (12)
- [X] **CRUD** (2)
- [X] **CLI** (2)
  * Syntax gemäß Anforderungen
- [X] **Simulation** (2)
  * ohne race conditions
- [X] **GUI** (2)
- [X] **I/O** (2)
  * in CLI oder GUI integriert
- [X] **Net** (2)

## Funktionalität (23)
- [X] vollständige GL (2)
- [X] threadsichere GL (1)
- [X] vollständiges CLI (1)
- [X] alternatives CLI (1)
  * _Kunden entfernen, Frachtstuecke entfernen_
- [X] ausdifferenziertes event-System mit mindestens 3 events (2)
- [X] observer oder property change propagation (2)
- [ ] angemessene Aufzählungstypen (2)
- [X] Simulation 2 (1)
- [ ] Simulation 3 (1)
- [X] skalierbare GUI (1)
- [X] vollständige GUI (1)
- [X] data binding verwendet (1)
- [X] Änderung des Lagerplatzes mittels drag&drop (1)
- [ ] Einfügen von Frachtstücken via GUI erfolgt nebenläufig (1)
- [ ] sowohl JBP als auch JOS (2)
- [ ] sowohl TCP als auch UDP (1)
- [ ] Server unterstützt konkurierende Clients für TCP oder UDP (2)

## zusätzliche Anforderungen (10)
- [X] Logeinträge für Nachrichten an die GL (2)
- [X] Logeinträge für Änderungen an der GL (2)
- [X] Trennung zwischen bestehender Implementierung und Log (2)
- [X] geschützter Zugriff auf die Logdatei (1)
- [ ] Integration in GUI (1)
- [X] Mehrsprachigkeit (1)
- [X] erweiterbare Mehrsprachigkeit (1)

## Architekturdiagramm
![Architekturdiagramm](architecture.png)
