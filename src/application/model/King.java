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
				if(board.getPiece(new Coordinate(possCoords[i][0], possCoords[i][1]), this.getType()) == null || 
				board.getPiece(new Coordinate(possCoords[i][0], possCoords[i][1]), this.otherType()) != null) 
					availCoords.add(new Coordinate(possCoords[i][0], possCoords[i][1]));
		}

		//		if (boundsChecker(r - 1, c - 1)) 
		//			if(board.getPiece(new Coordinate(r - 1, c - 1), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c - 1), this.otherType()) != null) 
		//				availCoords.add(new Coordinate(r - 1, c - 1));
		//		if (boundsChecker(r - 1)) if(board.getPiece(new Coordinate(r - 1, c), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c), this.otherType()) != null) 
		//			availCoords.add(new Coordinate(r - 1, c));
		//		if (boundsChecker(r - 1, c + 1)) 
		//			if(board.getPiece(new Coordinate(r - 1, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r - 1, c + 1), this.otherType()) != null) 
		//				availCoords.add(new Coordinate(r - 1, c + 1));
		//		if (boundsChecker(c - 1)) 
		//			if(board.getPiece(new Coordinate(r, c - 1), this.getType()) == null || board.getPiece(new Coordinate(r, c - 1), this.otherType()) != null) 
		//				availCoords.add(new Coordinate(r, c - 1));
		//		if (boundsChecker(c + 1)) 
		//			if(board.getPiece(new Coordinate(r, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r, c + 1), this.otherType()) != null) 
		//				availCoords.add(new Coordinate(r, c + 1));
		//		if (boundsChecker(r + 1, c - 1)) 
		//			if(board.getPiece(new Coordinate(r + 1, c - 1), this.getType()) == null || board.getPiece(new Coordinate(r + 1, c - 1), this.otherType()) != null) 
		//				availCoords.add(new Coordinate(r + 1, c - 1));
		//		if (boundsChecker(r + 1))
		//			if(board.getPiece(new Coordinate(r + 1, c), this.getType()) == null || board.getPiece(new Coordinate(r + 1, c), this.otherType()) != null) 
		//				availCoords.add(new Coordinate(r + 1, c));
		//		if (boundsChecker(r + 1, c + 1))
		//			if(board.getPiece(new Coordinate(r + 1, c + 1), this.getType()) == null || board.getPiece(new Coordinate(r + 1, c + 1), this.otherType()) != null) 
		//				availCoords.add(new Coordinate(r + 1, c + 1));

		return availCoords;
	}

	public String toString() {
		return "K";
	}
}