package application.model;

import java.util.ArrayList;

public class Board {
	public static enum Type {
		BLACK,
		WHITE
	}
	public Type turn;
	public Piece[][] board;
	//	private Rook bRook1, bRook2, wRook1, wRook2;
	//	private Knight bKnight1, bKnight2, wKnight1, wKnight2;
	//	private Bishop bBishop1, bBishop2, wBishop1, wBishop2;
	//	private Queen bQueen, wQueen;
	//	private King bKing, wKing;
	//	private Pawn bPawn1, bPawn2, bPawn3, bPawn4, bPawn5, bPawn6, bPawn7, bPawn8, wPawn1, wPawn2, wPawn3, wPawn4, wPawn5, wPawn6, wPawn7, wPawn8; 

	public Board() {
		turn = Type.WHITE;
		board = new Piece[8][8];
		for(int i = 0; i < 8; i++) {
			board[6][i] = new Pawn(6, i, Type.WHITE);
			board[1][i] = new Pawn(1, i, Type.BLACK);
		}
		board[0][0] = new Rook(Type.BLACK);
		board[0][7] = new Rook(Type.BLACK);
		board[7][0] = new Rook(Type.WHITE);
		board[7][7] = new Rook(Type.WHITE);

		board[0][1] = new Knight(Type.BLACK);
		board[0][6] = new Knight(Type.BLACK);
		board[7][1] = new Knight(Type.WHITE);
		board[7][6] = new Knight(Type.WHITE);

		board[0][2] = new Bishop(Type.BLACK);
		board[0][5] = new Bishop(Type.BLACK);
		board[7][2] = new Bishop(Type.WHITE);
		board[7][5] = new Bishop(Type.WHITE);

		board[0][3] = new Queen(Type.BLACK);
		board[7][3] = new Queen(Type.WHITE);

		board[0][4] = new King(Type.BLACK);
		board[7][4] = new King(Type.WHITE);
	}

	/**
	 * @param coord
	 * @return piece at that location
	 */
	public Piece getPiece(Coordinate coord, Type turn) {
		if(board[coord.getRowIndex()][coord.getColumnIndex()] != null)
			return isGettable(coord) ? board[coord.getRowIndex()][coord.getColumnIndex()] : null;
		return null;
	}

	
	public boolean isGettable(Coordinate coord) {
		return board[coord.getRowIndex()][coord.getColumnIndex()].getType() == turn;
	}
	/**
	 * @param row
	 * @param col
	 * @return boolean if there is a piece at  that location
	 */
	public boolean hasPiece(int row, int col) {
		return board[row][col] != null;
	}
	/**
	 * @param coord
	 * @return boolean if there is a piece at  that location
	 */
	public boolean hasPiece(Coordinate coord) {
		return board[coord.getRowIndex()][coord.getColumnIndex()] != null;
	}

	/** Moves piece at oldLoc to newLoc (removing piece at newLoc if there was one)
	 * @param oldLoc
	 * @param newLoc
	 */
	public boolean movePieces(Coordinate oldLoc, Coordinate newLoc) {
		Piece piece = board[oldLoc.getRowIndex()][oldLoc.getColumnIndex()];
		System.out.println(piece.getAvailableMovements(oldLoc.getRowIndex(), oldLoc.getColumnIndex(), this));
		if(piece.getAvailableMovements(oldLoc.getRowIndex(), oldLoc.getColumnIndex(), this).contains(newLoc)) {
			board[newLoc.getRowIndex()][newLoc.getColumnIndex()] = piece; //if new loc was occupied, the piece that was there is now deleted as there is no reference to it
			changeTurn();
			return true;
		}
		return false;
	}
	
	/** Checks if board is in Check for a color
	 * @param type Color of person that might be in Check
	 * @return
	 */
	public boolean isCheck(Type type) {
		Coordinate kingLoc = null;
		//store kingLoc
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j] instanceof King && board[i][j].getType() == type) {
					kingLoc = new Coordinate(i, j);
					break; //found, so don't check rest of board
				}
			}
		}
		//check if king can be attacked
		return isCheck(type, kingLoc);
	}

	/** Checks if board is in Check for a color given King's location
	 * @param type type Color of person that might be in check
	 * @param kingLoc
	 * @return
	 */
	public boolean isCheck(Type type, Coordinate kingLoc) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j] instanceof Piece) {
					if(board[i][j].getAvailableMovements(i, j, this).contains(kingLoc) && !board[i][j].getType().equals(type))
						return true;
				}
			}
		}
		return false;
	}
	/** Checks if board is in Checkmate for a color
	 * @param type Color of person that might be in Checkmate
	 * @return
	 */
	public boolean isCheckmate(Type type) {
		Coordinate kingLoc = null;
		//store kingLoc
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(board[i][j] instanceof King && board[i][j].getType() == type) {
					kingLoc = new Coordinate(i, j);
					break; //found, so don't check rest of board
				}
			}
		}
		//check if king can be attacked
		return isCheckmate(type, kingLoc);
	}
	/** Checks if board is in Checkmate for a color given King's location
	 * @param type Color of person that might be in Checkmate
	 * @param kingLoc
	 * @return
	 */
	public boolean isCheckmate(Type type, Coordinate kingLoc) {
		Coordinate[] kingsPosMoves = {new Coordinate(kingLoc.getRowIndex() - 1, kingLoc.getColumnIndex() - 1), new Coordinate(kingLoc.getRowIndex() - 1, kingLoc.getColumnIndex() + 1), new Coordinate(kingLoc.getRowIndex() + 1, kingLoc.getColumnIndex() - 1),new Coordinate(kingLoc.getRowIndex() + 1, kingLoc.getColumnIndex() + 1)};
		for(Coordinate posMove : kingsPosMoves) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if(board[i][j] instanceof Piece) {
						if(board[i][j].getAvailableMovements(i, j, this).contains(posMove) && !board[i][j].getType().equals(type))
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public ArrayList<Coordinate> getMoves(Coordinate coord){
		int r = coord.getRowIndex(), c = coord.getColumnIndex();
		if(this.board[r][c] != null)
			return this.board[r][c].getAvailableMovements(r, c, this);
		else
			return null;
	}
	public void changeTurn() {
		turn = turn == Type.BLACK ? Type.WHITE : Type.BLACK;
	}
}
