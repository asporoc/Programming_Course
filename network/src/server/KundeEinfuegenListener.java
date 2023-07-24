package server;

import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.KundeEinfuegenEvent;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class KundeEinfuegenListener implements CRUDEventListener {
    private LagerFassade lagerFassade;
    public KundeEinfuegenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        this.lagerFassade.getLager().einfuegen(((KundeEinfuegenEvent) event).getObject());

    }
}

