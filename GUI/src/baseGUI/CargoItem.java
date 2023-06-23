package baseGUI;

import cargos.UtilityClass;
import cargos.dryBulkCargoImpl;
import cargos.storableCargo;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;

import java.time.Duration;


public class CargoItem {



    private final StringProperty owner;
    private final ObjectProperty<Duration> durationOfStorage;
    private final StringProperty lastInspectionDate;
    private storableCargo cargo;
    private IntegerProperty storageLocation;
    private storableCargo dryBulkCargo;

    public CargoItem(storableCargo cargo) {
        this.cargo = cargo;
        this.storageLocation = new SimpleIntegerProperty(cargo.getStorageLocation());
        this.owner = new SimpleStringProperty(cargo.getOwner().getName());
        this.durationOfStorage = new SimpleObjectProperty<>(cargo.getDurationOfStorage());
        this.lastInspectionDate = new SimpleStringProperty(cargo.getLastInspectionDate().toString());
        Duration duration = cargo.getDurationOfStorage();
        updateDurationOfStorage(duration);


        Bindings.bindBidirectional(storageLocation, UtilityClass.storageLocationProperty(cargo));
    }


    public IntegerProperty storageLocationProperty() {
        return storageLocation;
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public ObjectProperty<Duration> durationOfStorageProperty() {
        return durationOfStorage;
    }

    public void setDryBulkCargoImpl(storableCargo dryBulkCargo) {
        this.dryBulkCargo = dryBulkCargo;
    }

    public void updateDurationOfStorage(Duration duration) {
        this.durationOfStorage.set(Duration.parse(duration.toString()));
    }

    public storableCargo getDryBulkCargoImpl() {
        return cargo;
    }
    public String getLastInspectionDate() {
        return lastInspectionDate.get();
    }
    public StringProperty lastInspectionDateProperty() {
        return lastInspectionDate;
    }

}
