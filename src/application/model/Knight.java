package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Knight extends Piece 
{

	public Knight(Type color) 
	{
		super(color);
	}

	@Override
	public ArrayList<Integer[]> getAvailableMovements(int x, int y) {
		ArrayList<Integer[]> availCoords = new ArrayList<>();

		// Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		// two squares away horizontally and one square vertically
		coord[0] = x+1;
		coord[1] = y+2;
		availCoords.add(coord);
		coord[0] = x-1;
		coord[1] = y+2;
		availCoords.add(coord);
		coord[0] = x+1;
		coord[1] = y-2;
		availCoords.add(coord);
		coord[0] = x-1;
		coord[1] = y-2;
		availCoords.add(coord);
		//two squares vertically and one square horizontally
		coord[0] = x+2;
		coord[1] = y+1;
		availCoords.add(coord);
		coord[0] = x+2;
		coord[1] = y-1;
		availCoords.add(coord);
		coord[0] = x-2;
		coord[1] = y-1;
		availCoords.add(coord);
		coord[0] = x-2;
		coord[1] = y+1;
		availCoords.add(coord);
		
		return availCoords;
	}

}
