package eventSystem.infrastructure;

import verwaltung.LagerFassade;

import java.util.EventObject;

public class HazardsAbrufenEvent extends EventObject {
    LagerFassade lagerFassade;
    public HazardsAbrufenEvent(Object source,LagerFassade lagerFassade) {
        super(source);
        this.lagerFassade = lagerFassade;
    }

    public LagerFassade getLagerFassade() {
        return lagerFassade;
    }


}

