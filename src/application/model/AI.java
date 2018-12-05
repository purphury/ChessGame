package application.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import application.controller.BoardController;
import application.controller.StartScreenController;
import application.model.Board.Type;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;


public class AI {
	private int count;
	private int abCount;
	
	
	public <T> void feedbackThread(BoardController bc, Coordinate oldLoc, Coordinate newLoc) {
		Task<Object> t1 = new Task<Object>() {

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
		Double value;

		double alpha = -100000;
		double beta = 100000;
		boolean toSort = false;
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
						value = minimax(newBoard, depth - 1, alpha, beta, !toMaximize, setStrategy, toSort);
						newBoard.undo();
						Coordinate move[] = {null,null}; // old, new
						move[0] = coord; 
						move[1] = c;
						listOfMoves.add(new MoveValue(coord, c, value));
						

					}					
				}
			}
		}

		listOfMoves.sort(new Comparator<MoveValue>() {

			@Override
			public int compare(MoveValue o1, MoveValue o2) {
				if(board.getTurn() == Type.WHITE) {
					return Double.compare(o2.getValue(), o1.getValue());
				}
				else {
					return Double.compare(o1.getValue(), o2.getValue());

				}
			}
			
		});

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
		if(StartScreenController.value >=80)
			str+=". But watch out! You're opponent is much smarter than me.";
		else if(StartScreenController.value >= 60)
			str+=". But watch out! You're opponent is a little smarter than me.";
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
				double newMean = value/100*4+1;
				
				double randomizer= -1;
				while(randomizer< .5 || randomizer > 5.5) {
					randomizer = (rand.nextGaussian()*3/4+newMean);
				}
				
				double roundedRandomizer = Math.round(randomizer);
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
		count = 0;
		abCount= 0;
		Board newBoard = new Board(board);
		Type turn = newBoard.getTurn();
		boolean toSort = true;
		ArrayList<Coordinate[]> availableMoves2 = new ArrayList<Coordinate[]>();
		double value, max = turn.equals(Type.WHITE) ? -Double.MAX_VALUE : Double.MAX_VALUE; 
		Coordinate move[] = {null, null}; // old, new
		double alpha = -100000;
		double beta = 100000;
		boolean toMaximize = turn.equals(Type.WHITE) ? true : false;//p1 maximizes p2 minimizes
		for(int i=0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				Coordinate coord = new Coordinate(i, j);
				if(newBoard.hasPiece(coord)&& newBoard.getPiece(coord).getType()==turn) {
					ArrayList<Coordinate> availableMoves = newBoard.getMoves(coord);
					for(Coordinate c : availableMoves) {
						availableMoves2.add(new Coordinate[]{coord, c});

					}									
				}
			}
		}
		
		availableMoves2.sort(new Comparator<Coordinate[]>() {

			@Override
			public int compare(Coordinate[] o1, Coordinate[] o2) {
				Board newBoard = new Board(board);
				newBoard.movePieces(((Coordinate[])o1)[0], ((Coordinate[])o1)[1]);
				Double d1 = evaluateBoard(newBoard, true);
			
				Board newBoard2 = new Board(board);
				newBoard2.movePieces(((Coordinate[])o2)[0], ((Coordinate[])o2)[1]);
				Double d2 = evaluateBoard(newBoard2,true);
				if(toMaximize) {					
					return Double.compare(d2, d1);
				}
				else {
					return Double.compare(d1, d2);

				}
			}
			
		});
		for(Coordinate[] coord: availableMoves2) {
			newBoard.setPreviousBoard(new Board(newBoard));

			newBoard.movePieces(coord[0], coord[1]);
			value = minimax(newBoard, depth - 1, alpha, beta, !toMaximize, setStrategy, toSort);
			newBoard.undo();


			if(!setStrategy&& turn.equals(Type.WHITE)) {
				value += 10*rand.nextDouble();
			}
			if(!setStrategy && turn.equals(Type.BLACK)){
				value -= 10*rand.nextDouble();
			}

			if(toMaximize ? value > max : value < max) {
				move[0] = coord[0]; move[1] = coord[1];
				max = value;
			}

			if(toMaximize) {
				alpha = Math.max(alpha, max);
			}

			if(!toMaximize) {
				beta = Math.min(beta, max);
			}

		}

		//System.out.println("Count: "+count+" abCount: "+abCount);
		if(adjustTime) {
			try {
				int sleepTime=0;
				if(depth <=4)
					sleepTime = depth;
				Thread.sleep((4-sleepTime)*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return move;
	}

	private double minimax(Board board, int depth, double alpha, double beta, boolean toMaximize, boolean useStrategy, boolean toSort){
		if(depth <= 0) {
			count++;
			return this.evaluateBoard(board, useStrategy);
		}
		count++;

		Board newBoard = new Board(board);
		ArrayList<Coordinate[]> availableMoves2 = new ArrayList<Coordinate[]>();

		Type turn = newBoard.getTurn();
		double value, max = toMaximize ? -Double.MAX_VALUE : Double.MAX_VALUE; 

		for(int i=0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				Coordinate coord = new Coordinate(i, j);
				if(newBoard.hasPiece(coord)&& newBoard.getPiece(coord).getType()==turn) {
					ArrayList<Coordinate> availableMoves = newBoard.getMoves(coord);
					for(Coordinate c : availableMoves) {
						availableMoves2.add(new Coordinate[]{coord, c});

					}
						
										
				}
			}
		}
		if(toSort) {
			availableMoves2.sort(new Comparator<Coordinate[]>() {

				@Override
				public int compare(Coordinate[] o1, Coordinate[] o2) {
					Board newBoard = new Board(board);
					newBoard.movePieces(((Coordinate[])o1)[0], ((Coordinate[])o1)[1]);
					Double d1 = evaluateBoard(newBoard, true);

					Board newBoard2 = new Board(board);
					newBoard2.movePieces(((Coordinate[])o2)[0], ((Coordinate[])o2)[1]);
					Double d2 = evaluateBoard(newBoard2,true);
					if(toMaximize) {

						return Double.compare(d2, d1);
					}
					else {
						return Double.compare(d1, d2);

					}
				}

			});
		}
		
		for(Coordinate[] coord: availableMoves2) {
			newBoard.setPreviousBoard(new Board(newBoard));

			newBoard.movePieces(coord[0], coord[1]);
			value = minimax(newBoard, depth - 1, alpha, beta, !toMaximize, useStrategy, toSort);
			newBoard.undo();

			if(toMaximize ? value > max : value < max) {
				max = value;

			}
			
			if(toMaximize) {
				alpha = Math.max(alpha, max);
				if(alpha >= beta) {
					abCount++;
					return max;
				}
			}

			if(!toMaximize) {
				beta = Math.min(beta, max);
				if(beta <= alpha) {
					abCount++;
					return max;
				}
			}				
		
		}
		
		return max;
	}
}


