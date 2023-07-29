package eventSystem.infrastructure;

import verwaltung.LagerFassade;

import java.util.EventObject;

public class HazardsAbrufenEvent extends EventObject {
    String option;
    LagerFassade lagerFassade;

    public String getOption() {
        return option;
    }

    public HazardsAbrufenEvent(Object source, LagerFassade lagerFassade, String option) {
        super(source);
        this.lagerFassade = lagerFassade;
        this.option=option;
    }

    public LagerFassade getLagerFassade() {
        return lagerFassade;
    }


}

