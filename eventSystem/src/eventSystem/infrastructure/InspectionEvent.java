package eventSystem.infrastructure;

import java.util.EventObject;

public class InspectionEvent extends EventObject {

    private int storageLocation;
    public InspectionEvent(Object source, int storageLocation) {
        super(source);
        this.storageLocation = storageLocation;
    }
    public int getStorageLocation(){
        return storageLocation;
    }
}
