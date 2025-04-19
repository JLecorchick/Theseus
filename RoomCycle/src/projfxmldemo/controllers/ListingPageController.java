package projfxmldemo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import projfxmldemo.helpers.NavigationManager;

public class ListingPageController {

    @FXML private Button listingsbackButton;
    @FXML private Button exploremenuButton;
    @FXML private Button profilemenuButton;

    @FXML
    public void initialize() {
        listingsbackButton.setOnAction(this::onBack);
        exploremenuButton .setOnAction(this::goToExplore);
        profilemenuButton.setOnAction(this::goToProfile);
    }
    
    @FXML
    private void onBack(ActionEvent evt) {
        ((Stage)listingsbackButton.getScene().getWindow()).close();
    }
    
    @FXML
    private void goToExplore(ActionEvent evt) {
        NavigationManager.goTo("Explore.fxml", "Explore");
        ((Stage)exploremenuButton.getScene().getWindow()).close();
    }
    
    @FXML
    private void goToProfile(ActionEvent evt) {
        NavigationManager.goTo("Profile.fxml", "Your Profile");
        ((Stage)profilemenuButton.getScene().getWindow()).close();
    }
}
