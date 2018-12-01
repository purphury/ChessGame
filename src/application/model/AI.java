package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class AI {

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
		Type turn = board.getTurn();
		double value, max = turn.equals(Type.WHITE) ? -Double.MAX_VALUE : Double.MAX_VALUE; 
		Coordinate move[] = {null, null}; // old, new
		
		for(int i=0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				Coordinate coord = new Coordinate(i, j);
				if(board.hasPiece(coord)) {
					ArrayList<Coordinate> availableMoves = board.getMoves(coord);
					
					for(Coordinate c : availableMoves) {
						board.movePieces(coord, c);
						value = minimax(board, depth - 1, !turn.equals(Type.WHITE));
						board.undo();
						
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
}

private double minimax(Board board, int depth, boolean maximize){
	if(depth == 0)
		return this.evaluateBoard(board);
	
	Type turn = board.getTurn();
	double value, max = turn.equals(Type.WHITE) ? -Double.MAX_VALUE : Double.MAX_VALUE; 
	
	for(int i=0; i<8; i++) {
		for(int j = 0; j<8; j++) {
			Coordinate coord = new Coordinate(i, j);
			if(board.hasPiece(coord)) {
				ArrayList<Coordinate> availableMoves = board.getMoves(coord);
				
				for(Coordinate c : availableMoves) {
					board.movePieces(coord, c);
					value = minimax(board, depth - 1, !turn.equals(Type.WHITE));
					board.undo();
					
					if(turn.equals(Type.WHITE) ? value > max : value < max) 
						max = value;
				}					
			}
		}
	}
	return max;
}
}
