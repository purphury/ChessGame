package application.controller;

import java.util.ArrayList;
import java.util.Iterator;

import application.model.Board;
import application.model.Board.Type;
import application.model.Coordinate;
import application.model.Piece;
import application.model.Timer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class BoardController {
	private ImageView allyPiece;
	private Coordinate clickedPieceCoordinate;
	public static Board boardModel;
	private ArrayList<Coordinate> availableMoves;
	@FXML public static Label p1Min, p1Sec, p2Min, p2Sec;
	public static Timer timer;
	public static Thread timeThread;
	
	@FXML
	private Label whiteNameLabel;

	@FXML
	private Label blackNameLabel;
	
	@FXML
	private Label checkLabel;
	
	@FXML
	private Label checkmateLabel;
	
	@FXML
	private Label turnLabel;

	@FXML
	private GridPane boardFX;
	@FXML
	public void handlePieceClick(MouseEvent event) {
		//check if a pane has been clicked && if a piece has been clicked, then move the piece//
		if(allyPiece != null && event.getSource() instanceof Pane) {
			Coordinate c = findCoordinate(boardFX, event);
			Pane clickedPane = (Pane) getNodeByRowColumnIndex(c.getRowIndex(), c.getColumnIndex(), boardFX);
			//Make sure that the area clicked does not have a piece on it because there is a circle//
			if(clickedPane.getChildren().size() != 0 
					&& clickedPane.getChildren().get(0) instanceof Circle ) {
				//Check if the piece can be moved at all//
				int x = boardModel.movePieces(clickedPieceCoordinate, c);
				if(x>0) {
					removeDots(c);
					turnLabelAppearance();
					availableMoves = null;
					boardFX.getChildren().remove(allyPiece);
					clickedPane.getChildren().add(allyPiece);
					
					if(x==2) {
						if(boardModel.getPiece(c).getType() == Type.WHITE) {
							Pane p = (Pane) getNodeByRowColumnIndex(c.getRowIndex()+1, c.getColumnIndex(), boardFX);
							
							if(p.getChildren().get(0) != null) {
								ImageView thing = (ImageView) p.getChildren().get(0);
								p.getChildren().remove(thing);
							}
							
						}
						if(boardModel.getPiece(c).getType() == Type.BLACK) {
							Pane p = (Pane) getNodeByRowColumnIndex(c.getRowIndex()-1, c.getColumnIndex(), boardFX);
							
							if(p.getChildren().get(0) != null) {
								ImageView thing = (ImageView) p.getChildren().get(0);
								p.getChildren().remove(thing);
							}
							
						}
							
					}
					
					allyPiece = null;
					clickedPieceCoordinate = null;
					if(boardModel.isCheck(boardModel.getPiece(c).otherType()))
						checkLabel.setVisible(true);
					else
						checkLabel.setVisible(false);
					
					if(boardModel.isCheckmate(boardModel.getPiece(c).otherType())) {
						checkLabel.setVisible(false);
						checkmateLabel.setVisible(true);
					}
					
				}
			}
			//if there is a piece on the pane that was clicked, kill the piece and move there//
			else if(!clickedPieceCoordinate.equals(c)) {
				//Check if the piece can actually be moved to the location clicked//
				if(boardModel.movePieces(clickedPieceCoordinate, c) > 0) {
					removeDots(c);
					turnLabelAppearance();
					ImageView enemyPiece = (ImageView) clickedPane.getChildren().get(0);
					clickedPane.getChildren().remove(enemyPiece);
					boardFX.getChildren().remove(allyPiece);
					clickedPane.getChildren().add(allyPiece);

					availableMoves = null;
					allyPiece = null;
					clickedPieceCoordinate = null;
					if(boardModel.isCheck(boardModel.getPiece(c).otherType()))
						checkLabel.setVisible(true);
					else
						checkLabel.setVisible(false);
					
					if(boardModel.isCheckmate(boardModel.getPiece(c).otherType())) {
						checkLabel.setVisible(false);
						checkmateLabel.setVisible(true);
					}
				}
			}
			//if the pane is clicked again, the piece is unselected//
			else if(clickedPieceCoordinate.equals(c)) {
				removeDots(c);
				availableMoves = null;
				allyPiece = null;
				clickedPieceCoordinate = null;
			}
		}
		//if an image (piece) has been clicked, then clickedPiece will not be null//
		else if(event.getSource() instanceof Pane&& allyPiece == null) {
			System.out.println(boardModel.getTurn());
			clickedPieceCoordinate = findCoordinate(boardFX, event);
			Piece clickPiece = boardModel.getPiece(clickedPieceCoordinate, boardModel.getTurn());
			Pane p = (Pane) event.getSource();
			if(p.getChildren() != null && clickPiece != null) {
				allyPiece = (ImageView) p.getChildren().get(0);
				availableMoves = boardModel.getMoves(clickedPieceCoordinate);
				addDots(clickedPieceCoordinate);
				System.out.println("Coords: " + boardModel.getMoves(clickedPieceCoordinate));
			}
		}
	}
	
	public void addDots(Coordinate b){
		Pane pane2 = (Pane)getNodeByRowColumnIndex(b.getRowIndex()
				, b.getColumnIndex(), boardFX);
		pane2.setStyle("-fx-background-color: #F9A602;");		
		for(Coordinate c : availableMoves){
			Pane pane = (Pane)getNodeByRowColumnIndex(c.getRowIndex()
					, c.getColumnIndex(), boardFX);
			if(!boardModel.hasPiece(c)){
				Circle circle = new Circle(37.0,37.0,10.0);
				pane.getChildren().add(circle);
			}
			else {
				pane.setStyle("-fx-background-color: #FF0000;");
			}

		}
	}

	
	public void removeDots(Coordinate d){
		changeToOriginalColor((Pane) allyPiece.getParent());
		for(Coordinate c : availableMoves){
			Pane pane = (Pane)getNodeByRowColumnIndex(c.getRowIndex()
					, c.getColumnIndex(), boardFX);			
			if(!boardModel.hasPiece(c) || c.equals(d)){
				changeToOriginalColor(pane);			
				if(pane.getChildren().get(0) instanceof Circle)
					pane.getChildren().remove(0);				
			}
			else
				changeToOriginalColor(pane);
		}
	}
	
	public void changeToOriginalColor(Pane pane) {
		Coordinate a = findCoordinateWithPane(boardFX, pane);

		if(((a.getColumnIndex()+a.getRowIndex()) % 2) == 1)
			pane.setStyle("-fx-background-color:  #595756;");
		else
			pane.setStyle("-fx-background-color:  white;");
	}
	 
	private static Coordinate findCoordinateWithPane(GridPane boardFX, Pane pane) {
		Coordinate a = new Coordinate();
		Node source = pane;
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
	
	//this will change the turn label
	

	public void turnLabelAppearance() {
		Type turn = boardModel.getTurn();
		if (turn.equals(Type.WHITE)) {
			turnLabel.setText(whiteNameLabel.getText() + "'s turn");
		}
		else if (turn.equals(Type.BLACK)) {
			turnLabel.setText(blackNameLabel.getText() + "'s turn");
		}	
	}
	
	public static void setTime(long player, Type turn) {
		//TODO: needs implementation!
		//Set time for playerOne//
		Thread th = new Thread(new Task() {
			@Override
			protected String call() throws Exception {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						int seconds = (int) ((player/1000) % 60);
						int minutes = (int) player/60000;
						if(turn == Type.WHITE) {
							//System.out.println("PlayerOne: " + minutes + ":" + seconds);
							System.out.println(p1Min.getText());
							//p1Min.setText("0" + String.valueOf(minutes));
							//p1Sec.setText(seconds < 10 ? "0" : "" + String.valueOf(seconds));
						}
						if(turn == Type.BLACK) {
							System.out.println("PlayerTwo: " + minutes + ":" + seconds);
							//p2Min.setText("0" + String.valueOf(minutes));
							//p2Sec.setText(seconds < 10 ? "0" : "" + String.valueOf(seconds));
						}
					}
				});
				return null;
			}
		});
		th.start();
	}
	
	@FXML
	void initialize() {
		assert whiteNameLabel != null : "fx:id=\"whiteName\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackNameLabel != null : "fx:id=\"blackName\" was not injected: check your FXML file 'Board.fxml'.";
		assert turnLabel != null : "fx:id=\"turnName\" was not injected: check your FXML file 'Board.fxml'.";
		assert boardFX != null : "fx:id=\"boardFX\" was not injected: check your FXML file 'Board.fxml'.";
		String blackNameString = StartScreenController.names.get(1), whiteNameString = StartScreenController.names.get(0);
		this.blackNameLabel.setText(blackNameString);
		this.whiteNameLabel.setText(whiteNameString);
		String whiteTurn = whiteNameString + "'s turn";
		turnLabel.setText(whiteTurn);
		checkLabel.setVisible(false);
		checkmateLabel.setVisible(false);
		boardModel = new Board(whiteNameString, blackNameString);
		allyPiece = null;
		availableMoves = new ArrayList<Coordinate>();
		/*
		timer = new Timer();
		timeThread = new Thread(timer);
		timeThread.start();
		*/
	}

}
