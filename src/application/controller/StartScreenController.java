package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StartScreenController implements Initializable{
	public static boolean isAI = false;
	public static Integer value;
	@FXML private TextField playerOne;
	@FXML private TextField playerTwo;
	@FXML private Button startButton; 
	@FXML private AnchorPane rootPane;
	@FXML Label easyL, medL, hardL;
	@FXML Slider difficultySlider;
	@FXML TextField sliderText;
	
	public static ArrayList<String> names;

	public void loadNames() {
		names = new ArrayList<String>();
		names.add(playerOne.getText());
		names.add(playerTwo.getText());
		//TODO: what to do in the case of field being empty
		
		try {
			if(isAI)
				value = (int) difficultySlider.getValue();
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
			easyL.setVisible(false);
			medL.setVisible(false);
			hardL.setVisible(false);
			difficultySlider.setVisible(false);
			sliderText.setVisible(false);
		} else {
			isAI = true;
			playerTwo.setText("Computer");
			playerTwo.setEditable(false);
			easyL.setVisible(true);
			medL.setVisible(true);
			hardL.setVisible(true);
			difficultySlider.setVisible(true);
			sliderText.setVisible(true);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		isAI = false;
		easyL.setVisible(false);
		medL.setVisible(false);
		hardL.setVisible(false);
		difficultySlider.setVisible(false);
		sliderText.setVisible(false);
		sliderText.setText("1");
		difficultySlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				
				sliderText.setText(String.format("%2.0f",Math.floor((double)newValue)));
				
			}			
		});
		
		
	}
}

