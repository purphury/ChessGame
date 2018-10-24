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

	public ArrayList<Point> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Point> availCoords = new ArrayList<>();
		
		if (r - 1 >= 0) 
			availCoords.add(new Point(r - 1, c));
		if (c + 1 <= 7) 
			availCoords.add(new Point(r, c + 1));
		if (r + 1 <= 7) 
			availCoords.add(new Point(r + 1, c));
		if (c - 1 >= 0) 
			availCoords.add(new Point(r, c - 1));
		if (r - 1 >= 0 && c - 1 >= 0) 
			availCoords.add(new Point(r - 1, c - 1));
		if (r - 1 >= 0 && c + 1 <= 7) 
			availCoords.add(new Point(r, c + 1));
		if (r + 1 <= 7 && c - 1 >= 0) 
			availCoords.add(new Point(r + 1, c - 1));
		if (r + 1 <= 7 && c + 1 <= 7) 
			availCoords.add(new Point(r + 1, c - 1));
		
		return availCoords;
	}
}