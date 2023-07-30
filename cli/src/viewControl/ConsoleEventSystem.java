package viewControl;

import administration.Customer;
import cargo.Hazard;
import cargos.*;
import eventSystem.infrastructure.*;
import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.StorableCargoEinfuegenEvent;
import eventSystem.listener.CRUDEventListener;
import verwaltung.Kunde;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.Scanner;


public class ConsoleEventSystem {
    Exception notImplementedException = new Exception("Der eingegebene Befehl ist nicht implementiert");
    public ConsoleEventSystem(){
    }
    public EventHandler<CRUDEventListener<StorableCargoEinfuegenEvent>> storableCargoEinfuegenHandler;
    public EventHandler<CRUDEventListener<AbrufenEvent>> abrufenEventHandler;
    public EventHandler<CRUDEventListener<CargoEntfernenEvent>> entfernenEventHandler;
    public EventHandler<CRUDEventListener<KundeEinfuegenEvent>> kundeEinfuegenHandler;
    public EventHandler<CRUDEventListener<InspectionEvent>> inspectionEventHandler;
    public EventHandler<CRUDEventListener<PersistenceEvent>> persistenceEventHandler;
    public EventHandler<CRUDEventListener<KundeEntfernenEvent>> kundeEntfernenHandler;

    public void setStorableCargoEinfuegenHandler(EventHandler handler) {
        this.storableCargoEinfuegenHandler = handler;
    }
    public void setKundeEinfuegenHandler(EventHandler handler) {
        this.kundeEinfuegenHandler = handler;
    }

    public void setEntfernenEventHandler(EventHandler handler) {
        this.entfernenEventHandler = handler;
    }
    public void setInspectionEventHandler(EventHandler handler) {
        this.inspectionEventHandler = handler;
    }
    public void setAbrufenEventHandler(EventHandler handler) {
        this.abrufenEventHandler = handler;
    }
    public void setPersistenceEventHandler(EventHandler handler){this.persistenceEventHandler = handler;}
    public void setKundeEntfernenHandler(EventHandler handler){this.kundeEntfernenHandler = handler;}
    private AbrufenEvent abrufenEvent;

