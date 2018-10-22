package application.model;

import java.util.ArrayList;

public class Queen extends Piece {
	public Queen(int x, int y, boolean color) {
		super(x, y, color);
	}

	public ArrayList<Integer[]> getAvailableMovements() {
		ArrayList<Integer[]> availCoords = new ArrayList<>();

		// Coordinates to add to availCoords//
		Integer[] coord = new Integer[2];
		// diagonal motion
		// goes through the available y slots
		int yPos, yNeg;
		for (int i = 0; i < 8; i++) {
			coord[0] = i;
			yPos = i - this.getX() + this.getY(); // this checks the available movement on the positive y diagonally
			yNeg = -i + this.getX() + this.getY();// this checks the available movement on the negative y diagonally
			if (i != this.getX()) {
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
		coord[0] = this.getX();
		for (int i = 0; i < 8; i++) {
			if (i != this.getY()) {
				coord[1] = i;
				availCoords.add(coord);
			}
		}
		// goes through the available y slots
		coord[1] = this.getY();
		for (int i = 0; i < 8; i++) {

			if (i != this.getX()) {
				coord[0] = i;
				availCoords.add(coord);
			}
		}

		return availCoords;

	}

}
