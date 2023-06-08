package baseGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class guiImpl extends Application {
    @Override
    public void start(Stage mainStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("baseGUI.fxml"));
        Parent root = loader.load();
        viewModel controller = loader.getController();
        mainStage.setTitle("Storage Manager");
        mainStage.setScene(new Scene(root,950,440));
        mainStage.show();

    }
    public static void main(String[] args){launch(args);}



}
