package lk.ijse.mindwave.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.mindwave.bo.custom.TherapySessionBO;
import lk.ijse.mindwave.bo.custom.impl.TherapySessionBOImpl;
import lk.ijse.mindwave.dto.TherapySessionDTO;
import lk.ijse.mindwave.view.tdm.TherapySessionTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapySessionTableFormController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        clmTime.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        clmProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        clmTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));

        loadSessions();
    }

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<TherapySessionTM, LocalDate> clmDate;

    @FXML
    private TableColumn<TherapySessionTM, String> clmPatientId;

    @FXML
    private TableColumn<TherapySessionTM, String> clmProgramId;

    @FXML
    private TableColumn<TherapySessionTM, String> clmSessionId;

    @FXML
    private TableColumn<TherapySessionTM, String> clmStatus;

    @FXML
    private TableColumn<TherapySessionTM, String> clmTherapistId;

    @FXML
    private TableColumn<TherapySessionTM, LocalTime> clmTime;

    @FXML
    private AnchorPane sessionTablePane;

    @FXML
    private TableView<TherapySessionTM> tblTherapySession;

    private final TherapySessionBO therapySessionBO = new TherapySessionBOImpl();

    private void loadSessions() {
        ArrayList<TherapySessionDTO> sessions =  therapySessionBO.loadAllSessions();
        ObservableList<TherapySessionTM> sessionTMS = FXCollections.observableArrayList();

        for (TherapySessionDTO dto : sessions) {

            TherapySessionTM sessionTM = new TherapySessionTM(
                    dto.getSessionId(),
                    dto.getSessionDate(),
                    dto.getSessionTime(),
                    dto.getStatus(),
                    dto.getPatientId(),
                    dto.getProgramId(),
                    dto.getTherapistId()

            );

            sessionTMS.add(sessionTM);
        }
        tblTherapySession.setItems(sessionTMS);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        loadUI("/view/ManageTherapySession.fxml");
    }
    private void loadUI(String ui) {
        try {
            sessionTablePane.getChildren().clear();
            sessionTablePane.getChildren().add(FXMLLoader.load(getClass().getResource(ui)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
