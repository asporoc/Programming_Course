package server;

import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.StorableCargoEinfuegenEvent;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class StorableCargoEinfuegenListener implements CRUDEventListener {
    private LagerFassade lagerFassade;
    public StorableCargoEinfuegenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        this.lagerFassade.getLager().einfuegen(((StorableCargoEinfuegenEvent) event).getObject());

    }
}
