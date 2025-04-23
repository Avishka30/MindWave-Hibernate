package lk.ijse.mindwave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.mindwave.bo.custom.TherapySessionBO;
import lk.ijse.mindwave.bo.custom.impl.TherapySessionBOImpl;
import lk.ijse.mindwave.dto.TherapySessionDTO;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;

public class ManageTherapySessionController {


    @FXML
    private TextField txtPatientId;

    @FXML
    private TextField txtTherapistId;

    @FXML
    private TextField txtprogramId;

    @FXML
    private Button btnAddPatient;

    @FXML
    private Button btnAddTherapist;

    @FXML
    private Button btnAddTherapyProgram;

    @FXML
    private Button btnComplete;

    @FXML
    private Button btnSeeAll;

    @FXML
    private DatePicker dpSessionDate;

    @FXML
    private Label idLbl;

    @FXML
    private Label idLbl1;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label nameLbl;

    @FXML
    private AnchorPane sessionPane;

    @FXML
    private Pane theraphistHead;

    @FXML
    private ImageView theraphistImg;

    @FXML
    private Label titleLbl;

    @FXML
    private TextField txtSessionId;

    @FXML
    private TextField txtSessionTime;

    private final TherapySessionBO therapySessionBO = new TherapySessionBOImpl();


    private static ManageTherapySessionController instance;

    // set therapist id to text field
    public ManageTherapySessionController(){
        instance = this;
    }

    public static ManageTherapySessionController getInstance() {
        return instance;
    }

    public void setProgramId(String id) {txtprogramId.setText(id);}

    public void setPatientId(String id) {txtPatientId.setText(id);}

    public void setTherapistId(String id) {txtTherapistId.setText(id);}

    @FXML
    void btnAddPatient_OnAction(ActionEvent event) {
        loadUI("/view/PatientTable.fxml");

    }

    @FXML
    void btnAddTherapist_OnAction(ActionEvent event) {
        loadUI("/view/TherapistTable.fxml");

    }

    @FXML
    void btnAddTherapyProgram_OnAction(ActionEvent event) {
        loadUI("/view/ProgramTable.fxml");

    }

    @FXML
    void btnCompleteOnAction(ActionEvent event) {
        try {
            // Step 1: Validate input fields
            if (!validateInputs()) {
                return;
            }

            // Step 2: Collect session data
            TherapySessionDTO sessionDTO = collectSessionData();
            handlePaymentComplete(sessionDTO);

            // Step 3: Ask for confirmation
            if (confirmSessionBooking()) {
                // Step 4: Navigate to payment form
                navigateToPaymentForm(sessionDTO);

            }
        } catch (Exception e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }
    private boolean validateInputs() {
        // Validate session ID
        if (txtSessionId.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Session ID cannot be empty", Alert.AlertType.ERROR);
            txtSessionId.requestFocus();
            return false;
        }

        // Validate patient ID
        if (txtPatientId.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Patient ID cannot be empty", Alert.AlertType.ERROR);
            txtPatientId.requestFocus();
            return false;
        }

        // Validate therapist ID
        if (txtTherapistId.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Therapist ID cannot be empty", Alert.AlertType.ERROR);
            txtTherapistId.requestFocus();
            return false;
        }

        // Validate program ID
        if (txtprogramId.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Program ID cannot be empty", Alert.AlertType.ERROR);
            txtprogramId.requestFocus();
            return false;
        }

        // Validate date
        if (dpSessionDate.getValue() == null) {
            showAlert("Validation Error", "Session date must be selected", Alert.AlertType.ERROR);
            dpSessionDate.requestFocus();
            return false;
        }

        // Validate time
        try {
            LocalTime.parse(txtSessionTime.getText());
        } catch (Exception e) {
            showAlert("Validation Error", "Session time must be in a valid format (HH:MM)", Alert.AlertType.ERROR);
            txtSessionTime.requestFocus();
            return false;
        }

        return true;
    }
    private TherapySessionDTO collectSessionData() {
        TherapySessionDTO sessionDTO = new TherapySessionDTO();
        sessionDTO.setSessionId(txtSessionId.getText());
        sessionDTO.setPatientId(txtPatientId.getText());
        sessionDTO.setTherapistId(txtTherapistId.getText());
        sessionDTO.setProgramId(txtprogramId.getText());
        sessionDTO.setSessionDate(dpSessionDate.getValue());
        sessionDTO.setSessionTime(LocalTime.parse(txtSessionTime.getText()));
        return sessionDTO;
    }

    private boolean confirmSessionBooking() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Pay payment to confirm this session",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    private void navigateToPaymentForm(TherapySessionDTO sessionDTO) throws IOException {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ManagePaymentForm.fxml"));
            AnchorPane pane = loader.load();

            // Get the actual controller instance associated with the FXML
            ManagePaymentFormController paymentController = loader.getController();

            // Set session details
            paymentController.setSessionId(sessionDTO.getSessionId());
            paymentController.setParentController(this);

            // Now display the pane
            sessionPane.getChildren().setAll(pane);

            // Simulate the user completing the form and saving payment
            paymentController.savePaymentWithSession();


//            if (dto != null) {
//                handlePaymentComplete(sessionDTO);
//            }

        } catch (IOException e) {
            showAlert("Error", "Failed to load payment form!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void handlePaymentComplete(TherapySessionDTO sessionDTO) {
        try {
            boolean isBooked = therapySessionBO.bookSession(
                    sessionDTO.getSessionId(),
                    sessionDTO.getPatientId(),
                    sessionDTO.getTherapistId(),
                    sessionDTO.getProgramId(),
                    sessionDTO.getSessionDate(),
                    sessionDTO.getSessionTime()
            );

            if (isBooked) {
                showAlert("Success", "Therapy session booked successfully", Alert.AlertType.INFORMATION);
                clearFields();


            } else {
                showAlert("Error", "Failed to book session.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("Error", "Error saving session: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void onPaymentCompleted() {
        try {
            navigateToSessionList();
        } catch (IOException e) {
            showAlert("Error", "Could not navigate to session list after payment", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void navigateToSessionList() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ManageTherapySession.fxml"));
        AnchorPane pane = loader.load();
        sessionPane.getChildren().setAll(pane);
    }

    @FXML
    void btnSeeAllOnAction(ActionEvent event) {
        loadToUI("/view/TherapySessionTable.fxml");
    }

    private void loadUI(String ui) {
        try {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(FXMLLoader.load(getClass().getResource(ui)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadToUI(String ui) {
        try {
            sessionPane.getChildren().clear();
            sessionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ui)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        txtSessionId.clear();
        txtPatientId.clear();
        txtTherapistId.clear();
        txtprogramId.clear();
        txtSessionTime.clear();
        dpSessionDate.setValue(null);
    }

}
