package lk.ijse.mindwave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TherapyProgramManagementFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmFee;

    @FXML
    private TableColumn<?, ?> clmProgramId;

    @FXML
    private TableColumn<?, ?> clmProgramName;

    @FXML
    private Label durationLbl;

    @FXML
    private Label feeLbl;

    @FXML
    private Label idLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private AnchorPane pragramPane;

    @FXML
    private Pane programHead;

    @FXML
    private ImageView programImg;

    @FXML
    private TableView<?> tblTherapyPrograms;

    @FXML
    private Label titleLbl;

    @FXML
    private TextField txtTherapistAvailability;

    @FXML
    private TextField txtTherapistId;

    @FXML
    private TextField txtTherapistName;

    @FXML
    private TextField txtTherapistSpecialty;

    @FXML
    void btnDelete_OnAction(ActionEvent event) {

    }

    @FXML
    void btnSave_OnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdate_OnAction(ActionEvent event) {

    }

    @FXML
    void tblTherapistsOnClick(MouseEvent event) {

    }

}
