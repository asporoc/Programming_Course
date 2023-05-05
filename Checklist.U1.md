#Übung 1
Erstellen Sie die Geschäftslogik des Belegs prototypisch und testen Sie exemplarisch. Für die Übung müssen nicht alle Anforderungen realisiert werden aber mindestens Einfügen, Auflisten, Ändern (Inspektionsdatum) und Entfernen (CRUD) für mindestens einen Typ von den im Vertrag vordefinierten Frachtstücke, z.B. DryBulkCargo.
##Abgabeanforderungen
Die Abgabe hat als zip-Datei zu erfolgen, die ein lauffähiges IntelliJ-IDEA-Projekt enthält. Sie sollte die befüllte Checkliste im root des Projektes (neben der iml-Datei) enthalten in der der erreichte Stand bezüglich des Bewertungsschemas vermerkt ist.
##Quellen
Zulässige Quellen sind suchmaschinen-indizierte Internetseiten. Werden mehr als drei zusammenhängende Anweisungen übernommen ist die Quelle in den Kommentaren anzugeben. Ausgeschlossen sind Quellen, die auch als Beleg oder Übungsaufgabe abgegeben werden oder wurden. Zulässig sind außerdem die über moodle bereitgestellten Materialien, diese können für die Übungsaufgaben und den Beleg ohne Quellenangabe verwendet werden.
Flüchtige Quellen, wie Sprachmodelle, sind per screen shot zu dokumentieren.
##Bewertung
1 Punkt für die Erfüllung des Pflichtteils
###Pflichtteil
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
- [X] CRUD für eine Frachtart
- [X] mindestens ein Test
###empfohlene Realisierungen als Vorbereitung auf den Beleg
werden überprüft (aber nicht bewertet), wenn hier in der vorgegebenen Reihenfolge als bearbeitet angegeben
- [ ] mindestens je ein Test für CRUD
- [ ] mindestens zwei Tests mit Mockito
- [ ] Einfügen der Frachtstücke vollständig implementiert und getestet (mindestens 8 Testfälle)
- [ ] Unterstützung von mindestens zwei Frachtarten
- [ ] vollständige GL
- [ ] keine Code-Duplikate
