package eventSystem.listener;

import eventSystem.infrastructure.CRUDEventListener;
import verwaltung.Lager;

import java.util.EventObject;

public class AbrufenListener implements CRUDEventListener {
    private Lager cargoList;
    public AbrufenListener(Lager cargoList){
        this.cargoList = cargoList;
    }
    @Override
    public void onEvent(EventObject event) {
        this.cargoList.abrufen();


    }
}
