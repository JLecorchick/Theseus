package projfxmldemo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import projfxmldemo.helpers.NavigationManager;

public class ExploreController {
  @FXML private Button listmenuButton;
  @FXML private Button exploremenuButton; 
  @FXML private Button profilemenuButton;
  @FXML private Button btnCancel;

  @FXML
  public void initialize() {
    listmenuButton.setOnAction(e ->
      NavigationManager.goTo("ListingPage.fxml", "Your Listings")
    );

    profilemenuButton.setOnAction(e ->
      NavigationManager.goTo("Profile.fxml", "Your Profile")
    );
    
  }
    
    @FXML
    private void onBack(ActionEvent evt) {
        ((Stage)btnCancel.getScene().getWindow()).close();
  }
}
