package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StartScreenController {
	public static boolean isAI = false;
	@FXML private TextField playerOne;
	@FXML private TextField playerTwo;
	@FXML private Button startButton; 
	@FXML private AnchorPane rootPane;
	public static ArrayList<String> names;

	public void loadNames() {
		names = new ArrayList<String>();
		names.add(playerOne.getText());
		names.add(playerTwo.getText());
		//TODO: what to do in the case of field being empty
		
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Board.fxml"));
			rootPane.getChildren().setAll(pane);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void onEnter(ActionEvent event) {
		loadNames();
	}
	public void setAI(ActionEvent event) {
		if(isAI) {
			isAI = false;
			playerTwo.setText("Black");
			playerTwo.setEditable(true);
		} else {
			isAI = true;
			playerTwo.setText("Computer");
			playerTwo.setEditable(false);
		}
	}
}

