
// RUN PROJECT FROM HERE

package projfxmldemo;

import javafx.application.Application;
import javafx.stage.Stage;
import projfxmldemo.helpers.NavigationManager;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        NavigationManager.goTo("/fxml/Login.fxml", "RoomCycle - Log In");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
