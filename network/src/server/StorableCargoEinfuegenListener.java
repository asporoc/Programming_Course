package server;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.StorableCargoEinfuegenErgebnisEvent;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.StorableCargoEinfuegenEvent;
import logger.LogEnum;
import logger.LogUtil;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class StorableCargoEinfuegenListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private StorableCargoEinfuegenErgebnisEvent storableCargoEinfuegenErgebnisEvent;
    private LagerFassade lagerFassade;
    private LogUtil logUtil;
    public StorableCargoEinfuegenListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public StorableCargoEinfuegenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    public StorableCargoEinfuegenListener(LagerFassade lagerFassade, LogUtil logUtil){
        this.lagerFassade = lagerFassade;
        this.logUtil=logUtil;
    }
    @Override
    public void onEvent(EventObject event) {
        if(logUtil != null) {
            logUtil.logChange(LogEnum.CARGO_EINFUEGEN);
        }
        boolean ergebnis = this.lagerFassade.getLager().einfuegen(((StorableCargoEinfuegenEvent) event).getObject());
        if(ergebnis && logUtil != null){
            logUtil.logChange(LogEnum.CARGO_EINGEFUEGT);
        }
        if(eventHandler != null) {
            storableCargoEinfuegenErgebnisEvent = new StorableCargoEinfuegenErgebnisEvent(this, ergebnis);
            eventHandler.handleEvent(storableCargoEinfuegenErgebnisEvent);
        }
    }
}
