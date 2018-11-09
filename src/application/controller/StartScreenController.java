package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StartScreenController{

	@FXML
	private TextField playerOne;
	@FXML
	private TextField playerTwo;
	@FXML
	private Button startButton; 
	@FXML
	private AnchorPane rootPane;
	
	public static Timer timer;
	public static Thread timeThread;
	public static ArrayList<String> names;

	public void loadNames() {
		names = new ArrayList<String>();
		names.add(playerOne.getText());
		names.add(playerTwo.getText());
		//TODO: what to do in the case of field being empty
		
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Board.fxml"));
			rootPane.getChildren().setAll(pane);
			timer = new Timer();
			timeThread = new Thread(timer);
			timeThread.start();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void onEnter(ActionEvent event) {
		loadNames();
	}

}

