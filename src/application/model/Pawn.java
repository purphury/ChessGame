 package application.model;

import java.awt.Point;
import java.util.ArrayList;

import application.model.Board.Type;

public class Pawn extends Piece
{
	private int startR, startC;
	public Pawn(int r, int c, Type color)
	{
		super(color);
		startR = r;
		startC = c;
	}
	public ArrayList<Point> getAvailableMovements(int r, int c, Board board)
	{
		ArrayList<Point> availCoords = new ArrayList<>();
		//If pawn is in starting position//
		if(startR == r && startC == c) 
		{
			//Color is white//
			if(this.getType() == Type.WHITE) 
			{
				availCoords.add(new Point(r + 1, c));
				availCoords.add(new Point(r + 2, c));
			}
			//color is black//
			else {
				availCoords.add(new Point(r - 1, c));
				availCoords.add(new Point(r - 2, c));
			}
		} 
		//if pawn is not in starting position//
		else {
			//color is white//
			if(this.getType() == Type.WHITE && board.getPiece(r - 1, c) == null && r - 1 >= 0) 
				availCoords.add(new Point(r - 1, c));
			//color is black//
			else if(board.getPiece(r + 1, c) == null && r + 1 <= 7)
				availCoords.add(new Point(r + 1, c));
		}
		//Color is white; these are coordinates to attack diagonal pieces//
		if(this.getType() == Type.WHITE) {
			if(board.getPiece(r - 1, c - 1) != null && r - 1 >= 0 && c - 1 >= 0)
				availCoords.add(new Point(r - 1, c - 1));
			if(board.getPiece(r - 1, c + 1) != null && r - 1 >= 0 && c + 1 <= 7)
				availCoords.add(new Point(r - 1, c + 1));
		}
		//Color is black; these are coordinates to attack diagonal pieces//
		else {
			if(board.getPiece(r + 1, c - 1) != null && r + 1 <= 7 && c - 1 >= 0)
				availCoords.add(new Point(r + 1, c - 1));
			if(board.getPiece(r + 1, c + 1) != null && r + 1 <= 7 && c + 1 <= 7)
				availCoords.add(new Point(r + 1, c + 1));
		}
		
		return availCoords;
	}
	
	
}
