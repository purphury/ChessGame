package application.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Rook extends Piece 
{
	public Rook(int x, int y, boolean color)
	{
		super (x,y,color);
	}
	
	public ArrayList<Integer[]> getAvailableMovements()
	{
		ArrayList<Integer[]> availCoords = new ArrayList<>();
		
		//Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		//goes through the available y slots
		coord[0] = this.getX();
		for (int i = 0; i<8 ; i++)
		{
			if (i != this.getY())
			{
				coord[1] = i;
				availCoords.add(coord);
			}
		}
		//goes through the available y slots
		coord[1] = this.getY();
		for (int i = 0; i<8 ; i++)
		{

			if (i != this.getX()) {
				coord[0] = i;
				availCoords.add(coord);
			}
		}
		return availCoords;
	
	}
	
}
