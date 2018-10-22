package application.model;

import java.util.ArrayList;




public class King extends Piece
{
	public King(int x, int y, boolean color) 
	{
		super(x, y, color);
	}
	
	public ArrayList<Integer[]> getAvailableMovements() 
	{
		ArrayList<Integer[]> availCoords = new ArrayList<>();

		// Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		//forward movement
		coord[0] = this.getX();
		coord[1] = this.getY()+1;
		availCoords.add(coord);
		//diagonal forward movement
		coord[0] = this.getX()-1;
		coord[1] = this.getY()+1;
		availCoords.add(coord);
		coord[0] = this.getX()+1;
		availCoords.add(coord);
		//backwards movement
		coord[0] = this.getX();
		coord[1] = this.getY()-1;
		availCoords.add(coord);
		//backwards diagonal movement
		coord[0] = this.getX()-1;
		coord[1] = this.getY()-1;
		availCoords.add(coord);
		coord[0] = this.getX()-1;
		availCoords.add(coord);
		//left movement
		coord[0] = this.getX()-1;
		coord[1] = this.getY();
		availCoords.add(coord);
		//right movement
		coord[0] = this.getX()+1;
		coord[1] = this.getY();
		availCoords.add(coord);
		
		return availCoords;
		
	
	}
	
}
