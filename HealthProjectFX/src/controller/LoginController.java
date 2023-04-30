package controller;

import java.io.IOException;

import javafx.application.Platform;
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
	public TextField usernameTextField;
	@FXML
	public PasswordField passwordField;
	private String username;
	private String password;
	private static UserStore userMap;

	public void loginOnAction(ActionEvent e) throws IOException {
		userMap = UserStore.getUserStore();
		username = usernameTextField.getText();
		password = passwordField.getText();
		if (username.isBlank()|| password.isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information Error");
			alert.setHeaderText("Information Missing");
			alert.setContentText("Please make sure both the username field and password field are filled out completely");
			alert.showAndWait();
			e.consume();
		}
		try {
			if (userMap.searchUsername(username)) {
				if (userMap.searchPassword(username, password)) {
					Parent loginSuccessRoot = FXMLLoader.load(getClass().getResource("/view/HealthFunction2.fxml"));
					Scene typeScene = new Scene(loginSuccessRoot);
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
				alert.setHeaderText("Username Incorrect");
				alert.setContentText(
						"Username you entered is incorrect." + " If you are a new user please contact your IT Department.");
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
	public void exitOnAction(ActionEvent e) throws IOException {
		Platform.exit();
	}

}
