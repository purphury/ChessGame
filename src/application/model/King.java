package application.model;

import java.awt.Point;
import java.util.ArrayList;

import application.model.Board.Type;

public class King extends Piece
{
	public King(Type color) 
	{
		super(color);
	}
	
	public ArrayList<Point> getAvailableMovements(int x, int y) 
	{
		ArrayList<Point> availCoords = new ArrayList<>();
		//forward movement
		availCoords.add(new Point(x, y+1));
		//diagonal forward movement
		availCoords.add(new Point(x-1, y+1));
		availCoords.add(new Point(x+1, y+1));
		//backwards movement
		availCoords.add(new Point(x, y-1));
		//backwards diagonal movemen
		availCoords.add(new Point(x-1, y-1));
		availCoords.add(new Point(x-1, y-1));
		//left movement
		availCoords.add(new Point(x-1, y));
		//right movement
		availCoords.add(new Point(x+1, y));
		
		return availCoords;
	}
	
}
