package server;

import administration.Customer;
import cargo.Hazard;
import cargos.*;
import eventSystem.infrastructure.*;
import eventSystem.listener.CRUDEventListener;
import logger.LogEnum;
import logger.LogUtil;
import verwaltung.Lager;
import verwaltung.LagerFassade;
import viewControl.ConsoleEventSystem;



import java.util.ArrayList;
import java.util.EnumSet;
import java.util.EventObject;
import java.util.List;

public class AbrufenListener implements CRUDEventListener {
    private ConsoleEventSystem CES;
    private final LagerFassade lagerFassade;
    private EventHandler eventHandler;
    private LogUtil logUtil;
    


    public AbrufenListener(LagerFassade lagerFassade, EventHandler eventHandler) {
        this.lagerFassade = lagerFassade;
        this.eventHandler = eventHandler;
    }

    public AbrufenListener(LagerFassade lagerFassade, ConsoleEventSystem CES) {
        this.lagerFassade = lagerFassade;
        this.CES = CES;

    }
    public AbrufenListener(LagerFassade lagerFassade, ConsoleEventSystem CES,LogUtil logUtil) {
        this.lagerFassade = lagerFassade;
        this.CES = CES;
        this.logUtil = logUtil;

    }

    @Override
    public void onEvent(EventObject event) {
        if (((AbrufenEvent) event).getType().equals("customers")) {
            List<Customer> customerList = lagerFassade.getLager().getCustomerList();
            Lager lager = lagerFassade.getLager();
            int z;
            String[] cargoCustomer = new String[customerList.size()];
            for (int y = 0; y < customerList.size(); y++) {
                int i = 0;
                for (z = 0; z < lager.getCargoList().size(); z++) {
                    if (lager.getCargoList().get(y).getOwner().getName().equals(customerList.get(y).getName())) {
                        i++;
                    }
                }
                cargoCustomer[y] = ("Kunde: " + customerList.get(y).getName() + " hat " + i + " Frachtsuecke eingelagert.");
            }
            if (eventHandler != null) {
                CustomerAbrufenEvent customerAbrufenEvent = new CustomerAbrufenEvent(this, cargoCustomer);
                eventHandler.handleEvent(customerAbrufenEvent);
            } else if (CES != null) {
                if(logUtil!=null) {
                    logUtil.logChange(LogEnum.CUSTOMER_ABRUFEN);
                }
                for (String string : cargoCustomer) {
                    CES.customerAbrufen(string);
                }
            }

        } else if (((AbrufenEvent) event).getType().equals("hazards")) {
            EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
            Lager lager = lagerFassade.getLager();
            for(int i = 0; i <lager.getCargoList().size();i++){
                if(lager.getCargoList().get(i).getHazards().contains(Hazard.explosive)){
                    hazards.add(Hazard.explosive);
                }
                if(lager.getCargoList().get(i).getHazards().contains(Hazard.flammable)){
                    hazards.add(Hazard.flammable);
                }
                if(lager.getCargoList().get(i).getHazards().contains(Hazard.radioactive)){
                    hazards.add(Hazard.radioactive);
                }
                if(lager.getCargoList().get(i).getHazards().contains(Hazard.toxic)){
                    hazards.add(Hazard.toxic);
                }

            }
            if (eventHandler != null) {
                HazardsAbrufenEvent hazardsAbrufenEvent = new HazardsAbrufenEvent(this, hazards, ((AbrufenEvent) event).getOption());
                eventHandler.handleEvent(hazardsAbrufenEvent);
            } else if (CES != null) {
                if(logUtil != null) {
                    logUtil.logChange(LogEnum.HAZARDS_ABRUFEN);
                }
                CES.hazardsAbrufen(hazards,((AbrufenEvent)event).getOption());
            }
        } else if (((AbrufenEvent) event).getType().equals("cargos")) {
            ArrayList<storableCargo> cargos = new ArrayList<>();
            Class<? extends storableCargo> targetClass = null;
            switch (((AbrufenEvent) event).getOption()) {
                case "DryBulkCargo":
                    targetClass = DryBulkCargoImpl.class;

                    break;
                case "DryBulkAndUnitisedCargo":
                    targetClass = DryBulkAndUnitisedCargoImpl.class;
                    break;
                case "LiquidAndDryBulkCargo":
                    targetClass = LiquidAndDryBulkCargoImpl.class;
                    break;
                case "LiquidBulkAndUnitisedCargo":
                    targetClass = LiquidBulkAndUnitisedCargoImpl.class;
                    break;
                case "LiquidBulkCargo":
                    targetClass = LiquidBulkCargoImpl.class;
                    break;
                case "UnitisedCargo":
                    targetClass = UnitisedCargoImpl.class;
                    break;
                default:
                    break;
            }
            for (int i = 0; i < lagerFassade.getLager().getCargoList().size(); i++) {
                if (targetClass.isInstance(lagerFassade.getLager().getCargoList().get(i))) {
                    cargos.add(lagerFassade.getLager().getCargoList().get(i));
                }
            }
            if (eventHandler != null) {
                CargosAbrufenEvent cargosAbrufenEvent = new CargosAbrufenEvent(this, cargos);
                eventHandler.handleEvent(cargosAbrufenEvent);
            } else if (CES != null) {
                if(logUtil != null) {
                    logUtil.logChange(LogEnum.CARGO_ABRUFEN);
                }
                CES.cargosAbrufen(cargos);

            }else{

            }
        }
    }
}

