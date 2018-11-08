package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Rook extends Piece 
{
	public Rook(Type color)
	{
		super (color);
	}
	private int verticalInc;
	private int horizontalInc;

	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		addMovements(availCoords, -1,0, r, c, board);//South direction
		addMovements(availCoords, 0,-1, r, c, board);//West direction
		addMovements(availCoords, 1,0, r, c, board);//North direction
		addMovements(availCoords, 0,1, r, c, board);//East direction
		
		return availCoords;
	}
	
	public void addMovements(ArrayList<Coordinate> availCoords,int rowInc, int columnInc
															, int r, int c, Board board){
		
	for (int i = 1; i < 8 ; i++) {
		System.out.println("row: "+(r+i*rowInc)+ " column: "+(c+i*columnInc));
		System.out.println("boundsChecker: "+ boundsChecker(r+i*rowInc, c+i*columnInc));
		if (boundsChecker(r+i*rowInc, c+i*columnInc)) {
			if(board.hasPiece(new Coordinate(r + i*rowInc, c+i*columnInc))) { 					
				if(board.getPiece(new Coordinate(r+i*rowInc,c+i*columnInc)).getType() == this.otherType())
					availCoords.add(new Coordinate(r+i*rowInc,c+i*columnInc));				
				break;				
			}
			else
				availCoords.add(new Coordinate(r+i*rowInc,c+i*columnInc));			
		}
	}
	}		
	
		public String toString() {
	
		return "R";
	}
}
