package application.model;

import java.awt.Point;
import java.util.ArrayList;

import application.model.Board.Type;

public class Rook extends Piece 
{
	public Rook(Type color)
	{
		super (color);
	}
	
	public ArrayList<Point> getAvailableMovements(int x, int y)
	{
		ArrayList<Point> availCoords = new ArrayList<>();
		//goes through the available y slots
		for (int i = 0; i<8 ; i++)
		{
			if (i != y)
				availCoords.add(new Point(x, i));
			if (i != x)
				availCoords.add(new Point(i, y));
		}
		return availCoords;
	}
}
