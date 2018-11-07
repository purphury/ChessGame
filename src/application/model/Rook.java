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
				if(board.getPiece(new Coordinate(r - i, c), this.getType()) != null) { //blocked by ally
					NBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r - i, c));
				if(board.getPiece(new Coordinate(r - i, c), this.otherType()) != null) //blocked by enemy
					NBlocked = true;
			}
			//east path
			if (!EBlocked && c + i <= 7) {
				if(board.getPiece(new Coordinate(r, c + i), this.getType()) != null) { //blocked by ally
					EBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r, c + i));
				if(board.getPiece(new Coordinate(r, c + i), this.otherType()) != null)
					EBlocked = true;
			}
			//south path
			if (!SBlocked && r + i <= 7) {
				if(board.getPiece(new Coordinate(r + i, c), this.getType()) != null) { //blocked by ally
					SBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r + i, c));
				if(board.getPiece(new Coordinate(r + i, c), this.otherType()) != null)
					SBlocked = true;
			}
			//west path
			if (!WBlocked && c - i >= 0) {
				if(board.getPiece(new Coordinate(r, c - i), this.getType()) != null) { //blocked by ally
					WBlocked = true;
					continue;
				}
				availCoords.add(new Coordinate(r, c - i));
				if(board.getPiece(new Coordinate(r, c - i), this.otherType()) != null)
					WBlocked = true;
			}
		}
		return availCoords;
	}
}
