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
import model.User;
import model.UserStore;

import java.io.IOException;

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
    public TextArea notesTextArea;
    private String firstName;
    private String lastName;
    private String medications;
    private String notes;
    private String caretaker;
    private String condition;
    private String patientID;
    private static PatientStore patientStore;
	public void addPatientOnAction(ActionEvent e)throws IOException {
        patientStore = PatientStore.getPatientStore();
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();
        medications = medicationTextArea.getText();
        notes = notesTextArea.getText();

        if (lastName == "") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Error");
            alert.setHeaderText("Information Missing");
            alert.setContentText(
                    "Please make sure last name field is filled out completely.");
            alert.showAndWait();
            e.consume();
        }
            else {
                Patient patient = new Patient(firstName, lastName, medications, condition, notes, caretaker);
                patientStore.insert(patient.getPatientID(), patient);
                patientStore.displayAll();
                PatientStore.savePatient();
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Patient Information Confirmation");
                alert2.setHeaderText("Patient saved");
                alert2.setContentText("Patient Info Saved. Returning to function page.");
                alert2.showAndWait();
                try {
                    Parent functionRoot = FXMLLoader.load(getClass().getResource("/view/HealthFunction.fxml"));
                    Scene functionScene = new Scene(functionRoot);
                    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    window.setScene(functionScene);
                    window.show();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
        }
	}
 	public void backOnAction(ActionEvent e)throws IOException {
        Parent functionRoot = FXMLLoader.load(getClass().getResource("/view/HealthFunction.fxml"));
        Scene functionScene = new Scene(functionRoot);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(functionScene);
        window.show();
	}

}

