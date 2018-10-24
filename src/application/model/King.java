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
		int i = 1;
		
		if ( r - i >= 0) 
			availCoords.add(new Point(r - i, c));
		if ( c + i <= 7) 
			availCoords.add(new Point(r, c + i));
		if (r + i <= 7) 
			availCoords.add(new Point(r + i, c));
		if (c - i >= 0) 
			availCoords.add(new Point(r, c - i));
		if (r - i >= 0 && c - i >= 0) 
			availCoords.add(new Point(r - i, c - i));
		if (r - i >= 0 && c + i <= 7) 
			availCoords.add(new Point(r, c + i));
		if (r + i <= 7 && c - i >= 0) 
			availCoords.add(new Point(r + i, c - i));
		if (r + i <= 7 && c + i <= 7) 
			availCoords.add(new Point(r + i, c - i));
		
		return availCoords;
	}
}