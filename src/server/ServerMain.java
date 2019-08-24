package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//FXMLLoader loader = new FXMLLoader(getClass().getResource("C:\\Users\\vanni\\IdeaProjects\\progIII-prova\\src\\server\\server_log.fxml"));
		//loader.setController(new ServerController());
		Parent root = FXMLLoader.load(getClass().getResource("/resources/server_view.fxml" ));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
