package cargos;

import administration.Storable;
import cargo.Cargo;
import javafx.beans.property.IntegerProperty;

import java.io.Serializable;
import java.util.Date;

public interface storableCargo extends Storable,Cargo, Serializable, EinfuegenObject {
    void setStorageLocation(int location);
    void setLastInspectionDate(Date date);
    IntegerProperty storageLocationProperty();

}
