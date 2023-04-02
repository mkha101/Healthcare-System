package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patient;
import model.PatientStore;

import javax.swing.*;

public class HealthFunctionController {
    @FXML
    public TextField searchPatientTextField;
    @FXML
    public TextField searchRoomTextField;

    private String patientId;
    private int roomNumber;
    private static PatientStore patientStore;
    public TextArea resultsTextArea;

    public void searchPatientOnAction(ActionEvent e) throws IOException {
        patientStore = PatientStore.getPatientStore();
        patientId = searchPatientTextField.getText();
        if(patientStore.searchPatientId(patientId)) {
            resultsTextArea.clear();
           Patient patient = patientStore.display(patientId);
            resultsTextArea.setVisible(true);
            resultsTextArea.appendText(patient.toString());
        }else{
            resultsTextArea.setVisible(true);
            resultsTextArea.clear();
            resultsTextArea.appendText("patientId not found");
            System.out.println("Id not found");
        };
    }
    public void searchRoomOnAction(ActionEvent e) throws IOException{
        roomNumber = Integer.valueOf(searchRoomTextField.getText());
        System.out.println(roomNumber);
        if(patientStore.searchPatientRoom(roomNumber)==null) {
            resultsTextArea.clear();
            resultsTextArea.setVisible(true);
            resultsTextArea.appendText("roomNumber not in use");
        }else{
            ArrayList<Patient> arr = (ArrayList<Patient>) patientStore.searchPatientRoom(roomNumber);
            resultsTextArea.clear();
            resultsTextArea.setVisible(true);
            resultsTextArea.appendText(arr.toString());

           // System.out.println("room not found");
        };
    }
    public void addPatientOnAction(ActionEvent e) throws IOException {
        Parent addPatientRoot = FXMLLoader.load(getClass().getResource("/view/AddPatient.fxml"));
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
}
