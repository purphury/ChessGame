package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class AI {
	private static int totalDepth;

	public double evaluateBoard(Board board) {
		double strength = 0;
		Piece[][] boardM = board.getBoard();
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(boardM[i][j] != null)
					strength += boardM[i][j].getStrength(i, j);
			}
		return strength;
	}

	public Coordinate[] getBestMove(Board board, int depth) {
		Board newBoard = new Board(board);
		totalDepth = depth;
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
						value = minimax(newBoard, depth - 1, alpha, beta, !toMaximize);
						newBoard.undo();


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

	private double minimax(Board board, int depth, double alpha, double beta, boolean toMaximize){
		if(depth <= 0)
			return this.evaluateBoard(board);
		
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
						value = minimax(newBoard, depth - 1, alpha, beta, !toMaximize);
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


