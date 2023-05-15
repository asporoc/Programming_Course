package eventSystem.infrastructure;

import java.util.EventObject;

public class EinfuegenEvent extends EventObject {

    private String einfuegenString;
    public EinfuegenEvent(Object source, String einfuegenString) {
        super(source);
        this.einfuegenString = einfuegenString;
    }
    public String getEinfuegenString(){
        return einfuegenString;
    }
}
