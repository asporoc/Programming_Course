package eventSystem.infrastructure;

import java.util.Date;
import java.util.EventObject;

public class InspectionErgebnisEvent extends EventObject {
    private Date newInspectionDate;

    public Date getNewInspectionDate() {
        return newInspectionDate;
    }

    public InspectionErgebnisEvent(Object source, Date newInspectionDate) {
        super(source);
        this.newInspectionDate = newInspectionDate;
    }
}
