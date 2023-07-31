package viewControl;

import cargo.Hazard;
import cargos.*;
import eventSystem.infrastructure.*;
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

    public void execute() {
        boolean[] mode = new boolean[5];
        try (Scanner sc = new Scanner(System.in)) {
            do{
                System.out.println("Bitte geben sie einen Befehl gemäß folgendem Muster ein:" +
                        "\n :c Um in den Kunden/Frachtstueck Einfuegemodus zu kommen."+
                        "\n :r Um in den Abrufmodus zu kommen."+
                        "\n :d Um in den Entfernenmodus zu kommen." +
                        "\n :u Um in den Inspektionsmodus zu kommen."+
                        "\n :p Um ein Lager zu Speichern/Laden");


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
                                String[] inputOptions = newInput.split(" ");
                                if(null != this.abrufenEventHandler) {
                                    AbrufenEvent abrufenEvent;
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
                                try{
                                if(null != this.inspectionEventHandler) {
                                    InspectionEvent inspectionEvent = new InspectionEvent(this, Integer.parseInt(newInput));
                                    inspectionEventHandler.handleEvent(inspectionEvent);
                                    }
                                }catch (Exception e){

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
                        System.out.println("\n\n\n\nEingabe war fehlerhaft bitte versuchen sie es erneut");
                        execute();
                }
                }
            } while (true);
        }catch (Exception e){
            System.out.println("Something went wrong, the application has been restarted." +
                    "\n Please be careful to only enter full Commands for example:" +
                    "\n :c Heinz");
        }

    }
    public storableCargo parseCargo(String einfuegenString) {

        storableCargo cargo = null;
        String[] text = einfuegenString.split(" ");

        if (text.length < 3) {
            EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
            String value = text[2].replace(",", ".");

            if (text[3].equals(",")) {

            } else {
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
                    try {
                        cargo = new DryBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Integer.parseInt(text[text.length - 1]));
                        break;
                    } catch (Exception e) {
                        System.out.println("Eingabe nicht zulaessig");
                        return null;
                    }
                case "DryBulkAndUnitisedCargo":
                    try {
                        cargo = new DryBulkAndUnitisedCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Integer.parseInt(text[text.length - 1]), Boolean.parseBoolean(text[text.length - 2]));
                        break;
                    } catch (Exception e) {
                        System.out.println("Eingabe nicht zulaessig");
                        return null;
                    }
                case "LiquidAndDryBulkCargo":
                    try {
                        cargo = new LiquidAndDryBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]), Integer.parseInt(text[text.length - 1]));
                        break;
                    } catch (Exception e) {
                        System.out.println("Eingabe nicht zulaessig");
                        return null;
                    }
                case "LiquidBulkCargo":
                    try {
                        cargo = new LiquidBulkCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]));
                        break;
                    } catch (Exception e) {
                        System.out.println("Eingabe nicht zulaessig");
                        return null;
                    }
                case "UnitisedCargo":
                    try {
                        cargo = new UnitisedCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]));
                        break;
                    } catch (Exception e) {
                        System.out.println("Eingabe nicht zulaessig");
                        return null;
                    }
                case "LiquidBulkAndUnitisedCargo":
                    try {
                        cargo = new LiquidBulkAndUnitisedCargoImpl(new Kunde(text[1]), new BigDecimal(value), hazards, Boolean.parseBoolean(text[text.length - 2]), Boolean.parseBoolean(text[text.length - 1]));
                        break;
                    } catch (Exception e) {
                        System.out.println("Eingabe nicht zulaessig");
                        return null;
                    }
                default:
                    System.out.println("Einfuegen fehlerhaft bitter versuchen sie es erneut.");
                    return null;
            }
            return cargo;
        }
        return null;
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


