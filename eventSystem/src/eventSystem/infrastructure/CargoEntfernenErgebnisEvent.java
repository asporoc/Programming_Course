package eventSystem.infrastructure;

import java.util.EventObject;

public class CargoEntfernenErgebnisEvent extends EventObject {

    Boolean ergebnis;

    public Boolean getErgebnis() {
        return ergebnis;
    }
    public CargoEntfernenErgebnisEvent(Object source,Boolean ergebnis) {
        super(source);
        this.ergebnis=ergebnis;
    }
}
