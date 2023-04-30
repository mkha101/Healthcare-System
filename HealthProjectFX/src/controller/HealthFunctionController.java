package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patient;
import model.PatientStore;
public class HealthFunctionController {
    @FXML
    public TextField searchPatientTextField;
    @FXML
    public TextField searchRoomTextField;
    @FXML
    public TextField searchLastNameTextField;
    @FXML
    public TextArea patientInfoTextArea;
    @FXML
    public Button searchPatientIdButton;
    @FXML
    public Button searchRoomButton;
    @FXML
    public Button searchLastNameButton;
    private String patientId;
    private int roomNumber;
    private String lastName;
    private static PatientStore patientStore;

    public void searchPatientIdOnAction(ActionEvent e) throws IOException {
        try {
            patientInfoTextArea.clear();
            patientStore = PatientStore.getPatientStore();
            patientId = searchPatientTextField.getText();
            clearFields();
            if (patientStore.searchPatientId(patientId)) {
                Patient patient = patientStore.display(patientId);
                patientInfoTextArea.setVisible(true);
                patientInfoTextArea.appendText(patient.toString());
            } else {
                patientInfoTextArea.setVisible(true);
                patientInfoTextArea.clear();
                patientInfoTextArea.appendText("patientId not found");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            patientInfoTextArea.clear();
            patientInfoTextArea.appendText("Please enter an I.D. to search.");
        }
    }
    public void searchRoomOnAction(ActionEvent e) throws IOException{
        try{
            Integer.valueOf(searchRoomTextField.getText());
            patientInfoTextArea.clear();
            patientStore = PatientStore.getPatientStore();
            roomNumber = Integer.parseInt(searchRoomTextField.getText());
            clearFields();
            if (patientStore.searchPatientRoom(roomNumber) == null) {
                patientInfoTextArea.setVisible(true);
                patientInfoTextArea.appendText("roomNumber not in use");
            }
            else {
                ArrayList<Patient> arr = (ArrayList<Patient>) patientStore.searchPatientRoom(roomNumber);
                patientInfoTextArea.clear();
                patientInfoTextArea.setVisible(true);
                patientInfoTextArea.appendText(arr.toString());
            }
        }catch (Exception e1){
            patientInfoTextArea.clear();
            patientInfoTextArea.appendText("Please enter a number to search.");
        }
    }
    public void searchLastNameOnAction(ActionEvent e) {
        try{
        patientInfoTextArea.clear();
        patientStore = PatientStore.getPatientStore();
        lastName = searchLastNameTextField.getText();
        clearFields();

        if(patientStore.searchPatientLastName(lastName)==null) {
            patientInfoTextArea.setVisible(true);
            patientInfoTextArea.appendText("Last Name Not In System");
        }else{
            String patientId = patientStore.searchPatientLastName(lastName);
            Patient patient2 = patientStore.display(patientId);
            patientInfoTextArea.clear();
            patientInfoTextArea.setVisible(true);
            patientInfoTextArea.appendText(patient2.toString());
        }
        }catch (Exception e1){
            patientInfoTextArea.clear();
            patientInfoTextArea.appendText("Please enter a last name to search.");
        }
    }
    public void addPatientOnAction(ActionEvent e) throws IOException {
        Parent addPatientRoot = FXMLLoader.load(getClass().getResource("/view/AddPatient2.fxml"));
        Scene addPatientScene = new Scene(addPatientRoot);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(addPatientScene);
        window.show();
    }
    public void patientInfoOnAction(ActionEvent e) throws IOException {
        searchPatientIdButton.setVisible(true);
        searchRoomButton.setVisible(true);
        searchLastNameButton.setVisible(true);
        searchPatientTextField.setVisible(true);
        searchRoomTextField.setVisible(true);
        searchLastNameTextField.setVisible(true);
    }
    public void updatePatientOnAction(ActionEvent e) throws IOException {
        Parent addPatientRoot = FXMLLoader.load(getClass().getResource("/view/PatientUpdater2.fxml"));
        Scene addPatientScene = new Scene(addPatientRoot);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(addPatientScene);
        window.show();
    }
    public void logOutOnAction(ActionEvent e) throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/HealthLogin.fxml"));
        Scene loginScene = new Scene(loginRoot);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    private void clearFields() {
        searchPatientTextField.clear();
        searchLastNameTextField.clear();
        searchRoomTextField.clear();
    }
}
