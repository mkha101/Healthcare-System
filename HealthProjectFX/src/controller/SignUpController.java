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
import model.User;
import model.UserStore;

public class SignUpController {
	@FXML
	public TextField emailTextField;
	@FXML
	public TextField firstNameTextField;
	@FXML
	public TextField lastNameTextField;
	@FXML
	public TextField titleTextField;
	@FXML
	public PasswordField passwordField;
	@FXML
	public PasswordField retypePasswordField;

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String title;
	private String retypePassword;

	private static UserStore userStore;

	public void createNewAccountOnAction(ActionEvent e) {
		userStore = UserStore.getUserStore();
		email = emailTextField.getText();
		firstName = firstNameTextField.getText();
		lastName = lastNameTextField.getText();
		title = titleTextField.getText();
		password = passwordField.getText();
		retypePassword = retypePasswordField.getText();

		if (email.isBlank() || password.isBlank() || retypePassword .isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information Error");
			alert.setHeaderText("Information Missing");
			alert.setContentText(
					"Please make sure both the email field and password fields are filled out completely.");
			alert.showAndWait();
			e.consume();
		} else if (!(password.equals(retypePassword))) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information Error");
			alert.setHeaderText("Password does not match");
			alert.setContentText("Please make sure both passwords match.");
			alert.showAndWait();
			e.consume();
		} else if (userStore.searchEmail(email)) {
			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("Email Error");
			alert1.setHeaderText("Email Availability");
			alert1.setContentText("Email is already in use. Please Try a new email "
					+ "or contact customer service to recover your account");
			alert1.showAndWait();
			System.out.println("Email already exists.. Please try again");
			e.consume();
		} else if (!UserStore.passwordTest(password)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Password Requirements");
			alert.setHeaderText("Requirements not met");
			alert.setContentText("Password does not meet the minimum requirements"
					+ "Password must be at least 6 characters long. "
					+ "Please make sure to include one lowercase letter, " + "one uppercase letter, and one digit.");
			alert.showAndWait();
			e.consume();
		} else {
			userStore.insert(email, new User(email, password, firstName, lastName, title));
			UserStore.saveUser();
			Alert alert2 = new Alert(AlertType.CONFIRMATION);
			alert2.setTitle("User Sign Up Confirmation");
			alert2.setHeaderText("User saved");
			alert2.setContentText("User Info Saved. Proceeding to login page.");
			alert2.showAndWait();
			try {
				Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/HealthLogin.fxml"));
				Scene loginScene = new Scene(loginRoot);
				Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
				window.setScene(loginScene);
				window.show();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void goBackOnAction(ActionEvent e) throws IOException {
		Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/HealthLogin.fxml"));
		Scene loginScene = new Scene(loginRoot);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(loginScene);
		window.show();
	}
}
