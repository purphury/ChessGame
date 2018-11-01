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
		availCoords.add(new Coordinate(r + 1, c + 2));
		availCoords.add(new Coordinate(r - 1, c + 2));
		availCoords.add(new Coordinate(r + 1, c - 2));
		availCoords.add(new Coordinate(r - 1, c - 2));
		//two squares vertically and one square horizontally
		availCoords.add(new Coordinate(r + 2, c + 1));
		availCoords.add(new Coordinate(r + 2, c - 1));
		availCoords.add(new Coordinate(r - 2, c - 1));
		availCoords.add(new Coordinate(r - 2, c + 1));
		
		return availCoords;
	}

}
