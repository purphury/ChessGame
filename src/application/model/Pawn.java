package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Pawn extends Piece
{
	private int startX, startY;
	private String type;
	public Pawn(int x, int y, Type color)
	{
		super(color);
		startX = x;
		startY = y;
		type = "Pawn";
	}
	public ArrayList<Integer[]> getAvailableMovements(int x, int y)
	{
		ArrayList<Integer[]> availCoords = new ArrayList<>();
		
		//Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		
		//If pawn is in starting position//
		if(startX == x && startY == y) 
		{
			//Color is white//
			if(this.getType() == Type.WHITE) 
			{
				coord[0] = x;
				coord[1] = y+1;
				availCoords.add(coord);
				coord[1] = y+2;
				availCoords.add(coord);
			}
			//color is black//
			else {
				coord[0] = x;
				coord[1] = y-1;
				availCoords.add(coord);
				coord[1] = y-2;
				availCoords.add(coord);
			}
		} 
		//if pawn is not in starting position//
		else {
			//color is white//
			if(this.getType() == Type.WHITE) 
			{
				coord[0] = x;
				coord[1] = y+1;
				availCoords.add(coord);
			}
			//color is black//
			else {
				coord[0] = x;
				coord[1] = y-1;
				availCoords.add(coord);
			}
		}
		//Color is white; these are coordinates to attack diagonal pieces//
		if(this.getType() == Type.WHITE) {
			coord[0] = x-1;
			coord[1] = y+1;
			availCoords.add(coord);
			coord[0] = x+1;
			availCoords.add(coord);
		}
		//Color is black; these are coordinates to attack diagonal pieces//
		else {
			coord[0] = x-1;
			coord[1] = y-1;
			availCoords.add(coord);
			coord[0] = x+1;
			availCoords.add(coord);
		}
		
		return availCoords;
	}
	
	
}
