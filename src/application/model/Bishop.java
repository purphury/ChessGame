package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Bishop extends Piece {
	public Bishop(Type color) {
		super(color);
	}

	@Override
	public ArrayList<Integer[]> getAvailableMovements(int x, int y) {
		ArrayList<Integer[]> availCoords = new ArrayList<>();
		//Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		//goes through the available y slots
		int yPos, yNeg;
		for (int i = 0; i < 8 ; i++) {
			coord[0] = i;
			yPos = i - x + y; //this checks the available movement on the positive y
			yNeg = -i + x + y;//this checks the available movement on the negative y
			if(i != x) {
				if (yPos <8 && yPos >= 0 ) {
				coord[1] = yPos;
				availCoords.add(coord);
				}
				if (yNeg < 8 && yNeg >= 0 ) {
				coord[1] = yNeg;
				availCoords.add(coord);
				}
			}
		}
		return availCoords;
	
	}

	
}
