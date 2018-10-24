package application.model;

import java.awt.Point;
import java.util.ArrayList;

import application.model.Board.Type;

public class Queen extends Piece {
	public Queen(Type color) {
		super(color);
	}

	public ArrayList<Point> getAvailableMovements(int x, int y) {
		ArrayList<Point> availCoords = new ArrayList<>();
		// diagonal motion
		// goes through the available y slots
		int yPos, yNeg;
		for (int i = 0; i < 8; i++) {
			yPos = i - x + y; // this checks the available movement on the positive y diagonally
			yNeg = -i + x + y;// this checks the available movement on the negative y diagonally
			if (i != x) {
				if (yPos < 8 && yPos >= 0) {
					availCoords.add(new Point(i, yPos));
				}
				if (yNeg < 8 && yNeg >= 0) {
					availCoords.add(new Point(i, yNeg));
				}
			}
		}
		// cross movement
		// goes through the available y slots
		for (int i = 0; i < 8; i++) {
			if (i != y) {
				availCoords.add(new Point(x, i));
			}
		}
		// goes through the available y slots
		for (int i = 0; i < 8; i++) {

			if (i != x) {
				availCoords.add(new Point(i, y));
			}
		}

		return availCoords;
	}
}
