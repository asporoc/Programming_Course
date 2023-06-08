package baseGUI;

import cargos.storableCargo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import verwaltung.Lager;

public class viewModel {

    @FXML private Button einfuegenButton;
    @FXML private Button entfernenButton;
    @FXML private Button ueberpruefenButton;
    @FXML private Button abrufenButton;
    @FXML private ListView<String> listView;
    @FXML private ComboBox<String> cargoTypeComboBox;
    @FXML private TextField ownerTextField;
    @FXML private TextField valueTextField;
    @FXML private TextField grainSizeTextField;
    @FXML private CheckBox flammableCheckBox;
    @FXML private CheckBox toxicCheckBox;
    @FXML private CheckBox explosiveCheckBox;
    @FXML private CheckBox radioactiveCheckBox;
    @FXML private CheckBox fragileCheckBox;
    @FXML private CheckBox pressurizedCheckBox;
    private String einfuegenString="";
    Lager guiLager = new Lager();
    public void initialize() {

        ObservableList<String> options = FXCollections.observableArrayList(
                "DryBulkCargo",
                "DryBulkAndUnitisedCargo",
                "LiquidBulkCargo",
                "LiquidBulkAndUnitisedCargo",
                "LiquidAndDryBulkCargo",
                "UnitisedCargo"
        );
        cargoTypeComboBox.setItems(options);
    }

    @FXML private ObservableList<storableCargo> items;



    public void einfuegenClick(ActionEvent actionEvent) {
        String owner = ownerTextField.getText().trim();
        String selectedOption = cargoTypeComboBox.getValue();
        if (selectedOption != null) {
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
                    einfuegenString+=","+"flammable";
                }
                if (toxic) {
                    einfuegenString+=","+"toxic";
                }
                if (explosive) {
                    einfuegenString+=","+"explosive";
                }
                if (radioactive) {
                    einfuegenString += "," + "radioactive";
                }
            }else { einfuegenString+=" ,";
            }
            einfuegenString+= " "+String.valueOf(fragileCheckBox.isSelected())+" "+String.valueOf(pressurizedCheckBox.isSelected());
            einfuegenString+= " "+grainSizeTextField.getText();
            guiLager.einfuegen(einfuegenString);
        } else {
            einfuegenString+=owner;
            guiLager.einfuegen(einfuegenString);
        }
        einfuegenString="";

    }



    public void abrufenClick(ActionEvent actionEvent) {

    }

    public void ueberpruefenClick(ActionEvent actionEvent) {
    }

    public void entfernenClick(ActionEvent actionEvent) {
    }
}
