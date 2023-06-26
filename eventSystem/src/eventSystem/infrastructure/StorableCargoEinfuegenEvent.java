package eventSystem.infrastructure;

import cargos.storableCargo;

import java.util.EventObject;

public class StorableCargoEinfuegenEvent extends EventObject {

    private storableCargo object;
    public StorableCargoEinfuegenEvent(Object source, storableCargo object) {
        super(source);
        this.object = object;
    }
    public storableCargo getObject(){
        return object;
    }
}
