package eventSystem.listener;

import eventSystem.infrastructure.CRUDEventListener;
import eventSystem.infrastructure.EntfernenEvent;
import verwaltung.Lager;

import java.util.EventObject;

public class EntfernenListener implements CRUDEventListener {
    private Lager cargoList;
    public EntfernenListener(Lager cargoList){
        this.cargoList = cargoList;
    }
    @Override
    public void onEvent(EventObject event) {
        this.cargoList.entfernen(((EntfernenEvent)event).getStorageLocation());

    }
}
