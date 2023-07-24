package server;

import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.EntfernenEvent;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class EntfernenListener implements CRUDEventListener {
    private LagerFassade lagerFassade;
    public EntfernenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        this.lagerFassade.getLager().entfernen(((EntfernenEvent)event).getStorageLocation());

    }
}
