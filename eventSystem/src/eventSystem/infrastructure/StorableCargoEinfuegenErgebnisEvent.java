package eventSystem.infrastructure;

import java.util.EventObject;

public class StorableCargoEinfuegenErgebnisEvent extends EventObject {
    Boolean ergebnis;

    public Boolean getErgebnis() {
        return ergebnis;
    }

    public StorableCargoEinfuegenErgebnisEvent(Object source, Boolean ergebnis) {
        super(source);
        this.ergebnis = ergebnis;

    }
}
