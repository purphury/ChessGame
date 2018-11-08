package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Rook extends Piece 
{
	public Rook(Type color)
	{
		super (color);
	}

	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		boolean NBlocked = false, EBlocked = false, SBlocked = false, WBlocked = false;
		for (int i = 1; i < 8 ; i++){
			//north path
			if (!NBlocked && r - i >= 0) {
				if(board.hasPiece(new Coordinate(r - i, c))) { 					
					NBlocked = true;
					if(board.getPiece(new Coordinate(r-i,c)).getType() == this.otherType())
						availCoords.add(new Coordinate(r - i, c));
					
				}
				else
					availCoords.add(new Coordinate(r - i, c));
				
				continue;
			}
		} for (int i = 1; i < 8 ; i++){
			//east path
			if (!EBlocked && c + i <= 7) {
				if(board.hasPiece(new Coordinate(r , c+i))) { 					
					EBlocked = true;
					if(board.getPiece(new Coordinate(r,c+i)).getType() == this.otherType())
						availCoords.add(new Coordinate(r, c+i));
					
				}
				else
					availCoords.add(new Coordinate(r , c+i));
				
				continue;
			}
		} for (int i = 1; i < 8 ; i++) {
			//south path
			if (!SBlocked && r + i <= 7) {
				if(board.hasPiece(new Coordinate(r + i, c))) { 					
					SBlocked = true;
					if(board.getPiece(new Coordinate(r+i,c)).getType() == this.otherType())
						availCoords.add(new Coordinate(r + i, c));
					
				}
				else
					availCoords.add(new Coordinate(r + i, c));
				
				continue;
			}
		} for (int i = 1; i < 8 ; i++) {
			//west path
			if (!WBlocked && c - i >= 0) {
				if(board.hasPiece(new Coordinate(r, c-i))) { 					
					WBlocked = true;
					if(board.getPiece(new Coordinate(r,c-i)).getType() == this.otherType())
						availCoords.add(new Coordinate(r, c-i));
					
				}
				else
					availCoords.add(new Coordinate(r, c-i));
				
				continue;
			}
		}
		return availCoords;
	}
	public String toString() {
		return "R";
	}
}
