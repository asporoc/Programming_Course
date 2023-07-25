package server;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.StorableCargoEinfuegenErgebnisEvent;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.StorableCargoEinfuegenEvent;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class StorableCargoEinfuegenListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private StorableCargoEinfuegenErgebnisEvent storableCargoEinfuegenErgebnisEvent;
    private LagerFassade lagerFassade;
    public StorableCargoEinfuegenListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    @Override
    public void onEvent(EventObject event) {
        boolean ergebnis = this.lagerFassade.getLager().einfuegen(((StorableCargoEinfuegenEvent) event).getObject());
        storableCargoEinfuegenErgebnisEvent = new StorableCargoEinfuegenErgebnisEvent(this,ergebnis);
        eventHandler.handleEvent(storableCargoEinfuegenErgebnisEvent);

    }
}
