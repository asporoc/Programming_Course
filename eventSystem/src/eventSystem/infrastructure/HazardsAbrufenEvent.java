package eventSystem.infrastructure;

import cargo.Hazard;

import java.util.EnumSet;
import java.util.EventObject;

public class HazardsAbrufenEvent extends EventObject {
    final String option;
    final EnumSet<Hazard> hazards;

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

