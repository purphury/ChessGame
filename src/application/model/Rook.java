package application.model;

import java.util.ArrayList;
import java.util.LinkedList;

import application.model.Board.Type;

public class Rook extends Piece 
{
	public Rook(Type color)
	{
		super (color);
	}
	
	public ArrayList<Integer[]> getAvailableMovements(int x, int y)
	{
		ArrayList<Integer[]> availCoords = new ArrayList<>();
		
		//Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		//goes through the available y slots
		coord[0] = x;
		for (int i = 0; i<8 ; i++)
		{
			if (i != y)
			{
				coord[1] = i;
				availCoords.add(coord);
			}
		}
		//goes through the available y slots
		coord[1] = y;
		for (int i = 0; i<8 ; i++)
		{

			if (i != x) {
				coord[0] = i;
				availCoords.add(coord);
			}
		}
		return availCoords;
	}
}
