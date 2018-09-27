package application;

import java.util.LinkedList;



public class Bishop extends Piece 
{
	public Bishop(int x, int y, boolean color)
	{
		super (x,y,color);
	}
	
	public LinkedList<Integer[]> getAvailableMovements()
	{
		LinkedList<Integer[]> availCoords = new LinkedList<Integer[]>();
		
		//Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		//goes through the available y slots
		int yPos, yNeg;
		for (int i = 0; i<8 ; i++) {
			coord[0] = i;
			yPos = i - this.getX() + this.getY(); //this checks the available movement on the positive y
			yNeg = -i + this.getX() + this.getY();//this checks the available movement on the negative y
			if(i != this.getX()) {
				if (yPos <8 && yPos >= 0 ) {
				coord[1] = yPos;
				availCoords.add(coord);
				}
				if (yNeg <8 && yNeg >= 0 ) {
				coord[1] = yNeg;
				availCoords.add(coord);
				}
			}
		}
		return availCoords;
	
	}
	
}