    public void execute() throws Exception {
        boolean[] mode = new boolean[5];
        try (Scanner sc = new Scanner(System.in)) {
            do{
                System.out.println("Bitte geben sie einen Befehl gemäß folgendem Muster ein:" +
                        "\n :c Name des Kunden den sie hinzufügen wollen"+
                        "\n :c Um ein Frachtstück hinzuzufügen: Fracht-Typ Kunde Wert Gefahren-Stoffe, einzelnes Komma für keine fragile(true/false) pressurized(true/false) grain-Size" +
                        "\n :r Um die Liste der gelagerten Frachstücke abzurufen"+
                        "\n :d Lagerort des Frachtstücks das sie entfernen möchten" +
                        "\n :u Lagerort des Frachtstücks das sie inspizieren wollen"+
                        "\n :p loadJOS/saveJOS um das Lager zu laden/speichern");


                Command c = new Command(sc.nextLine());

                Scanner u = new Scanner(System.in);
                while(true){
                switch (c.operator){
                    case c:
                        while (true) {
                            System.out.print("Enter a name or cargo: ");
                            String newInput = u.nextLine();

                            if (newInput.startsWith(":")) {
                                c = new Command(newInput);
                                break;
                            } else if (newInput.split(" ").length == 1) {
                                KundeEinfuegenEvent kundeEinfuegenEvent = new KundeEinfuegenEvent(this, parseKunde(newInput));
                                kundeEinfuegenHandler.handleEvent(kundeEinfuegenEvent);
                            } else {
                                StorableCargoEinfuegenEvent storableCargoEinfuegenEvent = new StorableCargoEinfuegenEvent(this, parseCargo(newInput));
                                storableCargoEinfuegenHandler.handleEvent(storableCargoEinfuegenEvent);
                            }
                        }
                        break;
                    case d:
                        while (true) {
                            System.out.print("Geben sie den Namen des Kunden oder den Lagerort des Frachtstücks das sie Löschen wollen ein: ");
                            String newInput = u.nextLine();

                            if (newInput.startsWith(":")) {
                                c = new Command(newInput);
                                break;
                            } else {
                                if(newInput.matches("\\d+")){
                                    if(null != this.entfernenEventHandler) {
                                        CargoEntfernenEvent cargoEntfernenEvent = new CargoEntfernenEvent(this,Integer.parseInt(newInput));
                                        entfernenEventHandler.handleEvent(cargoEntfernenEvent);
                                    }
                                }else{
                                    if(null != this.kundeEntfernenHandler){
                                        KundeEntfernenEvent kundeEntfernenEvent = new KundeEntfernenEvent(this,newInput);
                                        kundeEntfernenHandler.handleEvent(kundeEntfernenEvent);
                                    }
                                }


                            }
                        }
                        break;
                    case r:
                        while (true) {
                            System.out.print("Enter what you want to review: customers, hazards, cargos ");
                            String newInput = u.nextLine();

                            if (newInput.startsWith(":")) {
                                c = new Command(newInput);
                                break;
                            } else {
                                String inputOptions[] = newInput.split(" ");
                                if(null != this.abrufenEventHandler) {
                                    if(inputOptions.length==1){
                                        abrufenEvent = new AbrufenEvent(this,inputOptions[0]);
                                    }else {
                                        abrufenEvent = new AbrufenEvent(this, inputOptions[0], inputOptions[1]);
                                    }
                                    abrufenEventHandler.handleEvent(abrufenEvent);
                                }
                            }
                        }
                        break;
                    case u:
                        while (true) {
                            System.out.print("Lagerort des zu inspizierenden Frachtstücks eingeben:");
                            String newInput = u.nextLine();

                            if (newInput.startsWith(":")) {
                                c = new Command(newInput);
                                break;
                            } else {
                                if(null != this.inspectionEventHandler) {
                                    InspectionEvent inspectionEvent = new InspectionEvent(this,Integer.parseInt(newInput));
                                    inspectionEventHandler.handleEvent(inspectionEvent);
                                }
                            }
                        }
                        break;
                    case p:
                        while (true) {
                            System.out.print("Enter loadJOS or saveJOS: ");
                            String newInput = u.nextLine();

                            if (newInput.startsWith(":")) {
                                c = new Command(newInput);
                                break;
                            } else {
                                if(null != this.persistenceEventHandler) {
                                    PersistenceEvent persistenceEvent = new PersistenceEvent(this, newInput);
                                    persistenceEventHandler.handleEvent(persistenceEvent);
                                }
                            }
                        }
                        break;
                    default:
                        System.out.println("Please enter a valid option.");
                        break;
                }
                continue;
                }
            } while (true);
        }catch (Exception e){
            System.out.println("Something went wrong, the application has been restarted." +
                    "\n Please be careful to only enter full Commands for example:" +
                    "\n :c Heinz");
        }

    }
    public storableCargo parseCargo(String einfuegenString) throws Exception {

        storableCargo cargo = null;
        String[] text = einfuegenString.split(" ");


            EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
            String value = text[2];

            if (text[3].equals(",")) {

            }else {
                String[] hazardText = text[3].split(",");
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
            }
            switch (text[0]) {
                case "DryBulkCargo":
                    cargo = new DryBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Integer.parseInt(text[text.length - 1]));
                    break;
                case "DryBulkAndUnitisedCargo":
                    cargo = new DryBulkAndUnitisedCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Integer.parseInt(text[text.length - 1]), Boolean.parseBoolean(text[text.length - 2]));
                    break;
                case "LiquidAndDryBulkCargo":
                    cargo = new LiquidAndDryBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]), Integer.parseInt(text[text.length - 1]));
                    break;
                case "LiquidBulkCargo":
                    cargo = new LiquidBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]));
                    break;
                case "UnitisedCargo":
                    cargo = new UnitisedCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]));
                    break;
                case "LiquidBulkAndUnitisedCargo":
                    cargo = new LiquidBulkAndUnitisedCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]), Boolean.parseBoolean(text[text.length - 1]));
                    break;
                default:
                    System.out.println("Einfuegen fehlerhaft bitter versuchen sie es erneut.");
                    return null;
            }
            return cargo;
        }
    public Kunde parseKunde(String einfuegenString){
        return new Kunde(einfuegenString);
    }
    public void einfuegenKundeErfolgreich(){
        System.out.println("Das Einfuegen eines Kunden war Erfolgreich.");
    }
    public void einfuegenCargoErfolgreich(){
        System.out.println("Das Einfuegen eines Frachtstücks war Erfolgreich.");
    }
    public void einfuegenGescheitert(){
        System.out.println("Das Einfuegen hat nicht funktioniert.");
    }
    public void inspectionErfolgreich(Date date){
        System.out.println("Das Frachtstück wurde inspiziert, und das letzte Inspektionsdatum auf :"+ date+ " gesetzt.");
    }
    public void inspectiongGescheitert(){
        System.out.println("Die Inspektion ist gescheitert.");
    }
    public void customerAbrufen(String cargoCustomer){
        System.out.println(cargoCustomer);}
    public void hazardsAbrufen(EnumSet<Hazard> hazards, String option){

            if(option.equals("i")){
                for(Hazard hazard: hazards) {
                    System.out.println("Das Lager enthält mindestens ein Frachtstück das " + hazard.name() + " ist");
                }
            }else if(option.equals("e")){
                EnumSet<Hazard> allHazards = EnumSet.allOf(Hazard.class);
                allHazards.removeAll(hazards);

                for (Hazard notHazard : allHazards) {
                    System.out.println("Das Lager enthält keine Frachtstücke die " + notHazard.name() + " sind");
                }
            }
    }
    public void kundeEntfernt(boolean ergebnis){
        if(ergebnis){
            System.out.println("Kunde wurde erfolgreich entfernt.");
        }else{
            System.out.println("Kunde konnte nicht entfernt werden.");
        }
    }
    public void cargoEntfernt(boolean ergebnis){
        if(ergebnis){
            System.out.println("Frachtstück wurde erfolgreich entfernt.");
        }else{
            System.out.println("Frachtstück konnte nicht entfernt werden.");
        }
    }
    public void cargosAbrufen(ArrayList<storableCargo> cargos){
        for(storableCargo cargo:cargos){
            System.out.println("Lagerort: "+ cargo.getStorageLocation()+"| Letztes Inspectionsdatum: "+ cargo.getLastInspectionDate()+ "| Einlagerungsdauer: "+ cargo.getDurationOfStorage().getSeconds()+ " Sekunden.");
        }
    }
    public String[] serverStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zum starten bitte Kapazität und Protokol[TCP/UDP] angeben (UDP nicht implementiert)");
        String arguments = scanner.nextLine();
        return arguments.split(" ");
    }
    public String[] startClient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zum starten entweder Net Protocol oder Kapazität und Log Sprachcode (z.B. DE,ENG) angeben");
        String arguments = scanner.nextLine();
        return arguments.split(" ");
    }


}


