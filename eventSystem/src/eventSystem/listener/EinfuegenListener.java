package eventSystem.listener;

import eventSystem.infrastructure.CRUDEventListener;
import eventSystem.infrastructure.EinfuegenEvent;
import verwaltung.Lager;

import java.util.EventObject;

public class EinfuegenListener implements CRUDEventListener {
    private Lager cargoList;
    public EinfuegenListener(Lager cargoList){
        this.cargoList = cargoList;
    }
    @Override
    public void onEvent(EventObject event) {
        this.cargoList.einfuegen(((EinfuegenEvent) event).getEinfuegenString());

    }
}
