package eventSystem.infrastructure;

import java.util.EventObject;

public class AbrufenEvent extends EventObject {
    private String type;
    private String option;


    public String getType() {
        return type;
    }

    public String getOption() {
        return option;
    }

    public AbrufenEvent(Object source, String type, String option) {
        super(source);
        this.type = type;
        this.option = option;
    }
    public AbrufenEvent(Object source, String type) {
        super(source);
        this.type = type;
    }
}
