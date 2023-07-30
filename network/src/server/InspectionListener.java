package server;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.InspectionErgebnisEvent;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.InspectionEvent;
import logger.LogEnum;
import logger.LogUtil;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.Date;
import java.util.EventObject;


public class InspectionListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private InspectionErgebnisEvent inspectionErgebnisEvent;
    private LagerFassade lagerFassade;
    private LogUtil logUtil;
    public InspectionListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public InspectionListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    public InspectionListener(LagerFassade lagerFassade,LogUtil logUtil){
        this.lagerFassade = lagerFassade;
        this.logUtil=logUtil;
    }
    @Override
    public void onEvent(EventObject event) {
        if(logUtil != null) {
            logUtil.logChange(LogEnum.INSPECTION);
        }
        Date date= this.lagerFassade.getLager().inspection(((InspectionEvent)event).getStorageLocation());
        if(logUtil != null) {
            logUtil.logChange(LogEnum.INSPECTION_ERFOLGT);
        }
        if(eventHandler != null) {
            inspectionErgebnisEvent = new InspectionErgebnisEvent(this, date);
            eventHandler.handleEvent(inspectionErgebnisEvent);
        }
    }
}
