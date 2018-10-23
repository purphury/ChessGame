package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Queen extends Piece {
	public Queen(Type color) {
		super(color);
	}

	public ArrayList<Integer[]> getAvailableMovements(int x, int y) {
		ArrayList<Integer[]> availCoords = new ArrayList<>();

		// Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		// diagonal motion
		// goes through the available y slots
		int yPos, yNeg;
		for (int i = 0; i < 8; i++) {
			coord[0] = i;
			yPos = i - x + y; // this checks the available movement on the positive y diagonally
			yNeg = -i + x + y;// this checks the available movement on the negative y diagonally
			if (i != x) {
				if (yPos < 8 && yPos >= 0) {
					coord[1] = yPos;
					availCoords.add(coord);
				}
				if (yNeg < 8 && yNeg >= 0) {
					coord[1] = yNeg;
					availCoords.add(coord);
				}
			}
		}
		// cross movement
		// goes through the available y slots
		coord[0] = x;
		for (int i = 0; i < 8; i++) {
			if (i != y) {
				coord[1] = i;
				availCoords.add(coord);
			}
		}
		// goes through the available y slots
		coord[1] = y;
		for (int i = 0; i < 8; i++) {

			if (i != x) {
				coord[0] = i;
				availCoords.add(coord);
			}
		}

		return availCoords;
	}
}
