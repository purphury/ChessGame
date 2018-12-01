package application.model;

import java.util.ArrayList;
import application.model.Board.Type;

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

	public boolean isPromotion() {
		return promotion;
	}

	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}
	
	public double getStrength(int r, int c) {
		System.out.println(this.getType());
		return (this.getType() == Type.WHITE ? 10 + PawnStrengthBoard.PawnStrengthBoard[r][c] : -10 - PawnStrengthBoard.PawnStrengthBoard[8-r-1][c]);
	}
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
	
	
	
}
