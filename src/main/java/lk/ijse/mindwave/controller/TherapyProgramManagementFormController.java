package lk.ijse.mindwave.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.mindwave.bo.custom.TherapyProgramsBO;
import lk.ijse.mindwave.bo.custom.impl.TherapyProgramsBOImpl;
import lk.ijse.mindwave.dto.TherapyProgramDTO;
import lk.ijse.mindwave.view.tdm.TherapyProgramTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyProgramManagementFormController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TherapyProgramTM, Integer> clmDuration;

    @FXML
    private TableColumn<TherapyProgramTM, Double> clmFee;

    @FXML
    private TableColumn<TherapyProgramTM, String> clmProgramId;

    @FXML
    private TableColumn<TherapyProgramTM, String> clmProgramName;

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
    private TableView<TherapyProgramTM> tblTherapyPrograms;

    @FXML
    private Label titleLbl;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtProgramDuration;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;

    private final TherapyProgramsBO therapyProgramsBO = new TherapyProgramsBOImpl();


    @FXML
    void btnDelete_OnAction(ActionEvent event) {
        TherapyProgramTM selectedTherapyProgram = tblTherapyPrograms.getSelectionModel().getSelectedItem();
        if (selectedTherapyProgram == null) {
            showAlert("Warning", "Please select a therapy program to delete!", Alert.AlertType.WARNING);
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this therapy program?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                boolean isDelete = therapyProgramsBO.deleteTherapyPrograms(selectedTherapyProgram.getProgramId());

                if (isDelete) {
                    showAlert("Success", "Therapy program deleted successfully!", Alert.AlertType.INFORMATION);

                } else {
                    showAlert("Error", "Failed to delete therapy program!", Alert.AlertType.ERROR);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            loadTherapyPrograms();
            clearFields();
            generateNewID();
        }

    }

    @FXML
    void btnSave_OnAction(ActionEvent event) {
        String id = txtProgramId.getText();
        String name = txtProgramName.getText();
        int duration = Integer.parseInt(txtProgramDuration.getText());
        double fee = Double.parseDouble(txtFee.getText());

        if(id.isEmpty() || name.isEmpty() || duration < 0 || fee < 0)  {
            showAlert("Warning", "Please fill all fields!", Alert.AlertType.WARNING);
            return;
        }

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(id, name, duration, fee);
        try{
            boolean isSaved = therapyProgramsBO.saveTherapyPrograms(therapyProgramDTO);

            if (isSaved) {
                showAlert("Success", "Therapy Program save successfully!", Alert.AlertType.INFORMATION);

            } else {
                showAlert("Error", "Failed to save Therapy Program!", Alert.AlertType.ERROR);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        loadTherapyPrograms();
        clearFields();
        generateNewID();

    }

    @FXML
    void btnUpdate_OnAction(ActionEvent event) {
        String id = txtProgramId.getText();
        String name = txtProgramName.getText();
        int duration = Integer.parseInt(txtProgramDuration.getText());
        double fee = Double.parseDouble(txtFee.getText());

        if(id.isEmpty() || name.isEmpty() || duration < 0 || fee < 0)  {
            showAlert("Warning", "Please fill all fields!", Alert.AlertType.WARNING);
            return;
        }

        TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(id, name, duration, fee );
        try{
            boolean isUpdate = therapyProgramsBO.updateTherapyPrograms(therapyProgramDTO);

            if (isUpdate) {
                showAlert("Success", "Therapy Program update successfully!", Alert.AlertType.INFORMATION);

            } else {
                showAlert("Error", "Failed to update Therapy Program!", Alert.AlertType.ERROR);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        loadTherapyPrograms();
        clearFields();
        generateNewID();
    }

    @FXML
    void tblTherapistsOnClick(MouseEvent event) {
        TherapyProgramTM selectedItem = tblTherapyPrograms.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert("Warning", "Please select a therapy program", Alert.AlertType.WARNING);
        }else {
            txtProgramId.setText(selectedItem.getProgramId());
            txtProgramName.setText(selectedItem.getProgramName());
            txtProgramDuration.setText(String.valueOf(selectedItem.getDuration()));
            txtFee.setText(String.valueOf(selectedItem.getFee()));
//            txtTherapistId.setText(selectedItem.getTherapistId());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        clmProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clmFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadTherapyPrograms();
        generateNewID();
    }

    private void generateNewID() {
        txtProgramId.setText(therapyProgramsBO.getNextTherapyProgramId());
    }

    private void loadTherapyPrograms() {
        try  {
            ArrayList<TherapyProgramDTO> therapyProgramDTOS = therapyProgramsBO.loadAllTherapyPrograms();
            ObservableList<TherapyProgramTM> therapyProgramTMSList = FXCollections.observableArrayList();

            for (TherapyProgramDTO therapyProgramDTO : therapyProgramDTOS) {

                TherapyProgramTM therapyProgramTM = new TherapyProgramTM(
                        therapyProgramDTO.getProgramId(),
                        therapyProgramDTO.getProgramName(),
                        therapyProgramDTO.getDuration(),
                        therapyProgramDTO.getFee()
                );

                therapyProgramTMSList.add(therapyProgramTM);
            }
            tblTherapyPrograms.setItems(therapyProgramTMSList);
        } catch (Exception e) {
            showAlert("Error", "Failed to load therapists!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    private void clearFields() {
        txtProgramId.clear();
        txtProgramName.clear();
        txtProgramDuration.clear();
        txtFee.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
