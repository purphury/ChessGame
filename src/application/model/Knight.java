package application.model;

import java.awt.Point;
import java.util.ArrayList;

import application.model.Board.Type;

public class Knight extends Piece 
{

	public Knight(Type color) 
	{
		super(color);
	}

	@Override
	public ArrayList<Point> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Point> availCoords = new ArrayList<>();
		// two squares away horizontally and one square vertically
		availCoords.add(new Point(r + 1, c + 2));
		availCoords.add(new Point(r - 1, c + 2));
		availCoords.add(new Point(r + 1, c - 2));
		availCoords.add(new Point(r - 1, c - 2));
		//two squares vertically and one square horizontally
		availCoords.add(new Point(r + 2, c + 1));
		availCoords.add(new Point(r + 2, c - 1));
		availCoords.add(new Point(r - 2, c - 1));
		availCoords.add(new Point(r - 2, c + 1));
		
		return availCoords;
	}

}
