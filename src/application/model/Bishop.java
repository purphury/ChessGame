package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

/**
 * This is a class representation of a King piece
 * 
 * @author Chris Crabtree, Daniel Nix, Jonathan Balraj, Majd Hamoudah
 *	UTSA Application Programming CS3443 Fall 2018
 */
public class Bishop extends Piece {
	public Bishop(Type color) {
		super(color, 30);
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#copyPiece(application.model.Piece)
	 */
	public Piece copyPiece(Piece piece) {
		Bishop nB =  new Bishop(piece.getType());
		nB.setHasMoved(piece.getHasMoved());
		return nB;
	}

	/* (non-Javadoc)
	 * @see application.model.Piece#getStrength(int, int)
	 */
	public double getStrength(int r, int c) {
		return (this.getType() == Type.WHITE ? 30 + StrengthBoard.BishopStrengthBoard[r][c] : -30 - StrengthBoard.BishopStrengthBoard[8-r-1][c]);
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#getStrengthWOStrategy(int, int)
	 */
	public double getStrengthWOStrategy(int r, int c) {
		return (this.getType() == Type.WHITE ? 30  : -30 );
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#getAvailableMovements(int, int, application.model.Board)
	 */
	@Override
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		//first number is the vertical direction, the second is the horizontal direction
		//negative is up or left, positive is down or right. Zero keeps it stationary
		addMovements(availCoords, -1, -1, r, c, board);//Northwest direction
		addMovements(availCoords, -1, 1, r, c, board);//Northeast direction
		addMovements(availCoords, 1, -1, r, c, board);//Southwest direction
		addMovements(availCoords, 1, 1, r, c, board);//Southeast direction}
		return availCoords;
	}

	/**
	 * Adds locations to availCoords (if valid)
	 * @param availCoords
	 * @param rowInc
	 * @param columnInc
	 * @param r
	 * @param c
	 * @param board
	 */
	public void addMovements(ArrayList<Coordinate> availCoords, int rowInc, int columnInc, int r, int c, Board board) {
		for (int i = 1; i < 8; i++) {
			if (boundsChecker(r + i * rowInc, c + i * columnInc)) {// Checks if location is on the board
				if (board.hasPiece(new Coordinate(r + i * rowInc, c + i * columnInc))) { // A piece is on this location
					if (board.getPiece(new Coordinate(r + i * rowInc, c + i * columnInc)).getType() == this.otherType()) // Its an enemy piece
						availCoords.add(new Coordinate(r + i * rowInc, c + i * columnInc));
					break;
				} else // Location is a free space
					availCoords.add(new Coordinate(r + i * rowInc, c + i * columnInc));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Bishop";
	}
	
	public String toString2() {
		return "B";
	}
}
