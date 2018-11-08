package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class King extends Piece
{
	public King(Type color) 
	{
		super(color);
	}

	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
//		int[][] possCoords = {{r-1,c},{r,c+1},{r+1,c},{r+1,c},{r,c-1},{r-1,c-1},{r,c+1},{r+1,c-1},{r+1,c-1}};
//		
//		for(int[] possCoord : possCoords){
//			if(0 <= possCoord[0] && possCoord[0] <= 7 && 0 <= possCoord[1] && possCoord[1] <= 7)
//				availCoords.add(new Coordinate(possCoord[0], possCoord[1]));
//		}	
		
		if (r - 1 >= 0 && board.getPiece(new Coordinate(r - 1, c), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c), this.otherType()) != null) 
			availCoords.add(new Coordinate(r - 1, c));
		if (c + 1 <= 7 && board.getPiece(new Coordinate(r, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r, c + 1), this.otherType()) != null) 
			availCoords.add(new Coordinate(r, c + 1));
		if (r + 1 <= 7 && board.getPiece(new Coordinate(r, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r + 1, c), this.otherType()) != null) 
			availCoords.add(new Coordinate(r + 1, c));
		if (c - 1 >= 0 && board.getPiece(new Coordinate(r, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c + 1), this.otherType()) != null) 
			availCoords.add(new Coordinate(r, c - 1));
		if (r - 1 >= 0 && c - 1 >= 0 && board.getPiece(new Coordinate(r, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c + 1), this.otherType()) != null) 
			availCoords.add(new Coordinate(r - 1, c - 1));
		if (r - 1 >= 0 && c + 1 <= 7 && board.getPiece(new Coordinate(r, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c + 1), this.otherType()) != null) 
			availCoords.add(new Coordinate(r, c + 1));
		if (r + 1 <= 7 && c - 1 >= 0 && board.getPiece(new Coordinate(r, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c + 1), this.otherType()) != null) 
			availCoords.add(new Coordinate(r + 1, c - 1));
		if (r + 1 <= 7 && c + 1 <= 7 && board.getPiece(new Coordinate(r, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c + 1), this.otherType()) != null) 
			availCoords.add(new Coordinate(r + 1, c - 1));
		
		return availCoords;
	}
}