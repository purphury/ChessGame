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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * This is the controller for the board.fxml
 * 
 * @author Chris Crabtree, Daniel Nix
 *	UTSA Application Programming CS3443 Fall 2018
 */
public class StartScreenController implements Initializable{
	public static boolean isAI = false;
	public static Integer value;
	public static Integer minutes;
	public static boolean giveFeedback;
	public static boolean haveTimer;
	@FXML private TextField playerOne;
	@FXML private TextField playerTwo, timerTextField;
	@FXML private Button startButton; 
	@FXML private AnchorPane rootPane;
	@FXML private Label easyL, medL, hardL, timerLabel, errorLabel;
	@FXML private Slider difficultySlider;
	@FXML private TextField sliderText;
	@FXML private CheckBox fbCheckBox, compCheckBox;
	
	public static ArrayList<String> names;

	/**
	 * Processes the check of the feedback box
	 * 
	 * @param event
	 */
	@FXML
	public void feedbackCheck(ActionEvent event){
		if(!fbCheckBox.isSelected()) {
			fbCheckBox.setSelected(false);
			giveFeedback = false;
		}
		else {
			compCheckBox.setSelected(false);
			timerTextField.setVisible(false);
			timerLabel.setVisible(false);
			giveFeedback = true;
			haveTimer = false;
			minutes = null;
			errorLabel.setVisible(false);
		}
	}
	
	/**
	 * Processes the check of the competitive box
	 * 
	 * @param event
	 */
	@FXML
	public void competitionCheck(ActionEvent event){
		if(!compCheckBox.isSelected()) {
			haveTimer = false;
			timerTextField.setVisible(false);
			timerLabel.setVisible(false);
			minutes = null;
			errorLabel.setVisible(false);
		}
		else {
			fbCheckBox.setSelected(false);
			timerTextField.setVisible(true);
			timerLabel.setVisible(true);
			haveTimer = true;

		}
	}
	
	/**
	 * Preps info for board controller and
	 * loads board.fxml
	 */
	public void loadNames() {
		names = new ArrayList<String>();
		names.add(playerOne.getText());
		names.add(playerTwo.getText());
		//TODO: what to do in the case of field being empty
		int min = 0;
		if(haveTimer) {
			try {
				min = Integer.parseInt(timerTextField.getText());
			}catch(Exception e) {
				errorLabel.setVisible(true);
				return;
			}
			if(min< 1 || min > 60) {
				errorLabel.setVisible(true);
				return;
			}		
			minutes = min; 
		}
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
	
	/**
	 * Calls method to process game start
	 * 
	 * @param event
	 */
	public void onEnter(ActionEvent event) {
		loadNames();
	}
	
	/**
	 * Makes AI options visible
	 * 
	 * @param event
	 */
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
			playerTwo.setText("Johnny 5");
			playerTwo.setEditable(false);
			easyL.setVisible(true);
			medL.setVisible(true);
			hardL.setVisible(true);
			difficultySlider.setVisible(true);
			sliderText.setVisible(true);
		}
	}

	/**
	 * Initializes the StartScreenController and sets up a listener 
	 * for the AI dificulty slider
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		isAI = false;
		this.value= -1;
		haveTimer= false;
		giveFeedback = false;
		minutes = null;
		easyL.setVisible(false);
		medL.setVisible(false);
		hardL.setVisible(false);
		errorLabel.setVisible(false);
		timerTextField.setVisible(false);
		timerLabel.setVisible(false);
		difficultySlider.setVisible(false);
		sliderText.setVisible(false);
		sliderText.setText("1");
		difficultySlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if((double)newValue < 20) {
					playerTwo.setText("Johnny 5");
				}
				else if((double)newValue < 40) {
					playerTwo.setText("Wall-E");
				}
				else if((double)newValue < 60) {
					playerTwo.setText("R2D2");
				}
				else if((double)newValue < 80) {
					playerTwo.setText("Hal");
				}
				else{
					playerTwo.setText("Borg Collective");
				}
				sliderText.setText(String.format("%2.0f",Math.floor((double)newValue)));
				
			}			
		});
		
		
	}
}

