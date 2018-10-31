package application.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import application.model.Coordinate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardController {
	private ImageView clickedPiece;
	private boolean aPieceHasBeenClicked;
	private boolean isADoublePaneClick;
	private HashMap<ImageView, Integer> isFirstClickHash;
	private Pane parentOfClickedPiece;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView blackBishopTwo;

	@FXML
	private ImageView whitePawnThree;

	@FXML
	private ImageView whitePawnSeven;

	@FXML
	private ImageView whitePawnEight;

	@FXML
	private ImageView whiteKnightOne;

	@FXML
	private Label whiteName;

	@FXML
	private Label blackName;

	@FXML
	private ImageView blackKnightTwo;

	@FXML
	private ImageView whiteRookOne;

	@FXML
	private ImageView blackRookOne;

	@FXML
	private ImageView blackKing;

	@FXML
	private ImageView blackKnightOne;

	@FXML
	private ImageView whitePawnFour;

	@FXML
	private ImageView blackPawnThree;

	@FXML
	private ImageView whitePawnTwo;

	@FXML
	private ImageView whiteBishopTwo;

	@FXML
	private GridPane boardFX;

	@FXML
	private ImageView whitePawnFive;

	@FXML
	private ImageView whiteRookTwo;

	@FXML
	private ImageView blackPawnOne;

	@FXML
	private ImageView blackPawnFive;

	@FXML
	private ImageView blackPawnSix;

	@FXML
	private ImageView blackPawnEight;

	@FXML
	private ImageView whiteQueen;

	@FXML
	private ImageView blackRookTwo;

	@FXML
	private ImageView whiteKnightTwo;

	@FXML
	private ImageView blackPawnFour;

	@FXML
	private ImageView blackQueen;

	@FXML
	private ImageView blackPawnSeven;

	@FXML
	private ImageView whitePawnOne;

	@FXML
	private ImageView blackPawnTwo;

	@FXML
	private ImageView whitePawnSix;

	@FXML
	private ImageView whiteKing;

	@FXML
	private ImageView blackBishopOne;

	@FXML
	private ImageView whiteBishopOne;

	@FXML
	public void handlePieceClick(MouseEvent event) {
		if (event.getSource() instanceof Pane && parentOfClickedPiece.equals(event.getSource())) {
			aPieceHasBeenClicked = true;
		} else {
			if (aPieceHasBeenClicked) {
				Coordinate a = findCoordinate(boardFX, event);

				try {
					boardFX.getChildren().remove(clickedPiece);
					boardFX.add(clickedPiece, a.getColumnIndex(), a.getRowIndex());
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				clickedPiece = null;
				aPieceHasBeenClicked = false;
			} else {
				if (event.getSource() instanceof ImageView) {
					clickedPiece = (ImageView) event.getSource();
					parentOfClickedPiece = (Pane) clickedPiece.getParent();
					aPieceHasBeenClicked = true;
				}
			}
		}

	}

	private static Coordinate findCoordinate(GridPane boardFX, MouseEvent event) {
		Coordinate a = new Coordinate();
		if (boardFX.getRowIndex((Node) event.getSource()) != null)
			a.setRowIndex(boardFX.getRowIndex((Node) event.getSource()));
		else
			a.setRowIndex(0);
		if (boardFX.getColumnIndex((Node) event.getSource()) != null)
			a.setColumnIndex(boardFX.getColumnIndex((Node) event.getSource()));
		else
			a.setColumnIndex(0);

		return a;
	}

	@FXML
	void initialize() {
		assert blackBishopTwo != null : "fx:id=\"blackBishopTwo\" was not injected: check your FXML file 'Board.fxml'.";
		assert whitePawnThree != null : "fx:id=\"whitePawnThree\" was not injected: check your FXML file 'Board.fxml'.";
		assert whitePawnSeven != null : "fx:id=\"whitePawnSeven\" was not injected: check your FXML file 'Board.fxml'.";
		assert whitePawnEight != null : "fx:id=\"whitePawnEight\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteKnightOne != null : "fx:id=\"whiteKnightOne\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteName != null : "fx:id=\"whiteName\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackName != null : "fx:id=\"blackName\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackKnightTwo != null : "fx:id=\"blackKnightTwo\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteRookOne != null : "fx:id=\"whiteRookOne\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackRookOne != null : "fx:id=\"blackRookOne\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackKing != null : "fx:id=\"blackKing\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackKnightOne != null : "fx:id=\"blackKnightOne\" was not injected: check your FXML file 'Board.fxml'.";
		assert whitePawnFour != null : "fx:id=\"whitePawnFour\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackPawnThree != null : "fx:id=\"blackPawnThree\" was not injected: check your FXML file 'Board.fxml'.";
		assert whitePawnTwo != null : "fx:id=\"whitePawnTwo\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteBishopTwo != null : "fx:id=\"whiteBishopTwo\" was not injected: check your FXML file 'Board.fxml'.";
		assert boardFX != null : "fx:id=\"boardFX\" was not injected: check your FXML file 'Board.fxml'.";
		assert whitePawnFive != null : "fx:id=\"whitePawnFive\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteRookTwo != null : "fx:id=\"whiteRookTwo\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackPawnOne != null : "fx:id=\"blackPawnOne\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackPawnFive != null : "fx:id=\"blackPawnFive\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackPawnSix != null : "fx:id=\"blackPawnSix\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackPawnEight != null : "fx:id=\"blackPawnEight\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteQueen != null : "fx:id=\"whiteQueen\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackRookTwo != null : "fx:id=\"blackRookTwo\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteKnightTwo != null : "fx:id=\"whiteKnightTwo\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackPawnFour != null : "fx:id=\"blackPawnFour\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackQueen != null : "fx:id=\"blackQueen\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackPawnSeven != null : "fx:id=\"blackPawnSeven\" was not injected: check your FXML file 'Board.fxml'.";
		assert whitePawnOne != null : "fx:id=\"whitePawnOne\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackPawnTwo != null : "fx:id=\"blackPawnTwo\" was not injected: check your FXML file 'Board.fxml'.";
		assert whitePawnSix != null : "fx:id=\"whitePawnSix\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteKing != null : "fx:id=\"whiteKing\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackBishopOne != null : "fx:id=\"blackBishopOne\" was not injected: check your FXML file 'Board.fxml'.";
		assert whiteBishopOne != null : "fx:id=\"whiteBishopOne\" was not injected: check your FXML file 'Board.fxml'.";
		aPieceHasBeenClicked = false;
		isADoublePaneClick = false;
		isFirstClickHash = new HashMap<ImageView, Integer>();

	}

}
