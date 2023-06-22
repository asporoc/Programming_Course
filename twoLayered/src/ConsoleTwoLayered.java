import JOS.JOSItemSerializationUtils;
import administration.Customer;
import cargo.Hazard;
import cargos.*;
import util.Command;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Scanner;

public class ConsoleTwoLayered {

    private Lager logic;
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
                switch (c.operator){
                    case c:
                        //einfuegen mittels Scanner möglich erfordert jedoch korrekt formulierte strings
                        //this.logic.einfuegen(c.commandoString);
                        //einfachheit halber vor konfiguriertes Frachtstück und Kunde
                        //this.logic.einfuegen("Heinz");
                        //this.logic.einfuegen("DryBulkCargo Heinz 12,flammable,explosive true false 54");
                        parseCargo(c.commandoString);
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
                        //JOS.JOSItemSerializationUtils.serialize("test.ser",logic);
                        logic = JOSItemSerializationUtils.deserialize("test.ser");
                        break;
                    case Error:
                        throw notImplementedException;
                }
            }while(true);
        }

    }
    public void parseCargo(String einfuegenString) throws Exception {

        String[] text = einfuegenString.split(" ");

            EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
            String value = null;

            if (text[2].contains(",")) {
                String[] hazardText = text[2].split(",");
                value = hazardText[0];
                for (int i = 0; i < hazardText.length; i++) {
                    switch (hazardText[i]) {
                        case "flammable":
                            hazards.add(Hazard.flammable);
                            break;
                        case "toxic":
                            hazards.add(Hazard.toxic);
                            break;
                        case "radioactive":
                            hazards.add(Hazard.radioactive);
                            break;
                        case "explosive":
                            hazards.add(Hazard.explosive);
                            break;
                        default:
                    }
                }
            }else{
                value = text[2];
            }
            switch(text[0]){
                case "DryBulkCargo":
                    logic.einfuegen(new DryBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value),hazards,Integer.parseInt(text[text.length-1])));
                    break;
                case "DryBulkAndUnitisedCargo":
                    logic.einfuegen(new DryBulkAndUnitisedCargoImpl(new Kunde(text[1]),new BigDecimal(value),hazards,Integer.parseInt(text[text.length-1]),Boolean.parseBoolean(text[text.length-3])));
                    break;
                case "LiquidAndDryBulkCargo":
                    logic.einfuegen(new LiquidAndDryBulkCargoImpl(new Kunde(text[1]),new BigDecimal(value),hazards,Boolean.parseBoolean(text[text.length-2]),Integer.parseInt(text[text.length-1])));
                    break;
                case "LiquidBulkCargo":
                    logic.einfuegen(new LiquidBulkCargoImpl(new Kunde(text[1]),new BigDecimal(value),hazards,Boolean.parseBoolean(text[text.length-2])));
                    break;
                case "UnitisedCargo":
                    logic.einfuegen(new UnitisedCargoImpl(new Kunde(text[1]),new BigDecimal(value),hazards,Boolean.parseBoolean(text[text.length-3])));
                default:
                    System.out.println("Einfuegen fehlerhaft bitter versuchen sie es erneut.");
                    execute();
            }
    }
    public Customer parseKunde(String einfuegenString){
        return new Kunde(einfuegenString);
    }
}
