package lk.ijse.mindwave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReceptionistDashboardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI("/view/ManageTherapySession.fxml");
    }

    @FXML
    private Pane cardpane;

    @FXML
    private ImageView logoutImg2;

    @FXML
    private AnchorPane loadPane;

    @FXML
    private Button patientBtn;

    @FXML
    private ImageView patientImg;

    @FXML
    private Button paymentBtn;

    @FXML
    private ImageView paymentImg;

    @FXML
    private Button reportBtn;

    @FXML
    private ImageView reportImg;

    @FXML
    private Button sessionBtn;

    @FXML
    private AnchorPane receptioanistPane;

    @FXML
    private ImageView sessionImg;

    @FXML
    private Label tManagementLbl2;

    @FXML
    private Label tManagementLbl3;

    @FXML
    private Label tManagementLbl4;

    @FXML
    private Label tManagementLbl5;

    @FXML
    private Label theraphistLbl2;

    @FXML
    private Label theraphistLbl3;

    @FXML
    private Label theraphistLbl4;

    @FXML
    private Label theraphistLbl5;

    @FXML
    private Pane userRoleManPane2;

    @FXML
    private Pane userRoleManPane3;

    @FXML
    private Pane userRoleManPane4;

    @FXML
    private Pane userRoleManPane5;


    @FXML
    void patientOnAction(ActionEvent event) {
        loadUI("/view/PatientManagementForm.fxml");

    }

    @FXML
    void patmentPageOnAction(ActionEvent event) {
        loadUI("/view/ManagePaymentForm.fxml");

    }

    @FXML
    void reportPageOnAction(ActionEvent event) {

    }

    @FXML
    void sessionPageOnAction(ActionEvent event) {
        loadUI("/view/ManageTherapySession.fxml");

    }
    private void loadUI(String ui) {
        try {
            loadPane.getChildren().clear();
            loadPane.getChildren().add(FXMLLoader.load(getClass().getResource(ui)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadToUI(String ui) {
        try {
            receptioanistPane.getChildren().clear();
            receptioanistPane.getChildren().add(FXMLLoader.load(getClass().getResource(ui)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogOutOnAction(MouseEvent mouseEvent) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to log out", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            loadToUI("/view/LoginPage.fxml");
        }else {
            //
        }
    }
}
