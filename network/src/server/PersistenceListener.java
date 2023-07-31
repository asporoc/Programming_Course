package server;

import JOS.JOSItemSerializationUtils;
import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.PersistenceErgebnisEvent;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.PersistenceEvent;

import logger.LogEnum;
import logger.LogUtil;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class PersistenceListener implements CRUDEventListener {
    private PersistenceErgebnisEvent persistenceErgebnisEvent;
    private EventHandler eventHandler;
    private LagerFassade lagerFassade;
    private LogUtil logUtil;
    public PersistenceListener(LagerFassade lagerFassade, EventHandler eventHandler) {
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public PersistenceListener(LagerFassade lagerFassade) {
        this.lagerFassade = lagerFassade;
    }
    public PersistenceListener(LagerFassade lagerFassade,LogUtil logUtil) {
        this.lagerFassade = lagerFassade;
        this.logUtil = logUtil;
    }

    @Override
    public void onEvent(EventObject event) {
        String josString = ((PersistenceEvent)event).getJos();
        if(josString.equals("saveJOS")){
            if(logUtil != null) {
                logUtil.logChange(LogEnum.PERSISTENCE_SPEICHERN);
            }
            JOSItemSerializationUtils.serialize("test.ser", lagerFassade.getLager());
            if(logUtil != null) {
                logUtil.logChange(LogEnum.PERSISTENCE_GESPEICHERT);
            }
            if(eventHandler != null) {
                persistenceErgebnisEvent = new PersistenceErgebnisEvent(this);
                eventHandler.handleEvent(persistenceErgebnisEvent);
            }

        }
        else if(josString.equals("loadJOS")){
            if(logUtil != null) {
                logUtil.logChange(LogEnum.PERSISTENCE_LADEN);
            }
            lagerFassade.setLager(JOSItemSerializationUtils.deserialize("test.ser"));
            if(logUtil != null) {
                logUtil.logChange(LogEnum.PERSISTENCE_GELADEN);
            }
            if(eventHandler != null){
                persistenceErgebnisEvent = new PersistenceErgebnisEvent(this);
                eventHandler.handleEvent(persistenceErgebnisEvent);
            }

        }

    }
}
