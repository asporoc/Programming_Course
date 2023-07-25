package eventSystem.infrastructure;

import java.util.EventObject;

public class KundeEinfuegenErgebnisEvent extends EventObject {
    Boolean ergebnis;

    public Boolean getErgebnis() {
        return ergebnis;
    }

    public KundeEinfuegenErgebnisEvent(Object source, Boolean ergebnis) {
        super(source);
        this.ergebnis = ergebnis;

    }
}
