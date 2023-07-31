package eventSystem.listener;

import java.util.EventListener;
import java.util.EventObject;

public interface CRUDEventListener<T extends  EventObject>extends EventListener {
    void onEvent(T event);

}
