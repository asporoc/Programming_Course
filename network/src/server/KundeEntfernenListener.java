package server;

import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.KundeEntfernenErgebnisEvent;
import eventSystem.infrastructure.KundeEntfernenEvent;
import eventSystem.listener.CRUDEventListener;
import logger.LogEnum;
import logger.LogUtil;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class KundeEntfernenListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private final LagerFassade lagerFassade;
    private LogUtil logUtil;

    public KundeEntfernenListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;

    }
    public KundeEntfernenListener(LagerFassade lagerFassade) {
        this.lagerFassade = lagerFassade;
    }
    public KundeEntfernenListener(LagerFassade lagerFassade,LogUtil logUtil) {
        this.lagerFassade = lagerFassade;
        this.logUtil = logUtil;
    }

    @Override
    public void onEvent(EventObject event) {
        if(logUtil != null) {
            logUtil.logChange(LogEnum.KUNDE_ENTFERNEN);
        }
        boolean ergebnis = lagerFassade.getLager().entfernen(((KundeEntfernenEvent)event).getName());
        if(ergebnis && logUtil != null){
            logUtil.logChange(LogEnum.KUNDE_ENTFERNT);
        }
        if(eventHandler != null){
            KundeEntfernenErgebnisEvent kundeEntfernenErgebnisEvent = new KundeEntfernenErgebnisEvent(this, ergebnis);
            eventHandler.handleEvent(kundeEntfernenErgebnisEvent);

        }

    }
}
