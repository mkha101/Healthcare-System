package controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import model.Patient;
import model.PatientStore;
import model.Utilities;
import java.io.IOException;

public class PatientUpdaterController {
    @FXML
    public Label patientIdLabel;
    @FXML
    public Label firstNameLabel;
    @FXML
    public Label lastNameLabel;
    @FXML
    public Label dateOfBirthLabel;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Label caretakerLabel;
    @FXML
    public Label conditionLabel;
    @FXML
    public Label medicationLabel;
    @FXML
    public Label roomNumberLabel;
    @FXML
    public Label notesLabel;
    @FXML
    public TextField searchPatientIdTextField;
    @FXML
    public TextField searchLastNameTextField;
    @FXML
    public TextField patientIdTextField;
    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public TextField caretakerTextField;
    @FXML
    public TextField conditionTextField;
    @FXML
    public TextField roomNumberTextField;
    @FXML
    public TextArea medicationTextArea;
    @FXML
    public TextArea notesTextArea;
    @FXML
    public Button updatePatientButton;
    private static PatientStore patientStore;
    private String searchPatientId;
    private String lastName;
    private Button yesBtn;
    private Button noBtn;

    public void searchPatientIdOnAction(ActionEvent e) throws IOException {
        searchLastNameTextField.clear();
        patientStore = PatientStore.getPatientStore();
        searchPatientId = searchPatientIdTextField.getText();
        if(patientStore.searchPatientId(searchPatientId)) {
            clearFields();
            Patient patient = patientStore.display(searchPatientId);
            appendPatientText(patient);
            makeVisible();
        }else{
            System.out.println("Id not found");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Error");
            alert.setHeaderText("Information Not Found");
            alert.setContentText(
                    "The patient Id was not found.");
            alert.showAndWait();
            e.consume();
            try {
                Parent signUpRoot = FXMLLoader.load(getClass().getResource("/view/PatientUpdater2.fxml"));
                Scene signUpScene = new Scene(signUpRoot);
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(signUpScene);
                window.show();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    public void searchLastNameOnAction(ActionEvent e) throws IOException {
        searchPatientIdTextField.clear();
        lastName = searchLastNameTextField.getText();
        if(!(patientStore.searchPatientLastName(lastName) == null)) {
            clearFields();
            appendPatientText(patientStore.display(patientStore.searchPatientLastName(lastName)));
            makeVisible();
        }else{
            System.out.println("Name not found");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Error");
            alert.setHeaderText("Last Name Error");
            alert.setContentText(
                    "The last name was not found.");
            alert.showAndWait();
            e.consume();
            try {
                Parent signUpRoot = FXMLLoader.load(getClass().getResource("/view/PatientUpdater2.fxml"));
                Scene signUpScene = new Scene(signUpRoot);
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(signUpScene);
                window.show();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    public void updatePatientOnAction(ActionEvent e) throws IOException {
        try{
        Stage newStage1 = Utilities.createChoiceStage();
        HBox buttonBox = Utilities.createButtonBox();
        VBox masterBox = Utilities.createMasterBox();
        noBtn = Utilities.createNoBtn();
        yesBtn = Utilities.creatYesBtn();
        Label displayLabel = Utilities.createDisplayLabel();
        Patient patient = patientStore.display(patientIdTextField.getText());

        yesBtn.setOnAction(e1 -> {
            patient.setFirstName(firstNameTextField.getText());
            patient.setLastName(lastNameTextField.getText());
            patient.setCondition(conditionTextField.getText());
            patient.setDateOfBirth(datePicker.getValue());
            patient.setCaretaker(caretakerTextField.getText());
            patient.setRoomNumber(Integer.parseInt(roomNumberTextField.getText()));
            patient.setMedications(medicationTextArea.getText());
            patient.setNotes(notesTextArea.getText());

        PatientStore.savePatient();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Information Update");
            alert.setContentText(
                    "The patient information has been updated.");
            alert.showAndWait();
            newStage1.close();
        });

        noBtn.setOnAction(e1 -> {
            newStage1.close();
        });

        buttonBox.getChildren().addAll(yesBtn, noBtn);
        masterBox.getChildren().addAll(displayLabel, buttonBox);
        newStage1.setScene(new Scene(masterBox, 550, 300));
        newStage1.showAndWait();

        Parent signUpRoot = FXMLLoader.load(getClass().getResource("/view/PatientUpdater2.fxml"));
        Scene signUpScene = new Scene(signUpRoot);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();

        }catch(Exception e2){
            e2.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Update Error");
            alert.setContentText(
                    "The patient information has NOT been updated.");
            alert.showAndWait();
        }
    }
    public void goBackOnAction(ActionEvent e) throws IOException {
        Parent functionRoot = FXMLLoader.load(getClass().getResource("/view/HealthFunction2.fxml"));
        Scene functionScene = new Scene(functionRoot);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(functionScene);
        window.show();
    }
    public void makeVisible(){
        patientIdLabel.setVisible(true);
        patientIdTextField.setVisible(true);
        firstNameLabel.setVisible(true);
        firstNameTextField.setVisible(true);
        lastNameLabel.setVisible(true);
        lastNameTextField.setVisible(true);
        dateOfBirthLabel.setVisible(true);
        datePicker.setVisible(true);
        caretakerLabel.setVisible(true);
        caretakerTextField.setVisible(true);
        conditionLabel.setVisible(true);
        conditionTextField.setVisible(true);
        roomNumberLabel.setVisible(true);
        roomNumberTextField.setVisible(true);
        notesLabel.setVisible(true);
        notesTextArea.setVisible(true);
        medicationLabel.setVisible(true);
        medicationTextArea.setVisible(true);
        updatePatientButton.setVisible(true);
    }
    private void clearFields() {
        patientIdTextField.clear();
        searchPatientIdTextField.clear();
        searchLastNameTextField.clear();
        firstNameTextField.clear();
        lastNameTextField.clear();
        caretakerTextField.clear();
        conditionTextField.clear();
        roomNumberTextField.clear();
        notesTextArea.clear();
        medicationTextArea.clear();
    }
    public void appendPatientText(Patient patient) {
        try {
            if (patient.getPatientID() != null) {
                patientIdTextField.appendText(patient.getPatientID());
            }
            if (patient.getFirstName() != null) {
                firstNameTextField.appendText(patient.getFirstName());
            }
            if (patient.getLastName() != null) {
                lastNameTextField.appendText(patient.getLastName());
            }
            if (patient.getDateOfBirth() != null) {
                datePicker.setValue(patient.getDateOfBirth());
            }
            if (patient.getCondition() != null) {
                conditionTextField.appendText(patient.getCondition());
            }
            if (patient.getCaretaker() != null) {
                caretakerTextField.appendText(patient.getCaretaker());
            }
            if(patient.getRoomNumber() != 0) {
                roomNumberTextField.appendText(String.valueOf(patient.getRoomNumber()));
            }
            if (patient.getMedications() != null) {
                medicationTextArea.appendText(patient.getMedications());
            }
            if (patient.getNotes() != null) {
                notesTextArea.appendText(patient.getNotes());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
