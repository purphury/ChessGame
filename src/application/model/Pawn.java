package application.model;

import java.util.ArrayList;

public class Pawn extends Piece
{
	private int startX, startY;
	public Pawn(int x, int y, boolean color)
	{
		super(x,y,color);
		startX = x;
		startY = y;
	}
	
	public ArrayList<Integer[]> getAvailableMovements()
	{
		ArrayList<Integer[]> availCoords = new ArrayList<>();
		
		//Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		
		//If pawn is in starting position//
		if(startX == this.getX() && startY == this.getY()) 
		{
			//Color is white//
			if(this.getColor() == false) 
			{
				coord[0] = this.getX();
				coord[1] = this.getY()+1;
				availCoords.add(coord);
				coord[1] = this.getY()+2;
				availCoords.add(coord);
			}
			//color is black//
			else {
				coord[0] = this.getX();
				coord[1] = this.getY()-1;
				availCoords.add(coord);
				coord[1] = this.getY()-2;
				availCoords.add(coord);
			}
		} 
		//if pawn is not in starting position//
		else {
			//color is white//
			if(this.getColor() == false) 
			{
				coord[0] = this.getX();
				coord[1] = this.getY()+1;
				availCoords.add(coord);
			}
			//color is black//
			else {
				coord[0] = this.getX();
				coord[1] = this.getY()-1;
				availCoords.add(coord);
			}
		}
		//Color is white; these are coordinates to attack diagonal pieces//
		if(this.getColor() == false) {
			coord[0] = this.getX()-1;
			coord[1] = this.getY()+1;
			availCoords.add(coord);
			coord[0] = this.getX()+1;
			availCoords.add(coord);
		}
		//Color is black; these are coordinates to attack diagonal pieces//
		else {
			coord[0] = this.getX()-1;
			coord[1] = this.getY()-1;
			availCoords.add(coord);
			coord[0] = this.getX()+1;
			availCoords.add(coord);
		}
		
		return availCoords;
	}
	
	
}
