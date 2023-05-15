import util.Command;
import verwaltung.Lager;

import java.util.Scanner;

public class ConsoleTwoLayered {
    final Lager logic;
    public ConsoleTwoLayered(Lager logic){
        this.logic = logic;
    }
    Exception notImplementedException = new Exception("Der eingegebene Befehl ist nicht implementiert");
    public void execute() throws Exception {
        try(Scanner sc = new Scanner(System.in)){

            do{
                System.out.println("Bitte geben sie einen Befehl gemäß folgendem Muster ein:" +
                        "\n :c Name des Kunden den sie hinzufügen wollen"+
                        "\n :c Um ein Frachtstück hinzuzufügen: Fracht-Typ Kunde Wert Gefahren-Stoffe, einzelnes Komma für keine fragile(true/false) pressurized(true/false) grain-Size" +
                        "\n :d Lagerort des Frachtstücks das sie entfernen möchten" +
                        "\n :u Lagerort des Frachtstücks das sie inspizieren wollen" +
                        "\n :r Gibt den derzeitigen Inhalt des gesamten Lagers wieder");
                Command c = new Command(sc.nextLine());
                //dryBulkCargoImpl cargo = new dryBulkCargoImpl("henry",new BigDecimal(1),89, new Hazard[]{Hazard.flammable});
                switch (c.operator){
                    case c:
                        //einfuegen mittels Scanner möglich erfordert jedoch korrekt formulierte strings
                        //this.logic.einfuegen(c.commandoString);
                        //einfachheit halber vor konfiguriertes Frachtstück und Kunde
                        this.logic.einfuegen("Heinz");
                        this.logic.einfuegen("DryBulkCargo Heinz 12,flammable,explosive true false 54");
                        break;
                    case d:
                        this.logic.entfernen(c.commandoInt);
                        break;
                    case r:
                        System.out.println("Die derzeit eingelagerten FrachtStücke sind: ");
                        System.out.println(this.logic.abrufen().toString());
                        break;
                    case u:
                        this.logic.inspection(c.commandoInt);
                        break;
                    case p:
                        //persistenzmodus Implementierung
                        break;
                    case Error:
                        throw notImplementedException;
                }
            }while(true);
        }
    }
}
