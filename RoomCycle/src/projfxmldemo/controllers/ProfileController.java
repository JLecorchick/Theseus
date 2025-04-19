package projfxmldemo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projfxmldemo.dao.InviteCodeDAO;
import projfxmldemo.dao.UserDAO;
import projfxmldemo.helpers.CurrentUser;
import projfxmldemo.helpers.NavigationManager;
import projfxmldemo.models.User;
import projfxmldemo.AppUtil;

import java.io.File;

public class ProfileController {
    @FXML private Label lblUsername, lblAptName, lblInviteCode;
    @FXML private ImageView profileImage;
    @FXML private Button btnChangePhoto, btnGenerateCode, btnLogout; //, btnUpdateInfo;
    @FXML private TextField tfNewUsername, tfNewPhone;
    @FXML private PasswordField pfNewPassword;
    //@FXML private Button btnCancel;
    @FXML private Button listmenuButton;
    @FXML private Button exploremenuButton; 
    @FXML private Button profilemenuButton;
    
    private final UserDAO userDAO = new UserDAO();
    private final InviteCodeDAO codeDAO = new InviteCodeDAO();
    private User user;

    @FXML
    public void initialize() {
        String current = CurrentUser.getUsername();
        user = userDAO.findByUsername(current);
        lblUsername.setText(user.getUsername());
        lblAptName.setText(user.getAptName());

        listmenuButton.setOnAction(evt ->
        NavigationManager.goTo("ListingPage.fxml", "Your Listings")
    );

    exploremenuButton.setOnAction(evt ->
        NavigationManager.goTo("Explore.fxml", "Explore")
    );

    profilemenuButton.setOnAction(evt ->
        NavigationManager.goTo("Profile.fxml", "Your Profile")
    );
    
        btnGenerateCode.setOnAction(e -> {
            String code = codeDAO.generate(user.getAptName(), user.getUserId());
            lblInviteCode.setText("New code: " + code);
        });

        btnChangePhoto.setOnAction(e -> {
            FileChooser ch = new FileChooser();
            ch.setTitle("Select Profile Photo");
            ch.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images","*.png","*.jpg","*.jpeg"));
            File f = ch.showOpenDialog(null);
            if (f!=null) {
                profileImage.setImage(new Image(f.toURI().toString()));
            }
        });

/*        btnUpdateInfo.setOnAction(e -> {
            if (!tfNewUsername.getText().trim().isEmpty())
                user.setUsername(tfNewUsername.getText().trim());
            if (!tfNewPhone.getText().trim().isEmpty())
                user.setPhoneNum(tfNewPhone.getText().trim());
            if (!pfNewPassword.getText().isEmpty())
                userDAO.updatePassword(user.getUserId(), pfNewPassword.getText());
            userDAO.update(user);
            AppUtil.showAlert("Profile updated!","Success",null);
            lblUsername.setText(user.getUsername());
        });
*/
        btnLogout.setOnAction(e -> {
            CurrentUser.setUsername(null);
            NavigationManager.goTo("/fxml/Login.fxml","Log In");
            ((Stage)btnLogout.getScene().getWindow()).close();
        });

    }
}
