package application.model;

import java.awt.Point;
import java.util.ArrayList;

import application.model.Board.Type;

public class Bishop extends Piece {
	public Bishop(Type color) {
		super(color);
	}

	@Override
	public ArrayList<Point> getAvailableMovements(int x, int y) {
		ArrayList<Point> availCoords = new ArrayList<>();
		//goes through the available y slots
		int yPos, yNeg;
		for (int i = 0; i<8 ; i++) {
			yPos = i - x + y; //this checks the available movement on the positive y
			yNeg = -i + x + y;//this checks the available movement on the negative y
			if(i != x) {
				if (yPos <8 && yPos >= 0 ) {
				availCoords.add(new Point(i, yPos));
				}
				if (yNeg <8 && yNeg >= 0 ) {
				availCoords.add(new Point(i, yNeg));
				}
			}
		}
		return availCoords;
	
	}

	
}
