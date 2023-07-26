package server;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.InspectionErgebnisEvent;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.InspectionEvent;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.Date;
import java.util.EventObject;


public class InspectionListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private InspectionErgebnisEvent inspectionErgebnisEvent;
    private LagerFassade lagerFassade;
    public InspectionListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public InspectionListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    @Override
    public void onEvent(EventObject event) {
        Date date= this.lagerFassade.getLager().inspection(((InspectionEvent)event).getStorageLocation());
        if(eventHandler != null) {
            inspectionErgebnisEvent = new InspectionErgebnisEvent(this, date);
            eventHandler.handleEvent(inspectionErgebnisEvent);
        }
    }
}
