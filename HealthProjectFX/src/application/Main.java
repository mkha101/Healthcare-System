package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.InitializeData;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			InitializeData.initialize();
			GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/view/HealthLogin.fxml"));
			Scene scene = new Scene(root, 700, 550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.sizeToScene();
			primaryStage.setTitle("Management Services");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
