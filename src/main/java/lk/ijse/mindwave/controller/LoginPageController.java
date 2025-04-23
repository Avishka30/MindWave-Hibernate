package lk.ijse.mindwave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.mindwave.bo.AuthService;
import lk.ijse.mindwave.util.Role;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private Button btnSignIn;

    @FXML
    private ImageView docimg;

    @FXML
    private Label lblCreateAcc;

    @FXML
    private Label lblNotAcc;

    @FXML
    private AnchorPane loginPage;

    @FXML
    private Label logolbl;

    @FXML
    private AnchorPane semipage;

    @FXML
    private Label forgotPasLbl;

    @FXML
    private Label signinlbl;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    private final AuthService authService = new AuthService();
    @FXML
    void btnSignInOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Username and Password are Required!").show();
            return;
        }
        Role userRole = authService.authenticate(username, password);

        if (userRole != null) {
            navigateToDashBoard(userRole);
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid Username or Password!").show();
        }

    }
    private void navigateToDashBoard(Role role) {
        if (role == Role.ADMIN) { // enum ekn ena role ek blnv . ek constant value ekk
            loadUI("/view/AdminDashboard.fxml");
        } else if (role == Role.RECEPTIONIST) {
            loadUI("/view/ReceptionistDashboard.fxml");
        } else {
            new Alert(Alert.AlertType.ERROR,"Unauthorized access!").show();
        }
    }

    @FXML
    void forgotPassOnAction(MouseEvent event) {

    }

    @FXML
    void lblCreateAccOnClick(MouseEvent event) {
    loadUI("/view/SignUpPage.fxml");
    }

    private void loadUI(String ui) {
        try {
            loginPage.getChildren().clear();
            loginPage.getChildren().add(FXMLLoader.load(getClass().getResource(ui)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
