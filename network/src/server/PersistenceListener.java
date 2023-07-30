package server;

import JOS.JOSItemSerializationUtils;
import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.PersistenceErgebnisEvent;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.PersistenceEvent;

import verwaltung.LagerFassade;

import java.util.EventObject;

public class PersistenceListener implements CRUDEventListener {
    private PersistenceErgebnisEvent persistenceErgebnisEvent;
    private EventHandler eventHandler;
    private LagerFassade lagerFassade;
    public PersistenceListener(LagerFassade lagerFassade, EventHandler eventHandler) {
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public PersistenceListener(LagerFassade lagerFassade) {
        this.lagerFassade = lagerFassade;
    }

    @Override
    public void onEvent(EventObject event) {
        String josString = ((PersistenceEvent)event).getJos();
        if(josString.equals("saveJOS")){
            JOSItemSerializationUtils.serialize("test.ser", lagerFassade.getLager());
            if(eventHandler != null) {
                persistenceErgebnisEvent = new PersistenceErgebnisEvent(this);
                eventHandler.handleEvent(persistenceErgebnisEvent);
            }

        }
        else if(josString.equals("loadJOS")){
            lagerFassade.setLager(JOSItemSerializationUtils.deserialize("test.ser"));
            if(eventHandler != null){
                persistenceErgebnisEvent = new PersistenceErgebnisEvent(this);
                eventHandler.handleEvent(persistenceErgebnisEvent);
            }

        }

    }
}
