package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Pawn extends Piece {
	private int startR, startC;
	private enum IsAttack{NO, YES}; 

	public Pawn(int r, int c, Type color) {
		super(color);
		startR = r;
		startC = c;
	}

	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		// color is white//
		if (this.getType() == Type.WHITE ) {
			addMovement(availCoords, r - 1, c, board, IsAttack.NO);
			addMovement(availCoords, r - 1, c - 1, board, IsAttack.YES);		
			addMovement(availCoords, r - 1, c + 1, board, IsAttack.YES);		
		}
		// color is black//
		if (this.getType() == Type.BLACK ) {
			addMovement(availCoords, r + 1, c, board, IsAttack.NO);
			addMovement(availCoords, r + 1, c - 1, board, IsAttack.YES);		
			addMovement(availCoords, r + 1, c + 1, board, IsAttack.YES);
		}			
		// if pawn is in starting position
		if (startR == r && startC == c) {			
			// color is white//
			if(this.getType() == Type.WHITE) 
				addMovement(availCoords, r - 2, c, board, IsAttack.NO);

			// color is black//
			if(this.getType() == Type.BLACK) 
				addMovement(availCoords, r + 2, c, board, IsAttack.NO);			
		}		
		return availCoords;
	}

	public void addMovement(ArrayList<Coordinate> availCoords,int r, int c, Board board,IsAttack attackStatus) {		
		if(boundsChecker(r, c)) { //the location is inbounds
			if(board.hasPiece(new Coordinate(r, c))) { //a piece is on this location
				if(attackStatus == IsAttack.YES) { //its an attack move
					if(board.getPiece(new Coordinate(r, c)).getType() == this.otherType()) //its an enemy piece
						availCoords.add(new Coordinate(r, c));
				}
			}
			else if(attackStatus == IsAttack.NO)  //This location is a free space	
				availCoords.add(new Coordinate(r, c));
		}
	}
}
