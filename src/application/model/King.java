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
		int[][] possCoords = {{r-1,c},{r,c+1},{r+1,c},{r+1,c},{r,c-1},{r-1,c-1},{r,c+1},{r+1,c-1},{r+1,c-1}};

		for(int i = 0; i < 8; i++) {
			if (boundsChecker(possCoords[i][0], possCoords[i][1])) 
				if(board.getPiece(possCoords[i][0], possCoords[i][1], this.getType()) == null || board.getPiece(possCoords[i][0], possCoords[i][1], this.otherType()) != null) 
					availCoords.add(new Coordinate(possCoords[i][0], possCoords[i][1]));
		}
		
		return availCoords;
	}

	public String toString() {
		return "K";
	}
}