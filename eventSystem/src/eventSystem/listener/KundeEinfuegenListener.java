package eventSystem.listener;

import eventSystem.infrastructure.CRUDEventListener;
import eventSystem.infrastructure.KundeEinfuegenEvent;
import eventSystem.infrastructure.StorableCargoEinfuegenEvent;
import verwaltung.Lager;

import java.util.EventObject;

public class KundeEinfuegenListener implements CRUDEventListener {
    private Lager lager;
    public KundeEinfuegenListener(Lager cargoList){
        this.lager = cargoList;
    }
    @Override
    public void onEvent(EventObject event) {
        this.lager.einfuegen(((KundeEinfuegenEvent) event).getObject());

    }
}

