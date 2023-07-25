package server;

import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.CargoEntfernenEvent;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class CargoEntfernenListener implements CRUDEventListener {
    private LagerFassade lagerFassade;
    public CargoEntfernenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        this.lagerFassade.getLager().entfernen(((CargoEntfernenEvent)event).getStorageLocation());

    }
}
