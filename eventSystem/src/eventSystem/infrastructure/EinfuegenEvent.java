package eventSystem.infrastructure;

import cargos.storableCargo;

import java.util.EventObject;

public class EinfuegenEvent extends EventObject {

    private storableCargo object;
    public EinfuegenEvent(Object source, storableCargo object) {
        super(source);
        this.object = object;
    }
    public storableCargo getObject(){
        return object;
    }
}
