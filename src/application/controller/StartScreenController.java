package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StartScreenController {

	@FXML
	TextField playerOne;
	@FXML
	TextField playerTwo;
	@FXML
	Button startButton; 
	@FXML
	AnchorPane rootPane;
	
	public static ArrayList<String> names;

public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void loadNames() {
		names = new ArrayList<String>();
		names.add(playerOne.getText());
		names.add(playerTwo.getText());
		
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Board.fxml"));
			rootPane.getChildren().setAll(pane);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}

