package server;

import eventSystem.infrastructure.AbrufenEvent;
import eventSystem.infrastructure.CustomerAbrufenEvent;
import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.HazardsAbrufenEvent;
import eventSystem.listener.CRUDEventListener;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.EventObject;

public class AbrufenListener implements CRUDEventListener {
    private CustomerAbrufenEvent customerAbrufenEvent;
    private HazardsAbrufenEvent hazardsAbrufenEvent;
    private LagerFassade lagerFassade;
    private EventHandler eventHandler;
    public AbrufenListener(LagerFassade lagerFassade, EventHandler eventHandler){
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }
    public AbrufenListener(LagerFassade lagerFassade){
        this.lagerFassade = lagerFassade;

    }
    @Override
    public void onEvent(EventObject event) {
        if(eventHandler != null) {
            if (((AbrufenEvent) event).getType().equals("Customer")) {
                customerAbrufenEvent = new CustomerAbrufenEvent(this,lagerFassade);
                eventHandler.handleEvent(customerAbrufenEvent);
            }else if(((AbrufenEvent) event).getType().equals("Hazards")){
                hazardsAbrufenEvent = new HazardsAbrufenEvent(this, lagerFassade);
                eventHandler.handleEvent(hazardsAbrufenEvent);
            }
        }
    }
}
