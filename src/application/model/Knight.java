package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Knight extends Piece 
{

	public Knight(Type color) {
		super(color);
	}

	@Override
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();

		// two squares away horizontally and one square vertically		
		addMovement(availCoords, r + 1, c + 2, board);	
		addMovement(availCoords, r - 1, c + 2, board);
		addMovement(availCoords, r + 1, c - 2, board);
		addMovement(availCoords, r - 1, c - 2, board);

		//two squares vertically and one square horizontally
		addMovement(availCoords, r + 2, c + 1, board);
		addMovement(availCoords, r + 2, c - 1, board);
		addMovement(availCoords, r - 2, c - 1, board);
		addMovement(availCoords, r - 2, c + 1, board);

		return availCoords;
	}

	public void addMovement(ArrayList<Coordinate> availCoords,int r, int c, Board board) {
		if (boundsChecker(r, c)) {
			if(board.hasPiece(new Coordinate(r, c))) {//a piece is on this location
				if(board.getPiece(new Coordinate(r, c)).getType() == this.otherType()) {//its an enemy piece
					availCoords.add(new Coordinate(r, c));
					
				}
			}
			else {	//This location is a free space	
				availCoords.add(new Coordinate(r, c));
			}
		}
	}
}
