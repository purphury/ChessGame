package application.model;

public class Board {
	public static enum Type {
		BLACK,
		WHITE
	}

	public Piece[][] board;
	//	private Rook bRook1, bRook2, wRook1, wRook2;
	//	private Knight bKnight1, bKnight2, wKnight1, wKnight2;
	//	private Bishop bBishop1, bBishop2, wBishop1, wBishop2;
	//	private Queen bQueen, wQueen;
	//	private King bKing, wKing;
	//	private Pawn bPawn1, bPawn2, bPawn3, bPawn4, bPawn5, bPawn6, bPawn7, bPawn8, wPawn1, wPawn2, wPawn3, wPawn4, wPawn5, wPawn6, wPawn7, wPawn8; 

	public Board() {
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
		board[0][7] = new Knight(Type.BLACK);
		board[7][1] = new Knight(Type.WHITE);
		board[7][7] = new Knight(Type.WHITE);

		board[0][2] = new Bishop(Type.BLACK);
		board[0][5] = new Bishop(Type.BLACK);
		board[7][2] = new Bishop(Type.WHITE);
		board[7][5] = new Bishop(Type.WHITE);

		board[0][3] = new Queen(Type.BLACK);
		board[7][3] = new Queen(Type.WHITE);

		board[0][4] = new King(Type.BLACK);
		board[7][4] = new King(Type.WHITE);
	}

	public Piece getPiece(int row, int col) {
		return board[row][col];
	}

	public boolean hasPiece(int row, int col) {
		return board[row][col] != null;
	}
	//TODO: 
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
}
