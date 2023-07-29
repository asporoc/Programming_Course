package server;

import eventSystem.infrastructure.CargoEntfernenErgebnisEvent;
import eventSystem.infrastructure.EventHandler;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.CargoEntfernenEvent;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class CargoEntfernenListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private LagerFassade lagerFassade;
    private CargoEntfernenErgebnisEvent cargoEntfernenErgebnisEvent;
    public CargoEntfernenListener(LagerFassade lagerFassade,EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public CargoEntfernenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        Boolean ergebnis = this.lagerFassade.getLager().entfernen(((CargoEntfernenEvent)event).getStorageLocation());
        if(eventHandler != null) {
            cargoEntfernenErgebnisEvent = new CargoEntfernenErgebnisEvent(this, ergebnis);
            eventHandler.handleEvent(cargoEntfernenErgebnisEvent);
        }
    }
}
