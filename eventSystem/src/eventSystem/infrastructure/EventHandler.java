package eventSystem.infrastructure;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;


public class EventHandler<T extends CRUDEventListener> {
    private List<T> listenerList = new LinkedList<>();

    public void addListener(T listener) {
        this.listenerList.add(listener);
    }
    public void removeListener(T listener) {
        this.listenerList.remove(listener);
    }

    public void handleEvent(EventObject event) {
        for (CRUDEventListener listener : listenerList) {
            listener.onEvent(event);
        }
    }
}
