package application.controller;

import java.io.IOException;
import java.net.URL;
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

public void initialize(URL location, ResourceBundle resources) {
		
	}

	@SuppressWarnings("null")
	//this still doesnt work right
	// idk if i need the board.FXML to be setup first or its just beacause the method is wrong, will look at it tommorow
	public String[] loadNames() {
		String[] names = null;
		if (playerOne.getText() == null){
			names[0] = playerOne.getPromptText();
		}
		
		if (playerTwo.getText() == null) {
			names[1] = playerTwo.getPromptText();
		}
		else {
			names[0] = playerOne.getText();
			names[1] = playerTwo.getText();
		}
		
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../../Board.fxml"));
			rootPane.getChildren().setAll(pane);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		return names;
		
	}

}

