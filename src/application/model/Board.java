package application.model;

import java.util.ArrayList;

import application.controller.BoardController;

public class Board {
	private String whiteName;
	private String blackName;
	public static enum Type {
		BLACK,
		WHITE
	}
	private Type turn;
	private Piece[][] board;

	public Board(String whiteName, String blackName) {
		this.whiteName = whiteName;
		this.blackName = blackName;
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
	public String getWhiteName() {
		return whiteName;
	}
	public void setWhiteName(String whiteName) {
		this.whiteName = whiteName;
	}
	public String getBlackName() {
		return blackName;
	}
	public void setBlackName(String blackName) {
		this.blackName = blackName;
	}
	public Type getTurn() {
		return turn;
	}
	public void setTurn(Type turn) {
		this.turn = turn;
	}
	public Piece[][] getBoard() {
		return board;
	}
	public void setBoard(Piece[][] board) {
		this.board = board;
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
	/**
	 * @param r row
	 * @param c column
	 * @param turn
	 * @return piece at that location
	 */
	public Piece getPiece(int r, int c, Type turn) {
		return getPiece(new Coordinate(r, c), turn);
	}

	public Piece getPiece(Coordinate coord) {
		return board[coord.getRowIndex()][coord.getColumnIndex()];
	}
	
	public boolean isGettable(Coordinate coord) {
		Piece piece = board[coord.getRowIndex()][coord.getColumnIndex()];
		if(piece == null)
				return false;
		return piece.getType() == turn;
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
//		System.out.println("Old Location Row/Col: "+ (oldLoc.getRowIndex()-1) + " " + (oldLoc.getColumnIndex()-1) + "\n"
//			+ "New Location Row/Col: " + (newLoc.getRowIndex()-1) + " " + (newLoc.getColumnIndex()-1) + "\n" + piece.getAvailableMovements(oldLoc.getRowIndex(), oldLoc.getColumnIndex(), this));
		for(Coordinate c : piece.getAvailableMovements(oldLoc.getRowIndex(), oldLoc.getColumnIndex(), this))
			if(c.equals(newLoc)) {
				if(!piece.getHasMoved()) 
					piece.setHasMoved(true);
				board[newLoc.getRowIndex()][newLoc.getColumnIndex()] = piece; //if new loc was occupied, the piece that was there is now deleted as there is no reference to it
				board[oldLoc.getRowIndex()][oldLoc.getColumnIndex()] = null;  
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
	
	/** Gets AvailableMoves from the piece at location specified
	 * @param coord
	 * @return
	 */
	public ArrayList<Coordinate> getMoves(Coordinate coord){
		int r = coord.getRowIndex(), c = coord.getColumnIndex();
		if(this.board[r][c] != null)
			return this.board[r][c].getAvailableMovements(r, c, this);
		else
			return new ArrayList<Coordinate>();
	}
	public void changeTurn() {
		turn = turn == Type.BLACK ? Type.WHITE : Type.BLACK;
		
	}
}
