package baseGUI;

import cargos.storableCargo;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

import java.time.Duration;


public class CargoItem {



    private final StringProperty owner;
    private final ObjectProperty<Duration> durationOfStorage;
    private final StringProperty lastInspectionDate;
    private final SimpleStringProperty cargoType;
    private final storableCargo cargo;
    private final IntegerProperty storageLocation;

    public CargoItem(storableCargo cargo) {
        this.cargo = cargo;
        this.storageLocation = new SimpleIntegerProperty(cargo.getStorageLocation());
        this.owner = new SimpleStringProperty(cargo.getOwner().getName());
        this.durationOfStorage = new SimpleObjectProperty<>(cargo.getDurationOfStorage());
        this.lastInspectionDate = new SimpleStringProperty(cargo.getLastInspectionDate().toString());
        this.cargoType = new SimpleStringProperty(cargo.getClass().getSimpleName().replaceAll("Impl$", ""));
        Duration duration = cargo.getDurationOfStorage();
        updateDurationOfStorage(duration);


        //Bindings.bindBidirectional(storageLocation, UtilityClass.storageLocationProperty(cargo));
        Bindings.bindBidirectional(storageLocation, cargo.storageLocationProperty());
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

    public ObservableValue<String> cargoTypeProperty() {
        return cargoType;
    }
}
