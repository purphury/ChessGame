package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.model.Board;
import application.model.Coordinate;
import application.model.Piece;
import application.model.Board.Type;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardController {
	private ImageView clickedPiece;
	private boolean aPieceHasBeenClicked;
	private Pane parentOfClickedPiece;
	private Board boardModel;
	private Coordinate clickedPieceCoordinate;
	private ArrayList<Coordinate> availableMoves;
	private Type turn; //0 = Type.BLACK, 1 = Type.WHITE//
	@FXML
	private Label whiteName;

	@FXML
	private Label blackName;

	@FXML
	private GridPane boardFX;
/*
	@FXML
	public void handlePieceClick(MouseEvent event) {
		if (event.getSource() instanceof Pane && parentOfClickedPiece != null && parentOfClickedPiece.equals(event.getSource())) {

			aPieceHasBeenClicked = true;
		} else {
			if (aPieceHasBeenClicked) {
				Coordinate a = findCoordinate(boardFX, event);
				//	if(availableMoves.contains(a)){
				try {
					Pane pane = (Pane)getNodeByRowColumnIndex(a.getRowIndex()
							, a.getColumnIndex(), boardFX);
					boardFX.getChildren().remove(clickedPiece);	
					//	if(hasPiece(a)){
					//		pane.getChildren().remove(0);
					pane.getChildren().add(clickedPiece);
				} catch (Exception e) {
				}
				//	boardModel.move(clickedPieceCoordinate, a);
				//	removeDots();
				//	availableMoves = null;
				clickedPieceCoordinate = null;
				clickedPiece = null;
				parentOfClickedPiece = null;
				aPieceHasBeenClicked = false;
				turn = !turn;
				//	}
			} else {
				if (event.getSource() instanceof ImageView) {
					clickedPiece = (ImageView) event.getSource();
					Coordinate c = findCoordinate(boardFX,event);
					Type playerTurn = turn ? Type.WHITE : Type.BLACK;
					if(boardModel.hasPiece(c) && boardModel.getPiece(c, turn).getType() == playerTurn) {
//						availableMoves = boardModel.getMoves(c);
//						addDots();
						clickedPieceCoordinate = findCoordinate(boardFX, event);
						parentOfClickedPiece = (Pane) clickedPiece.getParent();
						aPieceHasBeenClicked = true;
					}
				}
			}
		}

	}
	*/
	@FXML
	public void handlePieceClick(MouseEvent event) {
		//TODO: check if a pane has been clicked && if a piece has been clicked, then move the piece//
		if(clickedPiece != null && event.getSource() instanceof Pane) {
			Coordinate c = findCoordinate(boardFX, event);
			Pane clickedPane = (Pane) getNodeByRowColumnIndex(c.getRowIndex(), c.getColumnIndex(), boardFX);
			boardFX.getChildren().remove(clickedPiece);
			clickedPane.getChildren().add(clickedPiece);
			turn = turn == turn.BLACK ? turn.WHITE : turn.BLACK;
			clickedPiece = null;
			
		}
		//TODO: check if a piece has been clicked if a piece has already been clicked//
		else if(clickedPiece != null && event.getSource() instanceof ImageView) {
			
		}
		//TODO: if an image (piece) has been clicked, then set aPieceHasBeenClicked to true//
		else if(event.getSource() instanceof Pane) {
			System.out.println("here");
			clickedPieceCoordinate = findCoordinate(boardFX, event);
			Piece clickPiece = boardModel.getPiece(clickedPieceCoordinate, turn);
			if(clickPiece != null) {
				Pane p = (Pane) event.getSource();
				clickedPiece = (ImageView) p.getChildren().get(0);
				
			}
		}
	}
	/*
	public void addDots(){
		for(Coordinate c : availableMoves){
			if(!boardModel.hasPiece(c)){
				Circle circle = new Circle(37.0,37.0,10.0);
				Pane pane = (Pane)getNodeByRowColumnIndex(c.getRowIndex()
												, c.getColumnIndex(), boardFX);
				pane.getChildren().add(circle);
			}
		}
	}*/

	/*
	public void removeDots(){

		for(Coordinate c : availableMoves){
			if(!hasPiece(c)){
				Pane pane = (Pane)getNodeByRowColumnIndex(c.getRowIndex()
												, c.getColumnIndex(), boardFX);
				pane.getChildren().remove(0);
			}
		}
	}
	 */

	private static Coordinate findCoordinate(GridPane boardFX, MouseEvent event) {
		Coordinate a = new Coordinate();
		Node source;
		if(((Node)event.getSource()).getParent() instanceof GridPane) 
			source = (Node)event.getSource();
		else 
			source = ((Node)event.getSource()).getParent();

		if (GridPane.getRowIndex(source) != null)
			a.setRowIndex(GridPane.getRowIndex(source));
		else
			a.setRowIndex(0);
		if (GridPane.getColumnIndex(source) != null)
			a.setColumnIndex(GridPane.getColumnIndex(source));
		else
			a.setColumnIndex(0);

		return a;
	}

	public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();
		Iterator<Node> it = childrens.iterator();
		while(it.hasNext()) {
			Node node = it.next();
			int i, j;
			if(GridPane.getRowIndex(node) != null)
				i = GridPane.getRowIndex(node);
			else
				i = 0;
			if(GridPane.getColumnIndex(node) != null)
				j = GridPane.getColumnIndex(node);
			else
				j = 0;
			if(i == row && j == column) {
				result = node;
				break;
			}
		}
		return result;
	}


	@FXML
	void initialize() {
		assert whiteName != null : "fx:id=\"whiteName\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackName != null : "fx:id=\"blackName\" was not injected: check your FXML file 'Board.fxml'.";
		assert boardFX != null : "fx:id=\"boardFX\" was not injected: check your FXML file 'Board.fxml'.";
		//	boardModel = new Board();
		aPieceHasBeenClicked = false;
		blackName.setText(StartScreenController.names.get(1));
		whiteName.setText(StartScreenController.names.get(0));
		turn = Type.WHITE;
		boardModel = new Board();
		clickedPiece = null;
	}

}
