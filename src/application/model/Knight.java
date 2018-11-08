package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Knight extends Piece 
{

	public Knight(Type color) 
	{
		super(color);
	}

	@Override
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		
		// two squares away horizontally and one square vertically		
		if(boundsChecker(r + 1, c + 2)) {
			if(board.hasPiece(new Coordinate(r+1,c+2))) {
				if(board.getPiece(new Coordinate(r+1,c+2)).getType() == this.otherType())
					availCoords.add(new Coordinate(r + 1, c + 2));
			}
			else
				availCoords.add(new Coordinate(r + 1, c + 2));			
		}
		if(boundsChecker(r - 1, c + 2)) {
			if(board.hasPiece(new Coordinate(r-1,c+2))) {
				if(board.getPiece(new Coordinate(r-1,c+2)).getType() == this.otherType())
					availCoords.add(new Coordinate(r-1, c + 2));
			}
			else
			availCoords.add(new Coordinate(r - 1, c + 2));
		}
		if(boundsChecker(r + 1, c - 2)) {
			if(board.hasPiece(new Coordinate(r+1,c-2))) {
				if(board.getPiece(new Coordinate(r+1,c-2)).getType() == this.otherType())
					availCoords.add(new Coordinate(r+1, c - 2));
			}
			else			
			availCoords.add(new Coordinate(r + 1, c - 2));
		}
		if(boundsChecker(r - 1, c - 2)) {
			if(board.hasPiece(new Coordinate(r-1,c-2))) {
				if(board.getPiece(new Coordinate(r-1,c-2)).getType() == this.otherType())
					availCoords.add(new Coordinate(r-1, c - 2));
			}
			else			
			availCoords.add(new Coordinate(r - 1, c - 2));
		}
		//two squares vertically and one square horizontally
		if(boundsChecker(r + 2, c + 1)) {
			if(board.hasPiece(new Coordinate(r+2,c+1))) {
				if(board.getPiece(new Coordinate(r+2,c+1)).getType() == this.otherType())
					availCoords.add(new Coordinate(r+2, c+1));
			}
			else			
			availCoords.add(new Coordinate(r + 2, c+1));
		}
		if(boundsChecker(r + 2, c - 1)) {
			if(board.hasPiece(new Coordinate(r+2,c-1))) {
				if(board.getPiece(new Coordinate(r+2,c-1)).getType() == this.otherType())
					availCoords.add(new Coordinate(r+2, c-1));
			}
			else						
			availCoords.add(new Coordinate(r + 2, c - 1));
		}
		if(boundsChecker(r - 2, c - 1)) {
			if(board.hasPiece(new Coordinate(r-2,c-1))) {
				if(board.getPiece(new Coordinate(r-2,c-1)).getType() == this.otherType())
					availCoords.add(new Coordinate(r-2, c-1));
			}
			else						
			availCoords.add(new Coordinate(r - 2, c - 1));
		}
		if(boundsChecker(r - 2, c + 1)) {
			if(board.hasPiece(new Coordinate(r-2,c+1))) {
				if(board.getPiece(new Coordinate(r-2,c+1)).getType() == this.otherType())
					availCoords.add(new Coordinate(r-2, c+1));
			}
			else						
			availCoords.add(new Coordinate(r - 2, c + 1));
		}
		
		return availCoords;
	}
	
	public String toString() {
		return "k";
	}
}
