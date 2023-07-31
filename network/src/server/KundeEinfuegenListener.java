package server;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.KundeEinfuegenErgebnisEvent;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.KundeEinfuegenEvent;
import logger.LogEnum;
import logger.LogUtil;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class KundeEinfuegenListener implements CRUDEventListener {
    private EventHandler eventHandler;

    private final LagerFassade lagerFassade;
    private LogUtil logUtil;
    public KundeEinfuegenListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public KundeEinfuegenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    public KundeEinfuegenListener(LagerFassade lagerFassade, LogUtil logUtil){
        this.lagerFassade = lagerFassade;
        this.logUtil=logUtil;
    }
    @Override
    public void onEvent(EventObject event) {

        if(logUtil!=null) {
            logUtil.logChange(LogEnum.KUNDE_EINFUEGEN);
        }
        boolean ergebnis = this.lagerFassade.getLager().einfuegen(((KundeEinfuegenEvent) event).getObject());
            if (ergebnis && logUtil !=null) {
                logUtil.logChange(LogEnum.KUNDE_EINGEFUEGT);
            }

        if(eventHandler!=null) {
            KundeEinfuegenErgebnisEvent kundeEinfuegenErgebnisEvent = new KundeEinfuegenErgebnisEvent(this, ergebnis);
            eventHandler.handleEvent(kundeEinfuegenErgebnisEvent);
        }
    }
}

