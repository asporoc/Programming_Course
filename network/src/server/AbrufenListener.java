package server;

import eventSystem.listener.CRUDEventListener;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class AbrufenListener implements CRUDEventListener {
    private LagerFassade lagerFassade;
    public AbrufenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        this.lagerFassade.getLager().getCargoList();
    }
}
