package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Queen extends Piece {
	public Queen(Type color) {
		super(color);
	}

	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		boolean NWBlocked = false, NEBlocked = false, SWBlocked = false, SEBlocked = false;
		boolean NBlocked = false, EBlocked = false, SBlocked = false, WBlocked = false;
		for (int i = 1; i < 8; i++) {
			// north path
			if (!NBlocked && r - i >= 0) {
				if(board.getPiece(new Coordinate(r - i, c), this.getType()) != null) { //blocked by ally
					NBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r - i, c));
				if (board.getPiece(new Coordinate(r - i, c), this.otherType()) != null)
					NBlocked = true;
			}
			// east path
			if (!EBlocked && c + i <= 7) {
				if(board.getPiece(new Coordinate(r, c + i), this.getType()) != null) { //blocked by ally
					EBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r, c + i));
				if (board.getPiece(new Coordinate(r, c + i), this.otherType()) != null)
					EBlocked = true;
			}
			// south path
			if (!SBlocked && r + i <= 7) {
				if(board.getPiece(new Coordinate(r + i, c), this.getType()) != null) { //blocked by ally
					SBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r + i, c));
				if (board.getPiece(new Coordinate(r + i, c), this.otherType()) != null)
					SBlocked = true;
			}
			// west path
			if (!WBlocked && c - i >= 0) {
				if(board.getPiece(new Coordinate(r, c - i), this.getType()) != null) { //blocked by ally
					WBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r, c - i));
				if (board.getPiece(new Coordinate(r, c - i), this.otherType()) != null)
					WBlocked = true;
			}
			// northwest path
			if (!NWBlocked && r - i >= 0 && c - i >= 0) {
				if(board.getPiece(new Coordinate(r - i, c - i), this.getType()) != null) { //blocked by ally
					NWBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r - i, c - i));
				if (board.getPiece(new Coordinate(r - i, c - i), this.otherType()) != null)
					NWBlocked = true;
			}
			// northeast path
			if (!NEBlocked && r - i >= 0 && c + i <= 7) {
				if(board.getPiece(new Coordinate(r - i, c + i), this.getType()) != null) { //blocked by ally
					NEBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r - i, c + i));
				if (board.getPiece(new Coordinate(r - i, c + i), this.otherType()) != null)
					NEBlocked = true;
			}
			// southwest path
			if (!SWBlocked && r + i <= 7 && c - i >= 0) {
				if(board.getPiece(new Coordinate(r + i, c - i), this.getType()) != null) { //blocked by ally
					SWBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r + i, c - i));
				if (board.getPiece(new Coordinate(r + i, c - i), this.otherType()) != null)
					SWBlocked = true;
			}
			// northwest path
			if (!SEBlocked && r + i <= 7 && c + i <= 7) {
				if(board.getPiece(new Coordinate(r + i, c - i), this.getType()) != null) { //blocked by ally
					SEBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r + i, c - i));
				if (board.getPiece(new Coordinate(r + i, c - i), this.otherType()) != null)
					SEBlocked = true;
			}
		}
		return availCoords;
	}
}
