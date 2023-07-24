package server;

import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.InspectionEvent;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;


public class InspectionListener implements CRUDEventListener {
    private LagerFassade lagerFassade;
    public InspectionListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        this.lagerFassade.getLager().inspection(((InspectionEvent)event).getStorageLocation());

    }
}
