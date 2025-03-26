package lk.ijse.mindwave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.mindwave.bo.custom.UserBo;
import lk.ijse.mindwave.bo.custom.impl.UserBoImpl;
import lk.ijse.mindwave.util.Role;

import java.io.IOException;

public class SignUpPageController {

    @FXML
    private Button btnSignUp;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private ImageView docimg;

    @FXML
    private Label loginlbl;

    @FXML
    private Label loglbl;

    @FXML
    private Label logolbl;

    @FXML
    private AnchorPane semipage;

    @FXML
    private AnchorPane signupPage;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    private final UserBo userBo = new UserBoImpl();

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
            String fullName = txtFullName.getText();
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();
            String selectedRole = cmbRole.getValue();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
               new Alert(Alert.AlertType.ERROR,"Required fields are empty");
               return;
            }
            if (!password.equals(confirmPassword)) {
                new Alert(Alert.AlertType.ERROR,"Passwords do not match");
            }

        Role role = Role.valueOf(selectedRole);

            boolean save = userBo.saveUser(fullName,username,email,password,role);
            if (save){
                new Alert(Alert.AlertType.INFORMATION,"User has been created");
                loadUI("/view/LoginPage.fxml");
            }
            else{
                new Alert(Alert.AlertType.ERROR,"User has not been created");
            }

    }

    @FXML
    void loginonaction(MouseEvent event) {
        loadUI("/view/LoginPage.fxml");

    }

    private void loadUI(String ui) {
        try {
            signupPage.getChildren().clear();
            signupPage.getChildren().add(FXMLLoader.load(getClass().getResource(ui)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
