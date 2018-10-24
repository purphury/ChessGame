package application.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StartScreenController {

	TextField playerOne;
	TextField playerTwo;
	Button startButton; 
	AnchorPane rootPane;

	@SuppressWarnings("null")
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

