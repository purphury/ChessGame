package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Bishop extends Piece {
	public Bishop(Type color) {
		super(color, 30);
	}

	public double getStrength(int r, int c) {
		return (this.getType() == Type.WHITE ? 30 + StrengthBoard.BishopStrengthBoard[r][c] : -30 - StrengthBoard.BishopStrengthBoard[8-r-1][c]);
	}
	
	@Override
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		//first number is the vertical direction, the second is the horizontal direction
		//negative is up or left, positive is down or right. Zero keeps it stationary
		addMovements(availCoords, -1, -1, r, c, board);//Northwest direction
		addMovements(availCoords, -1, 1, r, c, board);//Northeast direction
		addMovements(availCoords, 1, -1, r, c, board);//Southwest direction
		addMovements(availCoords, 1, 1, r, c, board);//Southeast direction}
		return availCoords;
	}

	public void addMovements(ArrayList<Coordinate> availCoords, int rowInc, int columnInc, int r, int c, Board board) {
		for (int i = 1; i < 8; i++) {
			if (boundsChecker(r + i * rowInc, c + i * columnInc)) {// Checks if location is on the board
				if (board.hasPiece(new Coordinate(r + i * rowInc, c + i * columnInc))) { // A piece is on this location
					if (board.getPiece(new Coordinate(r + i * rowInc, c + i * columnInc)).getType() == this.otherType()) // Its an enemy piece
						availCoords.add(new Coordinate(r + i * rowInc, c + i * columnInc));
					break;
				} else // Location is a free space
					availCoords.add(new Coordinate(r + i * rowInc, c + i * columnInc));
			}
		}
	}
	
	public String toString() {
		return "B";
	}
}
