package lk.ijse.mindwave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private AnchorPane adminDashBoardPage;

    @FXML
    private Pane cardpane;

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
    private Button programManPage;

    @FXML
    private Button reportBtn;

    @FXML
    private ImageView reportImg;

    @FXML
    private Button sessionBtn;

    @FXML
    private ImageView sessionImg;

    @FXML
    private Label tManagementLbl;

    @FXML
    private Label tManagementLbl1;

    @FXML
    private Label tManagementLbl2;

    @FXML
    private Label tManagementLbl3;

    @FXML
    private Label tManagementLbl4;

    @FXML
    private Label tManagementLbl5;

    @FXML
    private ImageView tharaphistImg;

    @FXML
    private Label theraphistLbl;

    @FXML
    private Label theraphistLbl1;

    @FXML
    private Label theraphistLbl2;

    @FXML
    private Label theraphistLbl3;

    @FXML
    private Label theraphistLbl4;

    @FXML
    private Label theraphistLbl5;

    @FXML
    private Button theraphistManBtn;

    @FXML
    private ImageView therapymanImg;

    @FXML
    private Pane userRoleManPane;

    @FXML
    private Pane userRoleManPane1;

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

    }

    @FXML
    void patmentPageOnAction(ActionEvent event) {

    }

    @FXML
    void reportPageOnAction(ActionEvent event) {

    }

    @FXML
    void sessionPageOnAction(ActionEvent event) {

    }

    @FXML
    void theraphistPageAction(ActionEvent event) {
        loadUI("/view/TherapistManagementForm.fxml");
    }

    @FXML
    void therapyProgramManAction(ActionEvent event) {
        loadUI("/view/TherapyProgramManagementForm.fxml");

    }
    private void loadUI(String ui) {
        try {
            loadPane.getChildren().clear();
            loadPane.getChildren().add(FXMLLoader.load(getClass().getResource(ui)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
