package application.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.Board;
import application.model.Piece;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class BoardController implements Initializable{
	@FXML GridPane board;
	ArrayList<Piece> wPieces;
	ArrayList<Piece> bPieces;
	private Board boards;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
