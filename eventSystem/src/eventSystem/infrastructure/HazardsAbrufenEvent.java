package eventSystem.infrastructure;

import cargo.Hazard;
import verwaltung.LagerFassade;

import java.util.EnumSet;
import java.util.EventObject;

public class HazardsAbrufenEvent extends EventObject {
    String option;
    EnumSet<Hazard> hazards;

    public String getOption() {
        return option;
    }

    public HazardsAbrufenEvent(Object source, EnumSet<Hazard> hazards, String option) {
        super(source);
        this.hazards = hazards;
        this.option=option;
    }

    public EnumSet<Hazard> getHazards() {
        return hazards;
    }


}

