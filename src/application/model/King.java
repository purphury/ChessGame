package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class King extends Piece {
	public boolean castelingAvelaible;
	public King(Type color) {
		super(color);
		castelingAvelaible = false;
	}

	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		//first number is the vertical direction, the second is the horizontal direction
		addMovement(availCoords, r - 1, c, board);// South direction
		addMovement(availCoords, r, c - 1, board);// West direction
		addMovement(availCoords, r + 1, c, board);// North direction
		addMovement(availCoords, r, c + 1, board);// East direction
		addMovement(availCoords, r - 1, c - 1, board);// Northwest direction
		addMovement(availCoords, r - 1, c + 1, board);// Northeast direction
		addMovement(availCoords, r + 1, c - 1, board);// Southwest direction
		addMovement(availCoords, r + 1, c + 1, board);// Southeast direction}

		//checks for casteling is allowed and adds it to avail movements
		if (getType() == Type.WHITE && board.whiteEverChecked == false && getHasMoved() == false && (board.getPiece(7,0).toString().equals("Rook") || board.getPiece(7,7).toString().equals("Rook"))) {
			
			for(int i = 3; i < 0; i--) {
				if (board.hasPiece(7,i) == false) {
					castelingAvelaible = true;
				}
				else
					castelingAvelaible = false;
			}
			if (castelingAvelaible == true) {
				addMovement(availCoords, r, c - 2, board);// West direction
				addMovement(availCoords, r, c - 3, board);// West direction
				addMovement(availCoords, r, c - 4, board);// West direction
			}
			
			for(int i = 5; i < 7; i++) {
				if (board.hasPiece(7,i) == false) {
					castelingAvelaible = true;
				}
				else
					castelingAvelaible = false;
			}

			if (castelingAvelaible == true) {
				addMovement(availCoords, r, c + 2, board);// east direction
				addMovement(availCoords, r, c + 3, board);// east direction
				addMovement(availCoords, r, c + 4, board);// east direction
			}
			
		}
		else if (getType() == Type.BLACK && board.blackEverChecked == false && getHasMoved() == false && (board.getPiece(0,0).toString().equals("Rook") || board.getPiece(0,7).toString().equals("Rook"))) {
			
			for(int i = 3; i < 0; i--) {
				if (board.hasPiece(0,i) == false) {
					castelingAvelaible = true;
				}
				else
					castelingAvelaible = false;
			}
			if (castelingAvelaible == true) {
				addMovement(availCoords, r, c - 2, board);// West direction
				addMovement(availCoords, r, c - 3, board);// West direction
				addMovement(availCoords, r, c - 4, board);// West direction
			}
			
			for(int i = 5; i < 7; i++) {
				if (board.hasPiece(0,i) == false) {
					castelingAvelaible = true;
				}
				else
					castelingAvelaible = false;
			}
			if (castelingAvelaible == true) {
				addMovement(availCoords, r, c + 2, board);// east direction
				addMovement(availCoords, r, c + 3, board);// east direction
				addMovement(availCoords, r, c + 4, board);// east direction
			}
			
		}
	
		return availCoords;
	}

	public boolean isCastelingAvelaible() {
		return castelingAvelaible;
	}

	public void setCastelingAvelaible(boolean castelingAvelaible) {
		this.castelingAvelaible = castelingAvelaible;
	}

	public void addMovement(ArrayList<Coordinate> availCoords, int r, int c, Board board) {
		if (boundsChecker(r, c)) {
			if (board.hasPiece(new Coordinate(r, c))) {// a piece is on this location
				if (board.getPiece(new Coordinate(r, c)).getType() == this.otherType())// its an enemy piece
					availCoords.add(new Coordinate(r, c));
			} 
			else // This location is a free space
				availCoords.add(new Coordinate(r, c));
		}
	}
	

}