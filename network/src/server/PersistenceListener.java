package server;

import JOS.JOSItemSerializationUtils;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.PersistenceEvent;

import verwaltung.LagerFassade;

import java.util.EventObject;

public class PersistenceListener implements CRUDEventListener {
    LagerFassade lagerFassade;
    public PersistenceListener(LagerFassade lagerFassade) {
        this.lagerFassade = lagerFassade;
    }

    @Override
    public void onEvent(EventObject event) {
        String josString = ((PersistenceEvent)event).getJos();
        if(josString.equals("saveJOS")){
            JOSItemSerializationUtils.serialize("test.ser", lagerFassade.getLager());
        }
        else if(josString.equals("loadJOS")){
            lagerFassade.setLager(JOSItemSerializationUtils.deserialize("test.ser"));

        }

    }
}
