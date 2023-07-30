package server;

import eventSystem.infrastructure.CargoEntfernenErgebnisEvent;
import eventSystem.infrastructure.EventHandler;
import eventSystem.listener.CRUDEventListener;
import eventSystem.infrastructure.CargoEntfernenEvent;
import logger.LogEnum;
import logger.LogUtil;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class CargoEntfernenListener implements CRUDEventListener {
    private EventHandler eventHandler;
    private LagerFassade lagerFassade;
    private CargoEntfernenErgebnisEvent cargoEntfernenErgebnisEvent;
    private LogUtil logUtil;
    public CargoEntfernenListener(LagerFassade lagerFassade,EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public CargoEntfernenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;
    }
    public CargoEntfernenListener(LagerFassade lagerFassade,LogUtil logUtil){
        this.lagerFassade = lagerFassade;
        this.logUtil = logUtil;
    }
    @Override
    public void onEvent(EventObject event) {
        if(logUtil != null) {
            logUtil.logChange(LogEnum.CARGO_ENTFERNEN);
        }
        Boolean ergebnis = this.lagerFassade.getLager().entfernen(((CargoEntfernenEvent)event).getStorageLocation());
        if(ergebnis && logUtil != null){
            logUtil.logChange(LogEnum.CARGO_ENTFERNT);
        }
        if(eventHandler != null) {
            cargoEntfernenErgebnisEvent = new CargoEntfernenErgebnisEvent(this, ergebnis);
            eventHandler.handleEvent(cargoEntfernenErgebnisEvent);
        }
    }
}
