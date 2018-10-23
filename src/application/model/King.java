package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class King extends Piece
{
	public King(Type color) 
	{
		super(color);
	}
	
	public ArrayList<Integer[]> getAvailableMovements(int x, int y) 
	{
		ArrayList<Integer[]> availCoords = new ArrayList<>();

		// Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		//forward movement
		coord[0] = x;
		coord[1] = y+1;
		availCoords.add(coord);
		//diagonal forward movement
		coord[0] = x-1;
		coord[1] = y+1;
		availCoords.add(coord);
		coord[0] = x+1;
		availCoords.add(coord);
		//backwards movement
		coord[0] = x;
		coord[1] = y-1;
		availCoords.add(coord);
		//backwards diagonal movement
		coord[0] = x-1;
		coord[1] = y-1;
		availCoords.add(coord);
		coord[0] = x-1;
		availCoords.add(coord);
		//left movement
		coord[0] = x-1;
		coord[1] = y;
		availCoords.add(coord);
		//right movement
		coord[0] = x+1;
		coord[1] = y;
		availCoords.add(coord);
		
		return availCoords;
	}
	
}
