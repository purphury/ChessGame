package application.model;

import java.awt.Point;
import java.util.ArrayList;

import application.model.Board.Type;

public class Pawn extends Piece
{
	private int startX, startY;
	public Pawn(int x, int y, Type color)
	{
		super(color);
		startX = x;
		startY = y;
	}
	public ArrayList<Point> getAvailableMovements(int x, int y)
	{
		ArrayList<Point> availCoords = new ArrayList<>();
		//If pawn is in starting position//
		if(startX == x && startY == y) 
		{
			//Color is white//
			if(this.getType() == Type.WHITE) 
			{
				availCoords.add(new Point(x, y + 1));
				availCoords.add(new Point(x, y + 2));
			}
			//color is black//
			else {
				availCoords.add(new Point(x, y - 1));
				availCoords.add(new Point(x, y - 2));
			}
		} 
		//if pawn is not in starting position//
		else {
			//color is white//
			if(this.getType() == Type.WHITE) 
				availCoords.add(new Point(x, y + 1));
			//color is black//
			else
				availCoords.add(new Point(x, y - 1));
		}
		//Color is white; these are coordinates to attack diagonal pieces//
		if(this.getType() == Type.WHITE) {
			availCoords.add(new Point(x - 1, y + 1));
			availCoords.add(new Point(x + 1, y + 1));
		}
		//Color is black; these are coordinates to attack diagonal pieces//
		else {
			availCoords.add(new Point(x - 1, y - 1));
			availCoords.add(new Point(x + 1, y - 1));
		}
		
		return availCoords;
	}
	
	
}
