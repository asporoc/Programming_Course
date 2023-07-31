package eventSystem.infrastructure;

import java.util.EventObject;

public class KundeEntfernenErgebnisEvent extends EventObject {
    final Boolean ergebnis;

    public Boolean getErgebnis() {
        return ergebnis;
    }

    public KundeEntfernenErgebnisEvent(Object source, Boolean ergebnis) {
        super(source);
        this.ergebnis = ergebnis;
    }
}
