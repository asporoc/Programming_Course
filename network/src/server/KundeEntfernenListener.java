package server;

import eventSystem.infrastructure.KundeEntfernenEvent;
import eventSystem.listener.CRUDEventListener;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class KundeEntfernenListener implements CRUDEventListener {
    private LagerFassade lagerFassade;
    public KundeEntfernenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;

    }

    @Override
    public void onEvent(EventObject event) {
        lagerFassade.getLager().entfernen(((KundeEntfernenEvent)event).getName());

    }
}
