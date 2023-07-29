package server;

import cargos.*;
import eventSystem.infrastructure.*;
import eventSystem.listener.CRUDEventListener;
import verwaltung.Lager;
import verwaltung.LagerFassade;

import java.util.ArrayList;
import java.util.EventObject;

public class AbrufenListener implements CRUDEventListener {
    private CustomerAbrufenEvent customerAbrufenEvent;
    private HazardsAbrufenEvent hazardsAbrufenEvent;
    private CargosAbrufenEvent cargosAbrufenEvent;
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
            if (((AbrufenEvent) event).getType().equals("customers")) {
                customerAbrufenEvent = new CustomerAbrufenEvent(this,lagerFassade);
                eventHandler.handleEvent(customerAbrufenEvent);
            }else if(((AbrufenEvent) event).getType().equals("hazards")){
                hazardsAbrufenEvent = new HazardsAbrufenEvent(this, lagerFassade,((AbrufenEvent)event).getOption());
                eventHandler.handleEvent(hazardsAbrufenEvent);
            }else if(((AbrufenEvent)event).getType().equals("cargos")){
                ArrayList<storableCargo> cargos = new ArrayList<>();
                Class<? extends storableCargo> targetClass = null;
                switch (((AbrufenEvent)event).getOption()){
                    case"DryBulkCargo":
                        targetClass = DryBulkCargoImpl.class;
                        break;
                    case"DryBulkAndUnitisedCargo":
                        targetClass = DryBulkAndUnitisedCargoImpl.class;
                        break;
                    case"LiquidAndDryBulkCargo":
                        targetClass = LiquidAndDryBulkCargoImpl.class;
                        break;
                    case"LiquidBulkAndUnitisedCargo":
                        targetClass = LiquidBulkAndUnitisedCargoImpl.class;
                        break;
                    case"LiquidBulkCargo":
                        targetClass = LiquidBulkCargoImpl.class;
                        break;
                    case"UnitisedCargo":
                        targetClass = UnitisedCargoImpl.class;
                        break;
                    default:
                        break;
                }
                for(int i = 0; i<lagerFassade.getLager().getCargoList().size();i++){
                    if(targetClass.isInstance(lagerFassade.getLager().getCargoList().get(i))){
                        cargos.add(lagerFassade.getLager().getCargoList().get(i));
                    }
                }
                cargosAbrufenEvent = new CargosAbrufenEvent(this,cargos);
                eventHandler.handleEvent(cargosAbrufenEvent);


            }

        }
    }
}
