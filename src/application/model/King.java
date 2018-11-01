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
		
		if (r - 1 >= 0) 
			availCoords.add(new Coordinate(r - 1, c));
		if (c + 1 <= 7) 
			availCoords.add(new Coordinate(r, c + 1));
		if (r + 1 <= 7) 
			availCoords.add(new Coordinate(r + 1, c));
		if (c - 1 >= 0) 
			availCoords.add(new Coordinate(r, c - 1));
		if (r - 1 >= 0 && c - 1 >= 0) 
			availCoords.add(new Coordinate(r - 1, c - 1));
		if (r - 1 >= 0 && c + 1 <= 7) 
			availCoords.add(new Coordinate(r, c + 1));
		if (r + 1 <= 7 && c - 1 >= 0) 
			availCoords.add(new Coordinate(r + 1, c - 1));
		if (r + 1 <= 7 && c + 1 <= 7) 
			availCoords.add(new Coordinate(r + 1, c - 1));
		
		return availCoords;
	}
}