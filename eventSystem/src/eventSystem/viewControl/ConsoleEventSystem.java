package eventSystem.viewControl;

import cargo.Hazard;
import cargos.*;
import eventSystem.infrastructure.*;
import verwaltung.Kunde;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Scanner;

public class ConsoleEventSystem {
    Exception notImplementedException = new Exception("Der eingegebene Befehl ist nicht implementiert");
    public ConsoleEventSystem(){
    }
    public EventHandler<CRUDEventListener<StorableCargoEinfuegenEvent>> storableCargoEinfuegenHandler;
    public EventHandler<CRUDEventListener<AbrufenEvent>> abrufenEventHandler;
    public EventHandler<CRUDEventListener<EntfernenEvent>> entfernenEventHandler;
    public EventHandler<CRUDEventListener<KundeEinfuegenEvent>> kundeEinfuegenHandler;
    public EventHandler<CRUDEventListener<InspectionEvent>> inspectionEventHandler;

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

    public void execute() throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            do{
                System.out.println("Bitte geben sie einen Befehl gemäß folgendem Muster ein:" +
                        "\n :c Name des Kunden den sie hinzufügen wollen"+
                        "\n :c Um ein Frachtstück hinzuzufügen: Fracht-Typ Kunde Wert Gefahren-Stoffe, einzelnes Komma für keine fragile(true/false) pressurized(true/false) grain-Size" +
                        "\n :d Lagerort des Frachtstücks das sie entfernen möchten" +
                        "\n :u Lagerort des Frachtstücks das sie inspizieren wollen");


                Command c = new Command(sc.nextLine());



                AbrufenEvent abrufenEvent = new AbrufenEvent(this);

                Scanner u = new Scanner(System.in);
                switch (c.operator){
                    case c:
                        if(null != this) {
                            if(c.commandoString.substring(3).split(" ").length==1){
                                KundeEinfuegenEvent kundeEinfuegenEvent = new KundeEinfuegenEvent(this,parseKunde(c.commandoString));
                                kundeEinfuegenHandler.handleEvent(kundeEinfuegenEvent);
                            }else{
                                StorableCargoEinfuegenEvent storableCargoEinfuegenEvent = new StorableCargoEinfuegenEvent(this,parseCargo(c.commandoString));
                                storableCargoEinfuegenHandler.handleEvent(storableCargoEinfuegenEvent);
                            }
                        }
                        break;
                    case d:
                        if(null != this.entfernenEventHandler) {
                            EntfernenEvent entfernenEvent = new EntfernenEvent(this,c.commandoInt);
                            entfernenEventHandler.handleEvent(entfernenEvent);
                        }
                        break;
                    case r:
                        if(null != this.abrufenEventHandler) {
                            abrufenEventHandler.handleEvent(abrufenEvent);
                        }
                        break;
                    case u:
                        if(null != this.inspectionEventHandler) {
                            InspectionEvent inspectionEvent = new InspectionEvent(this,c.commandoInt);
                            inspectionEventHandler.handleEvent(inspectionEvent);
                        }
                        break;
                    case p:
                        //persistenzmodus Implementierung
                        break;
                    case Error:
                        throw notImplementedException;
                }
                continue;
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
                default:
                    System.out.println("Einfuegen fehlerhaft bitter versuchen sie es erneut.");
                    return null;
            }
            return cargo;
        }
    public Kunde parseKunde(String einfuegenString){
        return new Kunde(einfuegenString);
    }
}


