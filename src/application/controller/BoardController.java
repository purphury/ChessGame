package application.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import application.model.AI;
import application.model.Board;
import application.model.Board.Type;
import application.model.Coordinate;
import application.model.Timer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class BoardController {
	private ImageView selectedPiece;
	private Coordinate clickedPieceCoordinate;
	public static Board boardModel;
	private Coordinate pawnToPromote;
	private Type turn;
	private ArrayList<Coordinate> availableMoves;
	@FXML
	public Label p1Min, p2Min;
	public static Timer timer;
	public static Thread timeThread;

	@FXML
	
	private Label whiteNameLabel, whiteNameLabel2;

	@FXML
	private Label blackNameLabel, blackNameLabel2;

	@FXML
	private Label checkLabel;

	@FXML
	private Label checkmateLabel;

	@FXML
	private Label turnLabel;

	@FXML
	private GridPane boardFX;
	
	@FXML
	private Pane promotionPane;
	
	private AI myAI = new AI();
	
	
	@FXML
	public void handleChoice(ActionEvent event) {
		String pieceChosenStr =((Button) event.getSource()).getText().toLowerCase();
		boardModel.exchangePiece(pieceChosenStr, pawnToPromote, boardModel.getPreviousTurn());
		ImageView view = ((ImageView)getPaneByRowColumnIndex(pawnToPromote.getRowIndex()
				, pawnToPromote.getColumnIndex()).getChildren().get(0));
		String imagePath = "images/";
		if(boardModel.getPreviousTurn() == Type.WHITE) 
			imagePath +="white_";			
		
		if(boardModel.getPreviousTurn()  == Type.BLACK) 
			imagePath +="black_";			
		
		imagePath += pieceChosenStr+".png";
		
		putImage(view, imagePath );
		promotionPane.setVisible(false);
		
		
	}
	
	synchronized Board getBoard() {
		return this.boardModel;
	}

	@FXML
	public void handlePieceClick(MouseEvent event) {
		if(!StartScreenController.isAI || boardModel.getTurn() == Type.WHITE) {
			// ***A piece has not been selected yet***
			if (selectedPiece == null) {
				selectPiece(event);
				//boardModel.display();

			}


			// ***A piece has already been selected***
			else {
				Coordinate c = findCoordinate(event);
				Pane clickedPane = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), c.getColumnIndex());
				int typeOfMove = boardModel.movePieces(clickedPieceCoordinate, c);

				// **Move was not possible**
				if(typeOfMove == 0)
					unselectPiece(c);

				// **Move was possible**
				if (typeOfMove >= 1  ) {

					// Moved to an empty space
					if (clickedPane.getChildren().size() != 0 && clickedPane.getChildren().get(0) 
							instanceof Circle) {
						movePiece(clickedPane);

						//**Move was an En Passant**
						if (typeOfMove == 2)
							processEnPassant(clickedPieceCoordinate, c);

						//Pawn reached opposite side
						if(typeOfMove == 3) {					
							promotePawn(boardModel.getPreviousTurn(), c);
						}

						//moving rook
						if (typeOfMove == 5) {
							Pane p = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), 7);

							if(p.getChildren().get(0) != null) {

								ImageView thing = (ImageView) p.getChildren().get(0);
								p.getChildren().remove(0);
								Pane destination = (Pane) getPaneByRowColumnIndex(c.getRowIndex(),  c.getColumnIndex() - 1);
								destination.getChildren().add(thing);

							}

						}
						if (typeOfMove == 4) {
							Pane p = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), 0);
							if(p.getChildren().get(0) != null) {
								ImageView thing = (ImageView) p.getChildren().get(0);
								p.getChildren().remove(thing);
								Pane destination = (Pane) getPaneByRowColumnIndex(c.getRowIndex(),  c.getColumnIndex() + 1);
								destination.getChildren().add(thing);
							}
						}


					}



					// Moved to enemy space
					else {
						killPiece(clickedPane);
						//Pawn reached opposite side
						if(typeOfMove == 3) {	
							promotePawn(boardModel.getPreviousTurn(), c);
						}
					}


					// Reset variables and see if its check or checkmate
					endOfMoveProcessing(c);
					//Coordinate[] h = myAI.getBestMove(boardModel, StartScreenController.value);
					//System.out.println(h[0].toString() + " " + h[1].toString());
					//boardModel.display();

					if(StartScreenController.isAI)
						myAI.moveAIThread(this);
				}
			}

		}
	}
	public void moveAI(Coordinate a, Coordinate b) {
		if(boardModel.isCheckmate(Type.BLACK))
			return;
		//Coordinate[] h = myAI.getBestMove(boardModel, 4);
		//System.out.println(h[0].toString() + " " + h[1].toString());
		Coordinate c = b;

		clickedPieceCoordinate = a;
		selectPiece(clickedPieceCoordinate);
	
		//System.out.println("xxx");
		Pane clickedPane = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), c.getColumnIndex());
		int typeOfMove = boardModel.movePieces(clickedPieceCoordinate, c);
		//System.out.println("Type of Move: " + typeOfMove);
		// **Move was not possible**
		if(typeOfMove == 0)
			unselectPiece(c);

		
		// **Move was possible**
		if (typeOfMove >= 1  ) {

			// Moved to an empty space
			if (clickedPane.getChildren().size() != 0 && clickedPane.getChildren().get(0) 
					instanceof Circle) {
				movePiece(clickedPane);

				//**Move was an En Passant**
				if (typeOfMove == 2)
					processEnPassant(clickedPieceCoordinate, c);

				//Pawn reached opposite side
				if(typeOfMove == 3) {					
					promotePawn(boardModel.getPreviousTurn(), c);
				}

				//moving rook
				if (typeOfMove == 5) {
					Pane p = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), 7);

					if(p.getChildren().get(0) != null) {

						ImageView thing = (ImageView) p.getChildren().get(0);
						p.getChildren().remove(0);
						Pane destination = (Pane) getPaneByRowColumnIndex(c.getRowIndex(),  c.getColumnIndex() - 1);
						destination.getChildren().add(thing);

					}

				}
				if (typeOfMove == 4) {
					Pane p = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), 0);
					if(p.getChildren().get(0) != null) {
						ImageView thing = (ImageView) p.getChildren().get(0);
						p.getChildren().remove(thing);
						Pane destination = (Pane) getPaneByRowColumnIndex(c.getRowIndex(),  c.getColumnIndex() + 1);
						destination.getChildren().add(thing);
					}
				}


			}



			// Moved to enemy space
			else {
				killPiece(clickedPane);
				//Pawn reached opposite side
				if(typeOfMove == 3) {	
					promotePawn(boardModel.getPreviousTurn(), c);
				}
			}


			// Reset variables and see if its check or checkmate
			endOfMoveProcessing(c);
		}
	}
	public void promotePawn(Type type, Coordinate c) {
		pawnToPromote = c;
		promotionPane.setVisible(true);
		ObservableList<Node> children = promotionPane.getChildren();
		if(children.size() == 8 && type == Type.WHITE) {
			putImage(((ImageView)children.get(4)),"images/white_rook.png" );
			putImage(((ImageView)children.get(5)),"images/white_knight.png" );
			putImage(((ImageView)children.get(6)),"images/white_bishop.png" );
			putImage(((ImageView)children.get(7)),"images/white_queen.png" );
		}
		if(children.size() == 8 && type == Type.BLACK) {
			putImage(((ImageView)children.get(4)),"images/black_rook.png" );
			putImage(((ImageView)children.get(5)),"images/black_knight.png" );
			putImage(((ImageView)children.get(6)),"images/black_bishop.png" );
			putImage(((ImageView)children.get(7)),"images/black_queen.png" );
		}
	}
	
	public void putImage(ImageView view, String imagePath) {
		File file = new File(imagePath);
		Image image = new Image(file.toURI().toString());
		view.setVisible(true);
		view.setImage(image);

	}

	public void selectPiece(MouseEvent event) {
		clickedPieceCoordinate = findCoordinate(event);
		Pane p = (Pane) event.getSource();
		if (p.getChildren().size() != 0 && boardModel.hasPiece(clickedPieceCoordinate)) {
			if (boardModel.getPiece(clickedPieceCoordinate).getType() == boardModel.getTurn()) {
				selectedPiece = (ImageView) p.getChildren().get(0);
				availableMoves = boardModel.getMoves(clickedPieceCoordinate);
				addDots(clickedPieceCoordinate);
			}
		}
	}
	
	public void selectPiece(Coordinate c) {
		clickedPieceCoordinate = c;
		Pane p = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), c.getColumnIndex());
		if (p.getChildren().size() != 0 && boardModel.hasPiece(clickedPieceCoordinate)) {
			if (boardModel.getPiece(clickedPieceCoordinate).getType() == boardModel.getTurn()) {
				selectedPiece = (ImageView) p.getChildren().get(0);
				availableMoves = boardModel.getMoves(clickedPieceCoordinate);
				addDots(clickedPieceCoordinate);
			}
		}
	}

	public void movePiece(Pane clickedPane) {
		boardFX.getChildren().remove(selectedPiece);
		clickedPane.getChildren().add(selectedPiece);
	}

	public void killPiece(Pane clickedPane) {
		ImageView enemyPiece = (ImageView) clickedPane.getChildren().get(0);
		clickedPane.getChildren().remove(enemyPiece);
		boardFX.getChildren().remove(selectedPiece);
		clickedPane.getChildren().add(selectedPiece);
	}

	public void endOfMoveProcessing(Coordinate c) {
		turnLabelAppearance();
		testForCheckAndCheckmate(boardModel.getPiece(c).otherType());
		unselectPiece(c);
	}

	public void unselectPiece(Coordinate c) {
		removeDots(c);
		availableMoves = null;
		selectedPiece = null;
		clickedPieceCoordinate = null;
	}

	public void testForCheckAndCheckmate(Type type) {

		if (boardModel.isCheck(type)) {
			if(type == Type.WHITE) {
				boardModel.whiteEverChecked = true;
			}
			else if(type == Type.BLACK) {
				boardModel.blackEverChecked = true;
			}
			checkLabel.setVisible(true);
		}
		else
			checkLabel.setVisible(false);

		if (boardModel.isCheckmate(type)) {
			checkLabel.setVisible(false);
			checkmateLabel.setVisible(true);
		}
	}

	public void processEnPassant(Coordinate fromPosition, Coordinate toPosition) {
		Pane p = (Pane) getPaneByRowColumnIndex(fromPosition.getRowIndex(), toPosition.getColumnIndex());
		if (p.getChildren().get(0) != null) {
			ImageView killedPawn = (ImageView) p.getChildren().get(0);
			p.getChildren().remove(killedPawn);
		}
	}

	public void addDots(Coordinate b) {
		Pane pane2 = (Pane) getPaneByRowColumnIndex(b.getRowIndex(), b.getColumnIndex());
		pane2.setStyle("-fx-background-color: #F9A602;");
		for (Coordinate c : availableMoves) {
			Pane pane = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), c.getColumnIndex());
			if (!boardModel.hasPiece(c)) {
				Circle circle = new Circle(37.0, 37.0, 10.0);
				pane.getChildren().add(circle);
			} else {
				pane.setStyle("-fx-background-color: #FF0000;");
			}

		}
	}

	public void removeDots(Coordinate d) {
		changeToOriginalColor(clickedPieceCoordinate);
		for (Coordinate c : availableMoves) {
			Pane pane = (Pane) getPaneByRowColumnIndex(c.getRowIndex(), c.getColumnIndex());
			if (!boardModel.hasPiece(c) || c.equals(d)) {
				changeToOriginalColor(c);
				if (pane.getChildren().get(0) instanceof Circle)
					pane.getChildren().remove(0);
			} else {
				if(pane.getChildren().size()>1) {
					if(pane.getChildren().get(0) instanceof Circle)
						pane.getChildren().remove(0);
				}
				changeToOriginalColor(c);
			}
		}
	}

	public void changeToOriginalColor(Coordinate a) {
		Pane pane = (Pane) getPaneByRowColumnIndex(a.getRowIndex(), a.getColumnIndex());

		if (((a.getColumnIndex() + a.getRowIndex()) % 2) == 1)
			pane.setStyle("-fx-background-color:  #595756;");
		else
			pane.setStyle("-fx-background-color:  white;");
	}

	private static Coordinate findCoordinate(MouseEvent event) {
		Coordinate a = new Coordinate();
		Node source;
		if (((Node) event.getSource()).getParent() instanceof GridPane)
			source = (Node) event.getSource();
		else
			source = ((Node) event.getSource()).getParent();

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

	public Pane getPaneByRowColumnIndex(final int row, final int column) {
		Node result = null;
		ObservableList<Node> childrens = boardFX.getChildren();
		Iterator<Node> it = childrens.iterator();
		while (it.hasNext()) {
			Node node = it.next();
			int i, j;
			if (GridPane.getRowIndex(node) != null)
				i = GridPane.getRowIndex(node);
			else
				i = 0;
			if (GridPane.getColumnIndex(node) != null)
				j = GridPane.getColumnIndex(node);
			else
				j = 0;
			if (i == row && j == column) {
				result = node;
				break;
			}
		}
		return (Pane) result;
	}

	// this will change the turn label

	public void turnLabelAppearance() {
		Type turn = boardModel.getTurn();
		int whiteName = whiteNameLabel.getText().length();
		int blackName = blackNameLabel.getText().length();
		if (turn.equals(Type.WHITE)) {
			turnLabel.setText(whiteNameLabel.getText() + (whiteNameLabel.getText().charAt(whiteName-1) == 's' ? "' turn":  "'s turn"));
		} else if (turn.equals(Type.BLACK)) {
			turnLabel.setText(blackNameLabel.getText() + (blackNameLabel.getText().charAt(blackName-1) == 's' ? "' turn":  "'s turn"));
		}
	}
	
	public void setTime() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				long currentPlayer;
				do {
					currentPlayer = timer.getCurrentPlayerTimeInMilliseconds();
					if (timer.getCount() >= 1000) {
						timer.setCount(0);
						int seconds = (int) ((currentPlayer/1000) % 60);
						int minutes = (int) currentPlayer/60000;
						if(boardModel.getTurn() == Type.WHITE) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									p1Min.setText("0" + String.valueOf(minutes));
//									p1Sec.setText(seconds < 10 ? "0" + String.valueOf(seconds) : "" + String.valueOf(seconds));								
								}

							});
						} else {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									p2Min.setText("0" + String.valueOf(minutes));
		//							p2Sec.setText(seconds < 10 ? "0" + String.valueOf(seconds) : "" + String.valueOf(seconds));								
								}

							});
						}
					}
				} while(currentPlayer > 0);
			}
		});
		
		
		th.setDaemon(true);
		th.start();
		
	}
	
	public <T> void diffTimer2() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Task<T> t1 = new Task<T>() {

			@Override
			protected T call() throws Exception {
				int player1Time = 300;

				long startTime = System.currentTimeMillis();
				while(player1Time > 0) {
					if(getBoard().getTurn() == Type.WHITE) {
						//final int p1FTime = player1Time;
						updateMessage(player1Time/60+":"+String.format("%02d", (player1Time%60)));						
						player1Time--;						
					}
					long elapsedTime = System.currentTimeMillis()- startTime;
					Thread.sleep(1000-(elapsedTime%1000)+5);
				}				
				return null;
			}
			
		};
		p1Min.textProperty().bind(t1.messageProperty());
		
		Task<T> t2 = new Task<T>() {

			@Override
			protected T call() throws Exception {
				int player2Time = 300;
				long startTime = System.currentTimeMillis();
				while(player2Time > 0) {
					if(getBoard().getTurn() == Type.BLACK || player2Time ==300) {
						updateMessage(player2Time/60+":"+String.format("%02d", (player2Time%60)));
						player2Time--;
					}
					long elapsedTime = System.currentTimeMillis()- startTime;
					Thread.sleep(1000-(elapsedTime%1000)+5);
				}				
				return null;
			}
			
		};
		p2Min.textProperty().bind(t2.messageProperty());

		Thread thread1 = new Thread(t1);
		Thread thread2 = new Thread(t2);

		thread1.setDaemon(true);
		thread2.setDaemon(true);
		thread1.start();
		thread2.start();

	}
	
	public AI getMyAI() {
		return myAI;
	}

	public void setMyAI(AI myAI) {
		this.myAI = myAI;
	}

	@FXML
	void initialize() {
		turn = Type.WHITE;
		boolean timerCheck = true;
		assert whiteNameLabel != null : "fx:id=\"whiteName\" was not injected: check your FXML file 'Board.fxml'.";
		assert blackNameLabel != null : "fx:id=\"blackName\" was not injected: check your FXML file 'Board.fxml'.";
		assert turnLabel != null : "fx:id=\"turnName\" was not injected: check your FXML file 'Board.fxml'.";
		assert boardFX != null : "fx:id=\"boardFX\" was not injected: check your FXML file 'Board.fxml'.";
		String blackNameString = StartScreenController.names.get(1),
				whiteNameString = StartScreenController.names.get(0);
		this.blackNameLabel.setText(blackNameString);
		this.whiteNameLabel.setText(whiteNameString);
		this.blackNameLabel2.setText(blackNameString);
		this.whiteNameLabel2.setText(whiteNameString);
		String whiteTurn = whiteNameString + "'" + (whiteNameString.charAt(whiteNameString.length()-1) == 's' ? "" : "s") + " turn";
		turnLabel.setText(whiteTurn);
		checkLabel.setVisible(false);
		checkmateLabel.setVisible(false);
		promotionPane.setVisible(false);
		boardModel = new Board(whiteNameString, blackNameString);
		selectedPiece = null;
		availableMoves = new ArrayList<Coordinate>();
		if(!timerCheck) {
		 timer = new Timer(); timeThread = new Thread(timer); 
		 timeThread.setDaemon(true); timeThread.start();
		 setTime();
		}
		else {
			diffTimer2();
		}
	}

}