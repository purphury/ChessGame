package application.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

import application.controller.BoardController;
import application.controller.StartScreenController;
import application.model.Board.Type;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;


public class AI {
	
	public <T> void feedbackThread(BoardController bc, Coordinate oldLoc, Coordinate newLoc) {
		Task t1 = new Task() {

			@Override
			protected String call() throws Exception {
				String str = getFeedback(BoardController.boardModel.getPreviousBoard(), true, oldLoc, newLoc);
				return str;
				
			}
			
		};
		t1.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		    @Override
		    public void handle(WorkerStateEvent t) {
		        bc.feedbackLabel.setText((String) t1.getValue());
		        
		    }
		});
		
		Thread th1 = new Thread(t1);
		th1.setDaemon(true);
		th1.start();
	}
	
	public String getFeedback(Board board, boolean setStrategy, Coordinate oldLoc, Coordinate newLoc) {
		int depth = 3;
		ArrayList<MoveValue> listOfMoves = new ArrayList<MoveValue>(); 

		Board newBoard = new Board(board);
		Type turn = newBoard.getTurn();
		Double value, max = turn.equals(Type.WHITE) ? -Double.MAX_VALUE : Double.MAX_VALUE; 

		double alpha = -100000;
		double beta = 100000;
		boolean toMaximize = board.getTurn() == Type.WHITE;
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
						Coordinate move[] = {null,null}; // old, new
						move[0] = coord; 
						move[1] = c;
						listOfMoves.add(new MoveValue(coord, c, value));
						

					}					
				}
			}
		}
		//System.out.println();
		ArrayList<Double> al = new ArrayList<Double>();

		listOfMoves.sort(new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				if(board.getTurn() == Type.WHITE) {
					return Double.compare(((MoveValue)o2).getValue(), ((MoveValue)o1).getValue());
				}
				else {
					return Double.compare(((MoveValue)o1).getValue(), ((MoveValue)o2).getValue());

				}
			}
			
		});

		//System.out.println("OL: "+oldLoc+" NL: "+newLoc);
		int fbValue=0;
		for(MoveValue v: listOfMoves) {
			if(v.getCoordinateFrom().equals(oldLoc)&&v.getCoordinateTo().equals(newLoc))
				fbValue=listOfMoves.indexOf(v);
		}
		String strFbValue=String.valueOf(fbValue+1);
		
		if(fbValue+1 == 1 )
			strFbValue="best";
		else if((fbValue+1)%10 == 1 && (fbValue+1) != 11)
			strFbValue+="st best";
		else if((fbValue+1) == 2|| ((fbValue+1)%10 == 2 && (fbValue+1) != 12))
			strFbValue +="nd best";
		else if((fbValue+1) == 3|| ((fbValue+1)%10 == 3 && (fbValue+1) != 13))
			strFbValue +="rd best";
		else 
			strFbValue +="th best";
		
		//System.out.println("fbValue: "+fbValue+" strFBV: "+strFbValue);
		String str=convertMoveToString(board, oldLoc, newLoc)+
				"\nC3PO:\n  By my calculations that was the "+
						strFbValue+" move of "+String.valueOf(listOfMoves.size());
		return str;
	}
	
	private String convertMoveToString(Board board, Coordinate oldLoc, Coordinate newLoc) {
		String ret= "";
		if(board.getTurn() == Type.WHITE)
			ret+="White ";
		else
			ret+="Black ";
		
		if(board.hasPiece(oldLoc))
			ret+= board.getPiece(oldLoc).toString()+" ";
		
		ret += convertRow(oldLoc.getRowIndex());
		ret += String.valueOf(oldLoc.getColumnIndex()+1)+" to ";
		ret += convertRow(newLoc.getRowIndex());
		ret += String.valueOf(newLoc.getColumnIndex()+1);
		return ret;
	}
	
	private String convertRow(int row) {
		String ret = "";
		if(row == 0)
			ret+="A";
		else if(row == 1)
			ret+="B";
		else if(row == 2)
			ret+="C";
		else if(row == 3)
			ret+="D";
		else if(row == 4)
			ret+="E";
		else if(row == 5)
			ret+="F";
		else if(row == 6)
			ret+="G";
		else if(row == 7)
			ret+="H";
		return ret;
	}
	
	public <T> void moveAIThread(BoardController bc) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		final Task<Coordinate[]> t1 = new Task<Coordinate[]>() {

			@Override
			protected Coordinate[] call() throws Exception {
				double value = StartScreenController.value;
				double newMean = value/100*4.4+.8;
				//System.out.println("Cont value: "+StartScreenController.value+"(pre calc)newMean"
				//			+(StartScreenController.value/100*5));
				
				double randomizer= -1;
				while(randomizer< .8 || randomizer > 5.2) {
					randomizer = (rand.nextGaussian()*3/4+newMean);
				//	System.out.println("randomizer: "+randomizer+" new mean: "+newMean);

				}
				
				double roundedRandomizer = Math.round(randomizer);
				//System.out.println("randomizer: "+roundedRandomizer);
				double d = rand.nextDouble();
				boolean setStrategy = setStrategy(d);
				//System.out.println("newMean: "+newMean+" randomizer: "+randomizer);

				//System.out.println("setStrat "+setStrategy+" d: "+d +" RR: "+ roundedRandomizer);
				return bc.getMyAI().getBestMove(BoardController.boardModel, (int)roundedRandomizer, setStrategy, rand, true);
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


	public Coordinate[] getBestMove(Board board, int depth, boolean setStrategy, Random rand, boolean adjustTime) {
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

						if(!setStrategy&& turn.equals(Type.WHITE)) {
							value += 10*rand.nextDouble();
						}
						if(!setStrategy && turn.equals(Type.BLACK)){
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
		if(adjustTime) {
			try {
				Thread.sleep((5-depth)*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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


