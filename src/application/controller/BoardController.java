package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.model.Bishop;
import application.model.Board;
import application.model.King;
import application.model.Knight;
import application.model.Pawn;
import application.model.Piece;
import application.model.Queen;
import application.model.Rook;
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
		boards= new Board();
		System.out.println(boards.board[6][0].getType());
	}

}
