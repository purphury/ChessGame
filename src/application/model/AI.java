package application.model;

import java.util.ArrayList;
import java.util.Random;

import application.controller.BoardController;
import application.controller.StartScreenController;
import application.model.Board.Type;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;


public class AI {
	
	public <T> void moveAIThread(BoardController bc) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		final Task<Coordinate[]> t1 = new Task<Coordinate[]>() {

			@Override
			protected Coordinate[] call() throws Exception {
				double value = StartScreenController.value;
				double newMean = value/100*5;
				//System.out.println("Cont value: "+StartScreenController.value+"(pre calc)newMean"
				//			+(StartScreenController.value/100*5));
				
				double randomizer= -1;
				while(randomizer<0 || randomizer > 5) {
					randomizer = (rand.nextGaussian()+newMean);
				//	System.out.println("randomizer: "+randomizer+" new mean: "+newMean);

				}
				
				double roundedRandomizer = Math.round(randomizer);
				//System.out.println("randomizer: "+roundedRandomizer);
				double d = rand.nextDouble();
				boolean setStrategy = setStrategy(d);
				System.out.println("setStrat "+setStrategy+" d: "+d +" RR: "+ roundedRandomizer);
				return bc.getMyAI().getBestMove(BoardController.boardModel, (int)roundedRandomizer, setStrategy, rand);
			}
		};
		
		
		t1.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		    @Override
		    public void handle(WorkerStateEvent t) {
		        Coordinate d[]= t1.getValue();
		        
		        bc.moveAI(d[0], d[1]); 
		    }
		});
		Thread th = new Thread(t1);
		th.setDaemon(true);
		th.start();
	}
	
	private boolean setStrategy(Double d) {
		double dou =StartScreenController.value;
		return d < dou/100+.1;
	}

	public double evaluateBoard(Board board, boolean useStrategy) {
		double strength = 0;
		Piece[][] boardM = board.getBoard();
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(boardM[i][j] != null)
					
					strength += useStrategy ? boardM[i][j].getStrength(i, j) : boardM[i][j].getStrengthWOStrategy(i, j);
			}
		return strength;
	}


	public Coordinate[] getBestMove(Board board, int depth, boolean setStrategy, Random rand) {
		Board newBoard = new Board(board);
		Type turn = newBoard.getTurn();
		double value, max = turn.equals(Type.WHITE) ? -Double.MAX_VALUE : Double.MAX_VALUE; 
		Coordinate move[] = {null, null}; // old, new
		double alpha = -100000;
		double beta = 100000;
		boolean toMaximize = false;
		for(int i=0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				Coordinate coord = new Coordinate(i, j);
				if(newBoard.hasPiece(coord)&& newBoard.getPiece(coord).getType()==turn) {
					ArrayList<Coordinate> availableMoves = newBoard.getMoves(coord);

					for(Coordinate c : availableMoves) {
						newBoard.setPreviousBoard(new Board(newBoard));
						newBoard.movePieces(coord, c);
						//Printer.printNodeLabel(newBoard, c);
						value = minimax(newBoard, depth - 1, alpha, beta, !toMaximize, setStrategy);
						newBoard.undo();

						if(depth == 0 && !setStrategy&& turn.equals(Type.WHITE)) {
							value += 10*rand.nextDouble();
						}
						if(depth == 0 && !setStrategy && turn.equals(Type.BLACK)){
							value -= 10*rand.nextDouble();

						}

						if(turn.equals(Type.WHITE) ? value > max : value < max) {
							move[0] = coord; move[1] = c;
							max = value;

						}

					}					
				}
			}
		}
		return move;
	}

	private double minimax(Board board, int depth, double alpha, double beta, boolean toMaximize, boolean useStrategy){
		if(depth <= 0)
			return this.evaluateBoard(board, useStrategy);
		
		Board newBoard = new Board(board);

		Type turn = newBoard.getTurn();
		double value, max = turn.equals(Type.WHITE) ? -Double.MAX_VALUE : Double.MAX_VALUE; 

		for(int i=0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				Coordinate coord = new Coordinate(i, j);
				if(newBoard.hasPiece(coord)&& newBoard.getPiece(coord).getType()==turn) {
					ArrayList<Coordinate> availableMoves = newBoard.getMoves(coord);

					for(Coordinate c : availableMoves) {
						newBoard.setPreviousBoard(new Board(newBoard));
						newBoard.movePieces(coord, c);
						//Printer.print(newBoard, depth, totalDepth);
						value = minimax(newBoard, depth - 1, alpha, beta, !toMaximize, useStrategy);
						//System.out.println("****Value:  "+value);
						newBoard.undo();
						if(turn.equals(Type.WHITE) ? value > max : value < max) 
							max = value;
						
						
						if(toMaximize) {
							alpha = Math.max(alpha, max);
							if(alpha >= beta) {
								return max;
							}
						}
						
						if(!toMaximize) {
							beta = Math.min(beta, max);
							if(beta <= alpha) {
								return max;
							}
						}
						
						
					}					
				}
			}
		}
		//System.out.println("****MAX VALUE FOR DEPTH ("+depth+"): "+max+" ***");
		return max;
	}
}


