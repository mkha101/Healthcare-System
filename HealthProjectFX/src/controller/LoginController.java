package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.UserStore;

public class LoginController {
	@FXML
	public TextField emailTextField;
	@FXML
	public PasswordField passwordField;

	private String email;
	private String password;
	private static UserStore userMap;

	public void loginOnAction(ActionEvent e) throws IOException {
		userMap = UserStore.getUserStore();
		if (emailTextField.getText() == null || passwordField.getText() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information Error");
			alert.setHeaderText("Information Missing");
			alert.setContentText("Please make sure both the email field and password field are filled out completely");
			alert.showAndWait();
			e.consume();
		}
		email = emailTextField.getText();
		password = passwordField.getText();
		try {
			if (userMap.searchEmail(email)) {
				if (userMap.searchPassword(email, password)) {
					Parent loginSucessRoot = FXMLLoader.load(getClass().getResource("/view/HealthFunction.fxml"));
					Scene typeScene = new Scene(loginSucessRoot);
					Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
					window.setScene(typeScene);
					window.show();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Information Error");
					alert.setHeaderText("Information Incorrect");
					alert.setContentText("Password you entered is incorrect");
					alert.showAndWait();
					e.consume();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information Error");
				alert.setHeaderText("Email Incorrect");
				alert.setContentText(
						"Email you entered is incorrect." + " If you are a new user please contact your IT Department.");// click the Sign Up button.");
				alert.showAndWait();
				e.consume();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void adminSignUpOnAction(ActionEvent e) throws IOException {
		Parent signUpRoot = FXMLLoader.load(getClass().getResource("/view/HealthSignUp.fxml"));
		Scene signUpScene = new Scene(signUpRoot);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(signUpScene);
		window.show();
	}

}
