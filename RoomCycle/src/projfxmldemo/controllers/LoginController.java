package projfxmldemo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import projfxmldemo.AppUtil;
import projfxmldemo.dao.UserDAO;
import projfxmldemo.helpers.CurrentUser;
import projfxmldemo.helpers.NavigationManager;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Hyperlink signupLink;
    @FXML private Label lbMessage;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        loginButton.setOnAction(evt -> handleLogin());
        signupLink.setOnAction(evt -> NavigationManager.goTo("/fxml/Signup.fxml", "Sign Up"));
    }

    private void handleLogin() {
        String u = usernameField.getText().trim();
        String p = passwordField.getText();
        if (u.isEmpty() || p.isEmpty()) {
            lbMessage.setText("Username & password required.");
        } else if (userDAO.validateLogin(u, p)) {
            CurrentUser.setUsername(u);
            System.out.println("look for /fxml/Explore.fxml → " +
            AppUtil.class.getResource("/fxml/Explore.fxml"));
            NavigationManager.goTo("/fxml/Explore.fxml", "Explore");
            ((Stage)loginButton.getScene().getWindow()).close();
        } else {
            lbMessage.setText("Invalid credentials.");
        }
    }
}
