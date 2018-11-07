package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Bishop extends Piece {
	public Bishop(Type color) {
		super(color);
	}

	@Override
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		boolean NWBlocked = false, NEBlocked = false, SWBlocked = false, SEBlocked = false;
		for (int i = 1; i < 8 ; i++) {
			//northwest path
			if (!NWBlocked && r - i >= 0 && c - i >= 0) {
				if(board.getPiece(new Coordinate(r - i, c), this.getType()) != null) { //blocked by ally
					NWBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r - i, c - i));
				if(board.getPiece(new Coordinate(r - i, c - i), this.otherType()) != null)
					NWBlocked = true;
			}
		} for (int i = 1; i < 8 ; i++) {
			//northeast path
			if (!NEBlocked && r - i >= 0 && c + i <= 7) {
				if(board.getPiece(new Coordinate(r - i, c), this.getType()) != null) { //blocked by ally
					NEBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r - i, c + i));
				if(board.getPiece(new Coordinate(r - i, c + i), this.otherType()) != null)
					NEBlocked = true;
			}
		} for (int i = 1; i < 8 ; i++) {
			//southwest path
			if (!SWBlocked && r + i <= 7 && c - i >= 0) {
				if(board.getPiece(new Coordinate(r - i, c), this.getType()) != null) { //blocked by ally
					SWBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r + i, c - i));
				if(board.getPiece(new Coordinate(r + i, c - i), this.otherType()) != null)
					SWBlocked = true;
			}
		} for (int i = 1; i < 8 ; i++) {
			//southeast path
			if (!SEBlocked && r + i <= 7 && c + i <= 7) {
				if(board.getPiece(new Coordinate(r - i, c), this.getType()) != null) { //blocked by ally
					SEBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r + i, c + i));
				if(board.getPiece(new Coordinate(r + i, c + i), this.otherType()) != null)
					SEBlocked = true;
			}
		}
		return availCoords;
	}
}
