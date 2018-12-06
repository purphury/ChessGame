package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

/**
 * This is a class representation of a King piece
 * 
 * @author Chris Crabtree, Daniel Nix, Jonathan Balraj, Majd Hamoudah
 *	UTSA Application Programming CS3443 Fall 2018
 */
public class King extends Piece {
	public boolean castlingAvailableRight;
	public boolean castlingAvailableLeft;
	public ArrayList<Coordinate> castlingMove;


	/**
	 * @return castlingMove
	 */
	public ArrayList<Coordinate> getCastlingMove() {
		return castlingMove;
	}

	/**
	 * @param castlingMove
	 */
	public void setCastlingMove(ArrayList<Coordinate> castlingMove) {
		this.castlingMove = castlingMove;
	}

	public King(Type color) {
		super(color, 900);
		castlingAvailableRight = false;
		castlingAvailableLeft = false;
		castlingMove = new ArrayList<Coordinate>();
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#copyPiece(application.model.Piece)
	 */
	public Piece copyPiece(Piece piece) {
		King newKing = new King(piece.getType());
		newKing.setCastlingAvailableLeft(((King)piece).isCastlingAvailableLeft());
		newKing.setCastlingAvailableRight(((King) piece).isCastlingAvailableRight());
		newKing.setCastlingMove(((King)piece).getCastlingMove());
		newKing.setHasMoved(((King)piece).getHasMoved());
		return newKing;
	}

	/* (non-Javadoc)
	 * @see application.model.Piece#getStrength(int, int)
	 */
	public double getStrength(int r, int c) {
		return (this.getType() == Type.WHITE ? 900 + StrengthBoard.KingStrengthBoard[r][c] : -900 - StrengthBoard.KingStrengthBoard[8-r-1][c]);
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#getStrengthWOStrategy(int, int)
	 */
	public double getStrengthWOStrategy(int r, int c) {
		return (this.getType() == Type.WHITE ? 900 : -900);
	}


	/* (non-Javadoc)
	 * @see application.model.Piece#getAvailableMovements(int, int, application.model.Board)
	 */
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

		//checks for castling is allowed and adds it to avail movements
		if (getType() == Type.WHITE && board.whiteEverChecked == false && getHasMoved() == false) {

			if (board.hasPiece(7,0) && board.getPiece(7,0).toString().equals("Rook")) {
				if(!board.hasPiece(7,1) && !board.hasPiece(7,2) && !board.hasPiece(7,3)){
					addMovement(availCoords, r, c - 2, board);// west direction
					addMovement(availCoords, r, c - 3, board);// west direction
					//addMovement(availCoords, r, c - 4, board);// west direction
					this.castlingMove.add(new Coordinate(r, c + 2));
					this.castlingMove.add(new Coordinate(r, c + 3));
					this.castlingMove.add(new Coordinate(r, c + 4));
				}
			}
			if(board.hasPiece(7,7) && board.getPiece(7,7).toString().equals("Rook")) {
				if(!board.hasPiece(7,5) && !board.hasPiece(7,6)){
					addMovement(availCoords, r, c + 2, board);// east direction
					this.castlingMove.add(new Coordinate(r, c + 2));
				}
			}

			

		}
		else if (getType() == Type.BLACK && board.blackEverChecked == false && getHasMoved() == false) {

			if (board.hasPiece(0,0) && board.getPiece(0,0).toString().equals("Rook")) {

				if(!board.hasPiece(0,1) && !board.hasPiece(0,2) && !board.hasPiece(0,3)){
					addMovement(availCoords, r, c - 2, board);// west direction
					addMovement(availCoords, r, c - 3, board);// west direction
					//addMovement(availCoords, r, c - 4, board);// west direction
					this.castlingMove.add(new Coordinate(r, c + 2));
					this.castlingMove.add(new Coordinate(r, c + 3));
					this.castlingMove.add(new Coordinate(r, c + 4));
				}
			}
			if(board.hasPiece(0,7) && board.getPiece(0,7).toString().equals("Rook")) {
				if(!board.hasPiece(0,5) && !board.hasPiece(0,6)){
					addMovement(availCoords, r, c + 2, board);// east direction
					this.castlingMove.add(new Coordinate(r, c + 2));
				}
			}
		}

		return availCoords;
	}



	/**
	 * @return castlingAvailableRight
	 */
	public boolean isCastlingAvailableRight() {
		return castlingAvailableRight;
	}

	/**
	 * @param castlingAvailableRight
	 */
	public void setCastlingAvailableRight(boolean castlingAvailableRight) {
		this.castlingAvailableRight = castlingAvailableRight;
	}

	/**
	 * @return castlingAvailableLeft
	 */
	public boolean isCastlingAvailableLeft() {
		return castlingAvailableLeft;
	}

	/**
	 * @param castlingAvailableLeft
	 */
	public void setCastlingAvailableLeft(boolean castlingAvailableLeft) {
		this.castlingAvailableLeft = castlingAvailableLeft;
	}

	/**
	 * Adds a move to the availCoords collection
	 * @param availCoords
	 * @param r
	 * @param c
	 * @param board
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "King";
	}
	
	public String toString2() {
		return "K";
	}


}