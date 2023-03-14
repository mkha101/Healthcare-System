package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HealthFunctionController {
    @FXML
    public TextField searchPatientTextField;
    @FXML
    public TextField searchRoomTextField;

    private String patientId;
    private String roomNumber;

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
