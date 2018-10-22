package application.view;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Board.fxml"));
			primaryStage.setScene(new Scene(root, 500, 600));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
