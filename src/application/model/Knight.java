package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public class Knight extends Piece 
{

	public Knight(Type color) {
		super(color, 30);
	}
	
	public Piece copyPiece(Piece piece) {
		Knight nk =  new Knight(piece.getType());
		nk.setHasMoved(piece.getHasMoved());
		return nk;
	}
	

	public double getStrength(int r, int c) {
		return (this.getType() == Type.WHITE ? 30 + StrengthBoard.KnightStrengthBoard[r][c] : -30 - StrengthBoard.KnightStrengthBoard[8-r-1][c]);
	}
	
	public double getStrengthWOStrategy(int r, int c) {
		return (this.getType() == Type.WHITE ? 30 : -30 );
	}
	
	@Override
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();

		// two squares away horizontally and one square vertically		
		addMovement(availCoords, r + 1, c + 2, board);	
		addMovement(availCoords, r - 1, c + 2, board);
		addMovement(availCoords, r + 1, c - 2, board);
		addMovement(availCoords, r - 1, c - 2, board);

		//two squares vertically and one square horizontally
		addMovement(availCoords, r + 2, c + 1, board);
		addMovement(availCoords, r + 2, c - 1, board);
		addMovement(availCoords, r - 2, c - 1, board);
		addMovement(availCoords, r - 2, c + 1, board);

		return availCoords;
	}

	public void addMovement(ArrayList<Coordinate> availCoords,int r, int c, Board board) {
		if (boundsChecker(r, c)) {
			if(board.hasPiece(new Coordinate(r, c))) {//a piece is on this location
				if(board.getPiece(new Coordinate(r, c)).getType() == this.otherType()) {//its an enemy piece
					availCoords.add(new Coordinate(r, c));
					
				}
			}
			else {	//This location is a free space	
				availCoords.add(new Coordinate(r, c));
			}
		}
	}
	
	public String toString() {
		return "Knight";
	}
	
	public String toString2() {
		return "k";
	}
}
