package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patient;
import model.PatientStore;
import model.Utilities;

import java.time.LocalDate;

public class AddPatientController {
    @FXML
    public TextField firstNameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public DatePicker birthDatePicker;
    @FXML
    public TextArea medicationTextArea;
    @FXML
    public TextField roomNumberTextField;
    @FXML
    public TextField conditionTextField;
    @FXML
    public TextField caretakerTextField;
    @FXML
    public TextArea notesTextArea;
    @FXML
    public DatePicker datePicker;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String medications;
    private String notes;
    private String caretaker;
    private String condition;
    private int roomNumber;
    private Patient patient;
    private static PatientStore patientStore;
    static int idCount;

	public void addPatientOnAction(ActionEvent e)throws IOException {
        idCount = Utilities.readPatientIdCount();
        Patient.setPatientIdCounter(idCount);

        patientStore = PatientStore.getPatientStore();
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();
        dateOfBirth = datePicker.getValue();
        medications = medicationTextArea.getText();
        roomNumber = Integer.parseInt(roomNumberTextField.getText());
        condition = conditionTextField.getText();
        caretaker = caretakerTextField.getText();
        notes = notesTextArea.getText();

        if (lastName.isBlank() || lastName == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Error");
            alert.setHeaderText("Name Information Missing");
            alert.setContentText(
                    "Please make sure last name field is filled out completely.");
            alert.showAndWait();
            e.consume();
        } else {
            patient = new Patient(firstName, lastName, dateOfBirth, medications, roomNumber, condition, notes, caretaker);
            patientStore.insert(patient.getPatientID(), patient);
            idCount++;
            Utilities.savePatientIdCount();

            PatientStore.savePatient();
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Patient Information Confirmation");
            alert2.setHeaderText("Patient saved");
            alert2.setContentText("Patient Info Saved. Returning to function page.");
            alert2.showAndWait();
            try {
                Parent functionRoot = FXMLLoader.load(getClass().getResource("/view/HealthFunction2.fxml"));
                Scene functionScene = new Scene(functionRoot);
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(functionScene);
                window.show();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

 	public void goBackOnAction(ActionEvent e)throws IOException {
        Parent functionRoot = FXMLLoader.load(getClass().getResource("/view/HealthFunction2.fxml"));
        Scene functionScene = new Scene(functionRoot);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(functionScene);
        window.show();
	}
}

