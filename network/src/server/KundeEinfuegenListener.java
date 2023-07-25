package server;

import eventSystem.infrastructure.AbrufenEvent;
import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.KundeEinfuegenErgebnisEvent;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.KundeEinfuegenEvent;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class KundeEinfuegenListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private KundeEinfuegenErgebnisEvent kundeEinfuegenErgebnisEvent;

    private LagerFassade lagerFassade;
    public KundeEinfuegenListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public KundeEinfuegenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        boolean ergebnis = this.lagerFassade.getLager().einfuegen(((KundeEinfuegenEvent) event).getObject());
        kundeEinfuegenErgebnisEvent = new KundeEinfuegenErgebnisEvent(this,ergebnis);
        eventHandler.handleEvent(kundeEinfuegenErgebnisEvent);

    }
}

