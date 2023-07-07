package eventSystem.listener;

import JOS.JOSItemSerializationUtils;
import eventSystem.infrastructure.CRUDEventListener;
import eventSystem.infrastructure.PersistenceEvent;
import verwaltung.Lager;

import java.util.EventObject;

public class PersistenceListener implements CRUDEventListener {
    Lager lager;
    public PersistenceListener(Lager lager) {
        this.lager = lager;
    }

    @Override
    public void onEvent(EventObject event) {
        String josString = ((PersistenceEvent)event).getJos();
        if(josString.equals("saveJOS")){
            JOSItemSerializationUtils.serialize("test.ser", ((PersistenceEvent) event).getLager());
        }
        else if(josString.equals("loadJOS")){
            lager = JOSItemSerializationUtils.deserialize("test.ser");
        }

    }
}
