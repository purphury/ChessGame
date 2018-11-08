package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class King extends Piece {
	public King(Type color) {
		super(color);
	}

	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		//first number is the vertical direction, the second is the horizontal direction
		//negative is up or left, positive is down or right. Zero keeps it stationary
		addMovement(availCoords, r - 1, c, board);// South direction
		addMovement(availCoords, r, c - 1, board);// West direction
		addMovement(availCoords, r + 1, c, board);// North direction
		addMovement(availCoords, r, c + 1, board);// East direction
		addMovement(availCoords, r - 1, c - 1, board);// Northwest direction
		addMovement(availCoords, r - 1, c + 1, board);// Northeast direction
		addMovement(availCoords, r + 1, c - 1, board);// Southwest direction
		addMovement(availCoords, r + 1, c + 1, board);// Southeast direction}

		return availCoords;
	}

	public void addMovement(ArrayList<Coordinate> availCoords, int r, int c, Board board) {
		if (boundsChecker(r, c)) {
			if (board.hasPiece(new Coordinate(r, c))) {// a piece is on this location
				if (board.getPiece(new Coordinate(r, c)).getType() == this.otherType())// its an enemy piece
					availCoords.add(new Coordinate(r, c));
			} 
			else // This location is a free space
				availCoords.add(new Coordinate(r, c));
		}
	}

}