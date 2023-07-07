package eventSystem.infrastructure;

import java.util.EventObject;

public class PersistenceEvent extends EventObject {
    public PersistenceEvent(Object source) {
        super(source);
    }
}
