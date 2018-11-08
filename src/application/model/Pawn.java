package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Pawn extends Piece
{
	private int startR, startC;
	public Pawn(int r, int c, Type color) {
		super(color);
		startR = r;
		startC = c;
	}
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		//If pawn is in starting position//
		if(startR == r && startC == c) {
			//color is white//
			if(this.getType() == Type.WHITE) {
				if(r - 1 >= 0 && board.getPiece(new Coordinate(r - 1, c), this.otherType()) == null)
					availCoords.add(new Coordinate(r - 1, c));
				if(r - 2 >= 0 && board.getPiece(new Coordinate(r - 1, c), this.otherType()) == null) 
					availCoords.add(new Coordinate(r - 2, c));
			}
			//color is black//
			else {
				if(r + 1 <= 7 && board.getPiece(new Coordinate(r + 1, c), this.otherType()) == null)
					availCoords.add(new Coordinate(r + 1, c));
				if(r + 2 <= 7 && board.getPiece(new Coordinate(r + 1, c), this.otherType()) == null)
					availCoords.add(new Coordinate(r + 2, c));
			}
		} 
		//if pawn is not in starting position//
		else {
			//color is white//
			if(r - 1 >= 0 && this.getType() == Type.WHITE && 
					board.getPiece(new Coordinate(r - 1, c), this.otherType()) == null && 
					board.getPiece(new Coordinate(r - 1, c), this.getType()) == null)
				availCoords.add(new Coordinate(r - 1, c));
			//color is black//
			else if(r + 1 <= 7 && 
					board.getPiece(new Coordinate(r + 1, c), this.otherType()) == null &&
					board.getPiece(new Coordinate(r + 1, c), this.getType()) == null)
				availCoords.add(new Coordinate(r + 1, c));
		}
		//Color is white; these are coordinates to attack diagonal pieces//
		if(this.getType() == Type.WHITE) {
			if(boundsChecker(r - 1, c - 1))
				if(board.getPiece(new Coordinate(r - 1, c - 1), this.otherType()) != null)
					availCoords.add(new Coordinate(r - 1, c - 1));
			if(boundsChecker(r - 1, c + 1))
				if(board.getPiece(new Coordinate(r - 1, c + 1), this.otherType()) != null)
					availCoords.add(new Coordinate(r - 1, c + 1));
		}
		//Color is black; these are coordinates to attack diagonal pieces//
		else {
			if(boundsChecker(r + 1, c - 1))
				if(board.getPiece(new Coordinate(r + 1, c - 1), this.otherType()) != null)
				availCoords.add(new Coordinate(r + 1, c - 1));
			if(boundsChecker(r + 1, c + 1))
				if(board.getPiece(new Coordinate(r + 1, c + 1), this.otherType()) != null)
				availCoords.add(new Coordinate(r + 1, c + 1));
		}

		return availCoords;
	}
	public String toString() {
		return "P";
	}

}
