package baseGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
}
