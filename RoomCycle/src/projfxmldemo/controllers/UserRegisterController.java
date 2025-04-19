package projfxmldemo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import projfxmldemo.AppUtil;
import projfxmldemo.dao.InviteCodeDAO;
import projfxmldemo.dao.UserDAO;
import projfxmldemo.models.User;

public class UserRegisterController {

    @FXML private TextField tfInviteCode;
    @FXML private TextField tfUsername;
    @FXML private TextField tfAptName;
    @FXML private TextField tfPhone;
    @FXML private PasswordField pfPassword;
    @FXML private Button bnRegister;
    @FXML private Button btnCancel;

    private final UserDAO userDAO       = new UserDAO();
    private final InviteCodeDAO codeDAO = new InviteCodeDAO();
    @FXML
    private void onSignup(ActionEvent evt) {
        String code  = tfInviteCode.getText().trim();
        String user  = tfUsername .getText().trim();
        String apt   = tfAptName   .getText().trim();
        String phone = tfPhone     .getText().trim();
        String pass  = pfPassword  .getText();

        System.out.printf("DEBUG signup fields → code='%s', user='%s', apt='%s', phone='%s'%n",
                tfInviteCode.getText(),
                tfUsername.getText(),
                tfAptName.getText(),
                tfPhone.getText());
        
        if (code.isEmpty() || user.isEmpty() || apt.isEmpty() || phone.isEmpty() || pass.isEmpty()) {
            AppUtil.showAlert("All fields are required.", "Error", null);
            return;
        }

        if (!codeDAO.validateCode(code, apt)) {
            AppUtil.showAlert("Invalid invite code.", "Error", null);
            return;
        }

        User u = new User();
        u.setInviteCode(code);
        u.setUsername(user);
        u.setAptName(apt);
        u.setPhoneNum(phone);
        u.setAptNum("");  // no apt-num in UI

        if (userDAO.create(u, pass)) {
            codeDAO.markUsed(code);
            AppUtil.showAlert("Account created! Please log in.", "Success", null);
            ((Stage)bnRegister.getScene().getWindow()).close();
        } else {
            AppUtil.showAlert("Registration failed—try another username?", "Error", null);
        }
    }

    @FXML
    private void onBack(ActionEvent evt) {
        ((Stage)btnCancel.getScene().getWindow()).close();
    }
}
