# Übung 4
Erstellen Sie die graphische Oberfläche für die Geschäftslogik.

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
- [X] main-Methoden nur im default package des module belegProg3
- [X] ausführbar
- [X] CRUD für mindestens einen Frachttyp in der GUI _darstellung der eingelagerten Hazards mittels abruf funktioniert leider noch nicht_
- [X] Geschäfts- und Darstellungslogik getrennt

### empfohlene Realisierungen als Vorbereitung auf den Beleg
werden überprüft (aber nicht bewertet), wenn hier in der vorgegebenen Reihenfolge als bearbeitet angegeben
- [X] Auflistungen sind immer sichtbar und werden automatisch aktualisiert _bei jedem Knopfdruck jedenfalls, bei der Sortierung leider nicht_
- [X] FXML verwendet
- [X] sortierbare Darstellung der Frachtstücke mit Platz, Kund*in, Inspektionsdatum und Einlagerungsdauer _gibt fertige implementierung googlen!_
- [X] skalierbare Darstellung _container nutzen_
- [X] data binding verwendet _im CargoItem um die Storage Location beim drag&drop anzupassen_
- [ ] Benutzeroberfläche wird nicht gesperrt (Nebenläufigkeit) _ich glaube die Oberfläche wird nicht gesperrt, weiß nicht so richtig was damit gemeint ist_
- [X] Austausch der Plätze mittels drag&drop

