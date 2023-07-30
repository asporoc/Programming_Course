package eventSystem.infrastructure;

import java.util.EventObject;

public class PersistenceErgebnisEvent extends EventObject {

    public PersistenceErgebnisEvent(Object source) {
        super(source);
    }
}
