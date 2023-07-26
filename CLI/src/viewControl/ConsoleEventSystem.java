package viewControl;

import cargo.Hazard;
import eventSystem.listener.CRUDEventListener;
import verwaltung.Kunde;

import java.math.BigDecimal;
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
                            System.out.print("Enter the Storage Location of a Cargo to delete ");
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
                            System.out.print("Enter what you want to review: Customer, Hazards ");
                            String newInput = u.nextLine();

                            if (newInput.startsWith(":")) {
                                c = new Command(newInput);
                                break;
                            } else {
                                if(null != this.abrufenEventHandler) {
                                    AbrufenEvent abrufenEvent = new AbrufenEvent(this);
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
                            System.out.print("Enter what you want to review: Customer, Hazards ");
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
            String value = null;

            if (text[2].contains(",")) {
                String[] hazardText = text[2].split(",");
                value = hazardText[0];
                for (int i = 1; i < hazardText.length; i++) {
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
            } else {
                value = text[2];
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


}


