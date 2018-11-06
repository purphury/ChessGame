package application.controller;

import java.util.Iterator;

import application.model.Board;
import application.model.Coordinate;
import application.model.Piece;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardController {
	private ImageView clickedPiece;
	private Coordinate clickedPieceCoordinate;
	private Board boardModel;
	@FXML
	private Label whiteName;

	@FXML
	private Label blackName;

	@FXML
	private GridPane boardFX;
	@FXML
	public void handlePieceClick(MouseEvent event) {
		//TODO: check if a pane has been clicked && if a piece has been clicked, then move the piece//
		if(clickedPiece != null && event.getSource() instanceof Pane) {
			Coordinate c = findCoordinate(boardFX, event);
			Pane clickedPane = (Pane) getNodeByRowColumnIndex(c.getRowIndex(), c.getColumnIndex(), boardFX);
			
			if(boardModel.movePieces(clickedPieceCoordinate, c)) {
				boardFX.getChildren().remove(clickedPiece);
				clickedPane.getChildren().add(clickedPiece);
			
				clickedPiece = null;
				clickedPieceCoordinate = null;
			}
		}
		//TODO: check if a piece has been clicked if a piece has already been clicked//
		else if(clickedPiece != null && event.getSource() instanceof ImageView) {
			
		}
		//TODO: if an image (piece) has been clicked, then set aPieceHasBeenClicked to true//
		else if(event.getSource() instanceof Pane) {
			clickedPieceCoordinate = findCoordinate(boardFX, event);
			Piece clickPiece = boardModel.getPiece(clickedPieceCoordinate, boardModel.turn);
			Pane p = (Pane) event.getSource();
			if(p.getChildren() != null && clickPiece != null) 
				clickedPiece = (ImageView) p.getChildren().get(0);
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
		blackName.setText(StartScreenController.names.get(1));
		whiteName.setText(StartScreenController.names.get(0));
		boardModel = new Board();
		clickedPiece = null;
	}

}
