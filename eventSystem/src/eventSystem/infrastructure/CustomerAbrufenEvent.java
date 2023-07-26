package eventSystem.infrastructure;

import verwaltung.LagerFassade;

import java.util.EventObject;

public class CustomerAbrufenEvent extends EventObject {
    LagerFassade lagerFassade;

    public LagerFassade getLagerFassade() {
        return lagerFassade;
    }

    public CustomerAbrufenEvent(Object source, LagerFassade lagerFassade) {
        super(source);
        this.lagerFassade = lagerFassade;
    }
}
