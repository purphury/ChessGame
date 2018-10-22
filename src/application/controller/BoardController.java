package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class BoardController implements Initializable{
	@FXML GridPane board;
	ArrayList<Piece> wPieces;
	ArrayList<Piece> bPieces;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i = 0; i < 8; i++) {
			wPieces.add(new Pawn(6, i, false));
			bPieces.add(new Pawn(1, i, true));
		}
		bPieces.add(new Rook(0, 0, true ));
		bPieces.add(new Rook(0, 7, true ));
		wPieces.add(new Rook(7, 0, false));
		wPieces.add(new Rook(7, 7, false));
		
		bPieces.add(new Knight(0, 1, true ));
		bPieces.add(new Knight(0, 7, true ));
		wPieces.add(new Knight(7, 1, false));
		wPieces.add(new Knight(7, 7, false));
		
		bPieces.add(new Bishop(0, 2, true ));
		bPieces.add(new Bishop(0, 5, true ));
		wPieces.add(new Bishop(7, 2, false));
		wPieces.add(new Bishop(7, 5, false));
		
		bPieces.add(new Queen(0, 3, true ));
		wPieces.add(new Queen(7, 3, false));
		
		bPieces.add(new King(0, 4, true ));
		wPieces.add(new King(7, 4, false));
	}

}
