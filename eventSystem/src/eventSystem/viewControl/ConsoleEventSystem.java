package eventSystem.viewControl;

import eventSystem.infrastructure.*;

import util.Command;
import verwaltung.Lager;

import java.util.Scanner;

public class ConsoleEventSystem {
    final Lager logic;
    Exception notImplementedException = new Exception("Der eingegebene Befehl ist nicht implementiert");
    public ConsoleEventSystem(Lager logic){
        this.logic = logic;
    }
    public EventHandler<CRUDEventListener<EinfuegenEvent>> einfuegenHandler;
    public EventHandler<CRUDEventListener<AbrufenEvent>> abrufenEventHandler;
    public EventHandler<CRUDEventListener<StorageLocationEvent>> storageLocationHandler;

    public void setEinfuegenHandler(EventHandler handler) {
        this.einfuegenHandler = handler;
    }

    public void setStorageLocationHandler(EventHandler handler) {
        this.storageLocationHandler = handler;
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
                EinfuegenEvent einfuegenEvent = new EinfuegenEvent(this,c.commandoString);
                StorageLocationEvent entfernenEvent = new StorageLocationEvent(this,c.commandoInt);
                StorageLocationEvent inspectionEvent = new StorageLocationEvent(this,c.commandoInt);
                AbrufenEvent abrufenEvent = new AbrufenEvent(this);

                Scanner u = new Scanner(System.in);
                switch (c.operator){
                    case c:
                        if(null != this.einfuegenHandler) {
                            einfuegenHandler.handleEvent(einfuegenEvent);
                        }
                        break;
                    case d:
                        if(null != this.storageLocationHandler) {
                            storageLocationHandler.handleEvent(entfernenEvent);
                        }
                        break;
                    case r:
                        if(null != this.abrufenEventHandler) {
                            abrufenEventHandler.handleEvent(abrufenEvent);
                        }
                        break;
                    case u:
                        if(null != this.storageLocationHandler) {
                            storageLocationHandler.handleEvent(inspectionEvent);
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

}
