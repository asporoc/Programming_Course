# Übung 3
Erstellen Sie die Simulationen für den Beleg. Jede Simulation soll eine eigene main-Methode haben.

Für die zufällig zu erzeugenden Frachtstücke kann auch eine Liste mit verschiedenen Instanzen bzw. Eigenschaften erzeugt werden, aus der zufällig ausgewählt wird.

## Abgabeanforderungen
Die Abgabe hat als zip-Datei zu erfolgen, die ein lauffähiges IntelliJ-IDEA-Projekt enthält. Sie sollte die befüllte Checkliste im root des Projektes (neben der iml-Datei) enthalten in der der erreichte Stand bezüglich des Bewertungsschemas vermerkt ist.

Änderungen an der Checkliste sind grundsätzlich nicht zulässig. Davon ausgenommen ist das Befüllen der Checkboxen und ergänzende Anmerkungen die _kursiv gesetzt_ sind.

## Quellen
Zulässige Quellen sind suchmaschinen-indizierte Internetseiten. Werden mehr als drei zusammenhängende Anweisungen übernommen ist die Quelle in den Kommentaren anzugeben. Ausgeschlossen sind Quellen, die auch als Beleg oder Übungsaufgabe abgegeben werden oder wurden. Zulässig sind außerdem die über moodle bereitgestellten Materialien, diese können für die Übungsaufgaben und den Beleg ohne Quellenangabe verwendet werden.
Flüchtige Quellen, wie Sprachmodelle, sind per screen shot zu dokumentieren.

## Bewertung
1 Punkt für die Erfüllung des Pflichtteils

### Pflichtteil
- [X] Quellen angegeben
- [X] zip Archiv
- [X] IntelliJ-Projekt (kein Gradle, Maven o.ä.)
- [X] JUnit5 und Mockito als Testframeworks (soweit verwendet)
- [X] keine weiteren Bibliotheken außer JavaFX
- [X] keine Umlaute, Sonderzeichen, etc. in Datei- und Pfadnamen
- [X] kompilierbar
- [X] Trennung zwischen Test- und Produktiv-Code
- [X] main-Methoden nur im default package des module belegProg3 _Ich wusste nicht ob sich das auch auf die Simulations Mains bezieht von daher sind diese in einem simulation 1 bzw. 2 Package_
- [X] ausführbar
- [X] Simulation 1
- [X] Trennung zwischen GL und Simulationslogik
- [X] Aktionen der threads produzieren Ausgaben auf der Konsole

### empfohlene Realisierungen als Vorbereitung auf den Beleg
werden überprüft (aber nicht bewertet), wenn hier in der vorgegebenen Reihenfolge als bearbeitet angegeben
- [ ] keine Verwendung von Thread.sleep o.Ä. bzw. nur mit 0-Werten
- [X] Änderungen an der Geschäftslogik produzieren Ausgaben auf der Konsole _mittels observer_
- [ ] deterministische Funktionalität testbar
- [X] Simulation 2 _nach start der Applikation wird eine Menge an Threads angegeben und diese wird dann ausgeführt_
- [ ] mindestens je ein Test für alle in der Simulation verwendeten Methoden die auf die Geschäftslogik zugreifen
- [ ] Simulation 3
- [ ] alle Tests sind deterministisch

