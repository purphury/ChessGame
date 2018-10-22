package application.model;

import java.util.ArrayList;

public class Knight extends Piece 
{

	public Knight(int x, int y, boolean color) 
	{
		super(x, y, color);
	}

	public ArrayList<Integer[]> getAvailableMovements() 
	{
		ArrayList<Integer[]> availCoords = new ArrayList<>();

		// Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		// two squares away horizontally and one square vertically
		coord[0] = this.getX()+1;
		coord[1] = this.getY()+2;
		availCoords.add(coord);
		coord[0] = this.getX()-1;
		coord[1] = this.getY()+2;
		availCoords.add(coord);
		coord[0] = this.getX()+1;
		coord[1] = this.getY()-2;
		availCoords.add(coord);
		coord[0] = this.getX()-1;
		coord[1] = this.getY()-2;
		availCoords.add(coord);
		//two squares vertically and one square horizontally
		coord[0] = this.getX()+2;
		coord[1] = this.getY()+1;
		availCoords.add(coord);
		coord[0] = this.getX()+2;
		coord[1] = this.getY()-1;
		availCoords.add(coord);
		coord[0] = this.getX()-2;
		coord[1] = this.getY()-1;
		availCoords.add(coord);
		coord[0] = this.getX()-2;
		coord[1] = this.getY()+1;
		availCoords.add(coord);
		
		return availCoords;
		
		
	}
	
}
