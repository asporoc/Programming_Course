package eventSystem.infrastructure;

import java.util.EventListener;
import java.util.EventObject;

public interface CRUDEventListener<T extends  EventObject>extends EventListener {
    public void onEvent(T event);

}
