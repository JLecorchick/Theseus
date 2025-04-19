package projfxmldemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AppUtil {

     // Loads an FXML file + returns a new stage
     // If the provided filename already starts with '/'...it is used directly

    public static Stage loadFxml(String fxmlFileName, String title) {
        String resourcePath = fxmlFileName.startsWith("/") 
                ? fxmlFileName 
                : "/fxml/" + fxmlFileName;
        
        System.out.println("LOADING FXML → " + AppUtil.class.getResource("/fxml/" + fxmlFileName));

        try {
            URL fxmlUrl = AppUtil.class.getResource(resourcePath);
            if (fxmlUrl == null) {
                throw new IOException("FXML resource not found: " + resourcePath);
            }
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            return stage;
        } catch (Exception ex) {
            ex.printStackTrace(); 
            showError(
                "Could not load FXML: " + resourcePath,
                "Load Error",
                ex.getClass().getSimpleName() + ": " + ex.getMessage()
            );
            return null;
        }
    }

    public static Stage loadFxml(Parent root, String title) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            return stage;
        } catch (Exception ex) {
            ex.printStackTrace();  
            showError(
                "Could not create window for: " + title,
                "Stage Error",
                ex.getClass().getSimpleName() + ": " + ex.getMessage()
            );
            return null;
        }
    }

    // alert dialog
    public static void showAlert(String content, String title, String headerText) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setResizable(false);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // error dialog
    private static void showError(String content, String title, String headerText) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setResizable(false);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
