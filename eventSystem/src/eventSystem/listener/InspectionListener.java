package eventSystem.listener;

import eventSystem.infrastructure.CRUDEventListener;
import eventSystem.infrastructure.EntfernenEvent;
import verwaltung.Lager;

import java.util.EventObject;


public class InspectionListener implements CRUDEventListener {
    private Lager cargoList;
    public InspectionListener(Lager cargoList){
        this.cargoList = cargoList;
    }
    @Override
    public void onEvent(EventObject event) {
        this.cargoList.inspection(((EntfernenEvent)event).getStorageLocation());

    }
}
