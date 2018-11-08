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
				//System.out.println(board.getPiece(new Coordinate(r - i, c - i), this.getType()));
				if(board.hasPiece(new Coordinate(r - i, c - i))) {
					NWBlocked = true;
					if(board.getPiece(new Coordinate(r - i, c - i)).getType() == this.otherType()) {
						availCoords.add(new Coordinate(r - i, c - i));
						continue;
					}						
				}
				else
					availCoords.add(new Coordinate(r - i, c - i));				
			}
		} for (int i = 1; i < 8 ; i++) {
			//northeast path
			if (!NEBlocked && r - i >= 0 && c + i <= 7) {
				if(board.hasPiece(new Coordinate(r - i, c + i))) {
					NEBlocked = true;
					if(board.getPiece(new Coordinate(r - i, c + i)).getType() == this.otherType()) {
						availCoords.add(new Coordinate(r - i, c + i));
						continue;
					}						
				}
				else
					availCoords.add(new Coordinate(r - i, c + i));
			}
		} for (int i = 1; i < 8 ; i++) {
			//southwest path
			if (!SWBlocked && r + i <= 7 && c - i >= 0) {
				if(board.hasPiece(new Coordinate(r + i, c - i))) {
					SWBlocked = true;
					if(board.getPiece(new Coordinate(r + i, c - i)).getType() == this.otherType()) {
						availCoords.add(new Coordinate(r + i, c - i));
						continue;
					}						
				}
				else
					availCoords.add(new Coordinate(r + i, c - i));
			}
		} for (int i = 1; i < 8 ; i++) {
			//southeast path
			if (!SEBlocked && r + i <= 7 && c + i <= 7) {
				if(board.hasPiece(new Coordinate(r +i, c + i))) {
					SEBlocked = true;
					if(board.getPiece(new Coordinate(r + i, c + i)).getType() == this.otherType()) {
						availCoords.add(new Coordinate(r + i, c + i));
						continue;
					}						
				}
				else
					availCoords.add(new Coordinate(r + i, c + i));
			}
		}
		return availCoords;
	}
	public String toString() {
		return "B";
	}
}
