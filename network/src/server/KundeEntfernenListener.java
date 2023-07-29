package server;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.KundeEntfernenErgebnisEvent;
import eventSystem.infrastructure.KundeEntfernenEvent;
import eventSystem.listener.CRUDEventListener;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class KundeEntfernenListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private LagerFassade lagerFassade;
    private KundeEntfernenErgebnisEvent kundeEntfernenErgebnisEvent;
    public KundeEntfernenListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;

    }
    public KundeEntfernenListener(LagerFassade lagerFassade) {
        this.lagerFassade = lagerFassade;
    }

    @Override
    public void onEvent(EventObject event) {
        Boolean ergebnis = lagerFassade.getLager().entfernen(((KundeEntfernenEvent)event).getName());
        if(eventHandler != null){
            kundeEntfernenErgebnisEvent = new KundeEntfernenErgebnisEvent(this,ergebnis);
            eventHandler.handleEvent(kundeEntfernenErgebnisEvent);

        }

    }
}
