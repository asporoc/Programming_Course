package baseGUI;

import administration.Customer;
import cargo.Hazard;
import cargos.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import verwaltung.Kunde;
import verwaltung.Lager;
import JOS.*;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Controller {

    @FXML private Button saveButton;
    @FXML private Button loadButton;
    @FXML private ComboBox<String> abrufenComboBox;
    @FXML private AnchorPane anchorpane;
    @FXML private TableColumn<CargoItem,Integer> storageLocationTab;
    @FXML private TableColumn<CargoItem,String> cargoTypeTab;
    @FXML private TableColumn<CargoItem,String> ownerTab;
    @FXML private TableColumn<CargoItem,String> lastInspectionDateTab;
    @FXML private TableColumn<CargoItem,String> storageDurationTab;
    @FXML private TableView<CargoItem> cargoTableView;
    @FXML private ComboBox<String> cargoTypeComboBox;
    @FXML private ComboBox<Integer> entfernenLagerOrt;
    @FXML private ComboBox<Integer> ueberpruefenLagerOrt;
    @FXML private TextField ownerTextField;
    @FXML private TextField valueTextField;
    @FXML private TextField grainSizeTextField;
    @FXML private CheckBox flammableCheckBox;
    @FXML private CheckBox toxicCheckBox;
    @FXML private CheckBox explosiveCheckBox;
    @FXML private CheckBox radioactiveCheckBox;
    @FXML private CheckBox fragileCheckBox;
    @FXML private CheckBox pressurizedCheckBox;
    @FXML private ListView<String> abrufenListView;


    Lager guiLager = new Lager();
    private ArrayList<Integer> keys = new ArrayList<>();
    private ObservableList<CargoItem> cargoItems = FXCollections.observableArrayList();
    private String einfuegenString="";

    private ObservableList<Integer> storageLocations;

    public void updateItems() {
        cargoItems.clear();
        for (int i = 0; i < guiLager.getMaxsize(); i++) {
            if (guiLager.abrufen(i) != null) {
                storableCargo cargo = guiLager.abrufen(i);
                cargoItems.add(new CargoItem(cargo));
            }
        }
        cargoTableView.setItems(cargoItems);
    }

    public void updateStorageLocations(){
        keys = new ArrayList<>(guiLager.getCargoList().keySet());
        storageLocations = FXCollections.observableArrayList(keys);
        entfernenLagerOrt.setItems(storageLocations);
        ueberpruefenLagerOrt.setItems(storageLocations);
    }

    public void initialize() {
        cargoTableView.prefWidthProperty().bind(anchorpane.widthProperty());
        updateStorageLocations();
        updateItems();

        storageLocationTab.setCellValueFactory(new PropertyValueFactory<>("storageLocation"));
        ownerTab.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().ownerProperty().getValue()));
        lastInspectionDateTab.setCellValueFactory(new PropertyValueFactory<>("lastInspectionDate"));
        cargoTypeTab.setCellValueFactory(cellData -> cellData.getValue().cargoTypeProperty());
        lastInspectionDateTab.setComparator((dateString1, dateString2) -> {
            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = dateFormat.parse(dateString1);
                date2 = dateFormat.parse(dateString2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert date1 != null;
            return date1.compareTo(date2);
        });

        storageDurationTab.setCellValueFactory(cellData -> {
            CargoItem cargo = cellData.getValue();
            Duration duration = cargo.durationOfStorageProperty().get();
            long seconds = duration.getSeconds();
            String durationString = String.valueOf(seconds);
            return new SimpleStringProperty(durationString);
        });
        storageDurationTab.setComparator((durationString1, durationString2) -> {
            Duration duration1 = Duration.ofSeconds(Long.parseLong(durationString1));
            Duration duration2 = Duration.ofSeconds(Long.parseLong(durationString2));
            return duration1.compareTo(duration2);
        });

        ObservableList<String> options = FXCollections.observableArrayList(
                "",
                "DryBulkCargo",
                "DryBulkAndUnitisedCargo",
                "LiquidBulkCargo",
                "LiquidBulkAndUnitisedCargo",
                "LiquidAndDryBulkCargo",
                "UnitisedCargo"
        );
        cargoTypeComboBox.setItems(options);
        ObservableList<String> abrufenOptions = FXCollections.observableArrayList(
                "",
                "Customer",
                "Hazards"
        );
        abrufenComboBox.setItems(abrufenOptions);
        cargoTableView.setRowFactory(tv -> {
            TableRow<CargoItem> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Dragboard dragboard = row.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(String.valueOf(row.getIndex()));
                    dragboard.setContent(content);
                }
                event.consume();
            });

            row.setOnDragOver(event -> {
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasString() && row.getIndex() != -1) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            row.setOnDragDropped(event -> {
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasString()) {
                    int draggedIndex = Integer.parseInt(dragboard.getString());
                    int targetIndex = row.getIndex();

                    guiLager.setStorageLocation(draggedIndex, targetIndex);

                    cargoTableView.getItems().setAll(cargoItems);
                    updateItems();
                }

                event.consume();
            });


            return row;
        });
    }
    private Map<String, Integer> getCustomerInfo() {
        Map<String, Integer> customerCargoMap = new HashMap<>();

        for (storableCargo cargo : guiLager.getCargoList().values()) {
            String customerName = cargo.getOwner().getName();

            if (customerCargoMap.containsKey(customerName)) {
                int currentCargoAmount = customerCargoMap.get(customerName);
                customerCargoMap.put(customerName, currentCargoAmount + 1);
            } else {
                customerCargoMap.put(customerName, 1);
            }
        }

        for (Customer customer : guiLager.getCustomerList()) {
            String customerName = customer.getName();

            if (!customerCargoMap.containsKey(customerName)) {
                customerCargoMap.put(customerName, 0);
            }
        }

        return customerCargoMap;
    }

    private void displayCustomerInfo() {
        Map<String, Integer> customerCargoMap = getCustomerInfo();

        abrufenListView.getItems().clear();

        for (Map.Entry<String, Integer> entry : customerCargoMap.entrySet()) {
            String customerName = entry.getKey();
            int cargoAmount = entry.getValue();

            String itemText = customerName + " - Cargo Amount: " + cargoAmount;

            abrufenListView.getItems().add(itemText);
        }
    }
    private Map<Hazard, Integer> getHazardInfo() {
        Map<Hazard, Integer> hazardCountMap = new HashMap<>();

        for (storableCargo cargo : guiLager.getCargoList().values()) {
            Collection<Hazard> hazards = cargo.getHazards();

            for (Hazard hazard : hazards) {
                if (hazardCountMap.containsKey(hazard)) {
                    int currentCount = hazardCountMap.get(hazard);
                    hazardCountMap.put(hazard, currentCount + 1);
                } else {
                    hazardCountMap.put(hazard, 1);
                }
            }
        }

        return hazardCountMap;
    }

    private void displayHazardInfo() {
        Map<Hazard, Integer> hazardCountMap = getHazardInfo();

        abrufenListView.getItems().clear();

        for (Map.Entry<Hazard, Integer> entry : hazardCountMap.entrySet()) {
            Hazard hazard = entry.getKey();
            int count = entry.getValue();

            String itemText = hazard.toString() + " - Count: " + count;

            abrufenListView.getItems().add(itemText);
        }
    }

    public void einfuegenClick(ActionEvent actionEvent) {
        storableCargo cargo=null;
        EnumSet<Hazard> hazards = EnumSet.noneOf(Hazard.class);
        String owner = ownerTextField.getText().trim();
        String selectedOption = cargoTypeComboBox.getValue();
        if (selectedOption != null && !selectedOption.equals("")) {
            einfuegenString += selectedOption+" ";
            einfuegenString += owner;
            int value = Integer.parseInt(valueTextField.getText().trim());
            einfuegenString += " "+value;
            boolean flammable = flammableCheckBox.isSelected();
            boolean explosive = explosiveCheckBox.isSelected();
            boolean toxic = toxicCheckBox.isSelected();
            boolean radioactive = radioactiveCheckBox.isSelected();
            if(flammable||toxic||radioactive||explosive){
                if(flammable){
                    hazards.add(Hazard.flammable);
                }
                if (toxic) {
                    hazards.add(Hazard.toxic);
                }
                if (explosive) {
                    hazards.add(Hazard.explosive);
                }
                if (radioactive) {
                    hazards.add(Hazard.radioactive);
                }
            }else { einfuegenString+=" ,";
            }
            einfuegenString+= " "+ fragileCheckBox.isSelected() +" "+ pressurizedCheckBox.isSelected();
            einfuegenString+= " "+grainSizeTextField.getText();
            switch(selectedOption) {
                case "DryBulkCargo":
                    cargo = new DryBulkCargoImpl(new Kunde(owner), new BigDecimal(value), hazards, Integer.parseInt(grainSizeTextField.getText()));
                    break;
                case "DryBulkAndUnitisedCargo":
                    cargo = new DryBulkAndUnitisedCargoImpl(new Kunde(owner), new BigDecimal(value), hazards, Integer.parseInt(grainSizeTextField.getText()), fragileCheckBox.isSelected());
                    break;
                case "LiquidAndDryBulkCargo":
                    cargo = new LiquidAndDryBulkCargoImpl(new Kunde(owner), new BigDecimal(value), hazards, pressurizedCheckBox.isSelected(), Integer.parseInt(grainSizeTextField.getText()));
                    break;
                case "LiquidBulkCargo":
                    cargo = new LiquidBulkCargoImpl(new Kunde(owner), new BigDecimal(value), hazards, pressurizedCheckBox.isSelected());
                    break;
                case "UnitisedCargo":
                    cargo = new UnitisedCargoImpl(new Kunde(owner), new BigDecimal(value), hazards, fragileCheckBox.isSelected());
                    break;
                case "LiquidBulkAndUnitisedCargo":
                    cargo = new LiquidBulkAndUnitisedCargoImpl((new Kunde(owner)), new BigDecimal(value), hazards, fragileCheckBox.isSelected(),pressurizedCheckBox.isSelected());
            }
            guiLager.einfuegen(cargo);
        } else {
            guiLager.einfuegen(new Kunde(owner));
        }
        einfuegenString="";
        updateItems();
        updateStorageLocations();
    }

    public void abrufenClick(ActionEvent actionEvent) {
        String selectedOption = abrufenComboBox.getValue();
        switch (selectedOption){
            case "Customer":
                displayCustomerInfo();
                break;
            case "Hazards":
                displayHazardInfo();
                break;

        }

    }

    public void ueberpruefenClick(ActionEvent actionEvent) {
        if (ueberpruefenLagerOrt.getValue() != null) {
            guiLager.inspection(ueberpruefenLagerOrt.getValue());
            updateItems();
            updateStorageLocations();
        }
    }

    public void entfernenClick(ActionEvent actionEvent) {
        guiLager.entfernen(entfernenLagerOrt.getValue());
        updateItems();
        updateStorageLocations();
    }

    public void saveClick(ActionEvent actionEvent) {
        JOSItemSerializationUtils.serialize("test.ser",guiLager);

    }

    public void loadClick(ActionEvent actionEvent) {
        guiLager=JOSItemSerializationUtils.deserialize("test.ser");
        updateItems();
    }
}