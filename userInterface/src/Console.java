import cargo.Hazard;
import cargos.dryBulkCargoImpl;
import verwaltung.Kunde;
import verwaltung.Lager;

import java.math.BigDecimal;
import java.util.Scanner;

public class Console {
    private Lager logic;
    public Console(Lager logic){
        this.logic = logic;
    }
    private String[] commandString;
    public void execute(){
        try(Scanner sc = new Scanner(System.in)){

            do{
                System.out.println("enter command:");
                Command c = new Command(sc.next());
                dryBulkCargoImpl cargo = new dryBulkCargoImpl("henry",new BigDecimal(1),89, new Hazard[]{Hazard.flammable});
                Scanner u = new Scanner(System.in);
                switch (c.operator){
                    case c:
                        einfuegenPrompt();
                        this.logic.einfuegen("Heinz");
                        break;
                    case d:
                        entfernenPrompt();

                        this.logic.entfernen(u.nextInt());
                        break;
                    case r:
                        this.logic.abrufen();
                        break;
                    case u:
                        inspectionPrompt();
                        this.logic.inspection(cargo);
                        break;
                    case p:
                        //persistenzmodus Implementierung
                        break;

                }

            }while(true);
        }

    }
    public static void einfuegenPrompt(){
        System.out.println("Bitte geben sie: Fracht-Typ Kunden-Name Wert " +
                "Gefahrenstoffe, einzelnes Komma für keine\n" +
                "fragile(true/false) pressurized(true/false)\n" +
                "grainSize\n" +
                "oder einen Namen um einen Kunden hinzuzufügen:");
    }
    public static void entfernenPrompt(){
        System.out.println("Bitte geben sie den Lagerplatz des zu entfernenden Frachtstücks an:");
    }
    public static void inspectionPrompt(){
        System.out.println("Bitte geben sie den Lagerplatz des Frachtstücks das sie inspizieren möchten an:");
    }
}
