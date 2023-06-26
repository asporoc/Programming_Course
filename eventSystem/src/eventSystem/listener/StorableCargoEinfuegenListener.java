package eventSystem.listener;

import eventSystem.infrastructure.CRUDEventListener;
import eventSystem.infrastructure.StorableCargoEinfuegenEvent;
import verwaltung.Lager;

import java.util.EventObject;

public class StorableCargoEinfuegenListener implements CRUDEventListener {
    private Lager cargoList;
    public StorableCargoEinfuegenListener(Lager cargoList){
        this.cargoList = cargoList;
    }
    @Override
    public void onEvent(EventObject event) {
        this.cargoList.einfuegen(((StorableCargoEinfuegenEvent) event).getObject());

    }
}
