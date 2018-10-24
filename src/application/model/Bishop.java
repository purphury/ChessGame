package application.model;

import java.awt.Point;
import java.util.ArrayList;

import application.model.Board.Type;

public class Bishop extends Piece {
	public Bishop(Type color) {
		super(color);
	}

	@Override
	public ArrayList<Point> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Point> availCoords = new ArrayList<>();
		boolean NWBlocked = false, NEBlocked = false, SWBlocked = false, SEBlocked = false;
		for (int i = 0; i < 8 ; i++) {
			//northwest path
			if (!NWBlocked && r - i >= 0 && c - i >= 0) {
				availCoords.add(new Point(r - i, c - i));
				if(board.getPiece(r - i, c - i) != null)
					NWBlocked = true;
			}
			//northeast path
			if (!NEBlocked && r - i >= 0 && c + i <= 7) {
				availCoords.add(new Point(r, c + i));
				if(board.getPiece(r, c + i) != null)
					NEBlocked = true;
			}
			//southwest path
			if (!SWBlocked && r + i <= 7 && c - i >= 0) {
				availCoords.add(new Point(r + i, c - i));
				if(board.getPiece(r + i, c - i) != null)
					SWBlocked = true;
			}
			//northwest path
			if (!SEBlocked && r + i <= 7 && c + i <= 7) {
				availCoords.add(new Point(r + i, c - i));
				if(board.getPiece(r + i, c - i) != null)
					SEBlocked = true;
			}
		}
		return availCoords;
	
	}

	
}
