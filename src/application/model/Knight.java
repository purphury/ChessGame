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
	public ArrayList<Point> getAvailableMovements(int x, int y) {
		ArrayList<Point> availCoords = new ArrayList<>();
		// two squares away horizontally and one square vertically
		availCoords.add(new Point(x+1, y+2));
		availCoords.add(new Point(x-1, y+2));
		availCoords.add(new Point(x+1, y-2));
		availCoords.add(new Point(x-1, y-2));
		//two squares vertically and one square horizontally
		availCoords.add(new Point(x+2, y+1));
		availCoords.add(new Point(x+2, y-1));
		availCoords.add(new Point(x-2, y-1));
		availCoords.add(new Point(x-2, y+1));
		
		return availCoords;
	}

}
