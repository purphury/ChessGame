package application.model;

import java.util.ArrayList;
import application.model.Board.Type;

/**
 * This is a class representation of a King piece
 * 
 * @author Chris Crabtree
 *	UTSA Application Programming CS3443 Fall 2018
 */

public class Pawn extends Piece {
	private int startR, startC;
	private enum IsAttack{NO, YES};
	public boolean justDidDoubleMove;
	private boolean promotion;

	public Pawn(int r, int c, Type color) {
		super(color, 10);
		promotion = false;
		startR = r;
		startC = c;
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#copyPiece(application.model.Piece)
	 */
	public Piece copyPiece(Piece piece) {
		Pawn newPawn = new Pawn(((Pawn)piece).getStartR(), ((Pawn)piece).getStartC(),piece.getType());
		newPawn.setPromotion(((Pawn)piece).isPromotion());
		newPawn.setJustDidDoubleMove(((Pawn)piece).isJustDidDoubleMove());
		newPawn.setHasMoved(piece.getHasMoved());
		return newPawn;
	}

	/**
	 * @return
	 */
	public boolean isPromotion() {
		return promotion;
	}

	/**
	 * @param promotion
	 */
	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#getStrength(int, int)
	 */
	public double getStrength(int r, int c) {
		return (this.getType() == Type.WHITE ? 10 + StrengthBoard.PawnStrengthBoard[r][c] : -10 - StrengthBoard.PawnStrengthBoard[8-r-1][c]);
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#getStrengthWOStrategy(int, int)
	 */
	public double getStrengthWOStrategy(int r, int c) {
		return (this.getType() == Type.WHITE ? 10 : -10 );
	}
	
	/* (non-Javadoc)
	 * @see application.model.Piece#getAvailableMovements(int, int, application.model.Board)
	 */
	public ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board) {
		ArrayList<Coordinate> availCoords = new ArrayList<>();
		// color is white//
		if (this.getType() == Type.WHITE ) {
			addMovement(availCoords, r - 1, c, board, IsAttack.NO);
			addMovement(availCoords, r - 1, c - 1, board, IsAttack.YES);		
			addMovement(availCoords, r - 1, c + 1, board, IsAttack.YES);
			addEnPassant(availCoords, r, c+1, board, Type.WHITE);		
			addEnPassant(availCoords, r, c-1, board, Type.WHITE);
			//if(board.get)

		}
		// color is black//
		if (this.getType() == Type.BLACK ) {
			addMovement(availCoords, r + 1, c, board, IsAttack.NO);
			addMovement(availCoords, r + 1, c - 1, board, IsAttack.YES);		
			addMovement(availCoords, r + 1, c + 1, board, IsAttack.YES);
			addEnPassant(availCoords, r, c+1, board, Type.BLACK);		
			addEnPassant(availCoords, r, c-1, board, Type.BLACK);
		}			
		// if pawn is in starting position
		if (startR == r && startC == c) {			
			// color is white//
			if(this.getType() == Type.WHITE && !board.hasPiece(new Coordinate(r-1,c))) 
				addMovement(availCoords, r - 2, c, board, IsAttack.NO);
				
			// color is black//
			if(this.getType() == Type.BLACK && !board.hasPiece(new Coordinate(r+1,c))) 
				addMovement(availCoords, r + 2, c, board, IsAttack.NO);	
			

		}		
		return availCoords;
	}

	/**
	 * Adds location to move set if valid
	 * @param availCoords
	 * @param r
	 * @param c
	 * @param board
	 * @param attackStatus
	 */
	public void addMovement(ArrayList<Coordinate> availCoords,int r, int c, Board board,IsAttack attackStatus) {		
		if(boundsChecker(r, c)) { //the location is inbounds
			if(board.hasPiece(new Coordinate(r, c))) { //a piece is on this location
				if(attackStatus == IsAttack.YES) { //its an attack move
					if(board.getPiece(new Coordinate(r, c)).getType() == this.otherType()) //its an enemy piece
						availCoords.add(new Coordinate(r, c));
				}
			}
			else if(attackStatus == IsAttack.NO)  //This location is a free space	
				availCoords.add(new Coordinate(r, c));
		}
	}
	
	/**
	 * @param availCoords
	 * @param r
	 * @param c
	 * @param board
	 * @param type
	 */
	public void addEnPassant(ArrayList<Coordinate> availCoords,int r, int c, Board board, Type type) {
		if(boundsChecker(r,c)) {
			if(board.hasPiece(r, c)) {
				Piece pieceRight = board.getPiece(r, c);
				if(pieceRight instanceof Pawn && pieceRight.getType() != type
												&&((Pawn)pieceRight).justDidDoubleMove) {
					if(type == Type.WHITE)
						availCoords.add(new Coordinate(r-1,c));
					if(type == Type.BLACK)
						availCoords.add(new Coordinate(r+1,c));
				}
			}
		}
	}
	
	/**
	 * @return
	 */
	public int getStartR() {
		return startR;
	}

	/**
	 * @param startR
	 */
	public void setStartR(int startR) {
		this.startR = startR;
	}

	/**
	 * @return
	 */
	public int getStartC() {
		return startC;
	}

	/**
	 * @param startC
	 */
	public void setStartC(int startC) {
		this.startC = startC;
	}

	/**
	 * @return
	 */
	public boolean isJustDidDoubleMove() {
		return justDidDoubleMove;
	}

	/**
	 * @param justDidDoubleMove
	 */
	public void setJustDidDoubleMove(boolean justDidDoubleMove) {
		this.justDidDoubleMove = justDidDoubleMove;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Pawn";
	}
	public String toString2() {
		return "p";
	}
	
}
