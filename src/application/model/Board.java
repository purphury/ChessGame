package application.model;

public class Board {
	public Piece[][] board;
	public Board() {
		board = new Piece[8][8];
		for(int i = 0; i < 8; i++) {
				board[6][i] = new Pawn(6, i, false);
				board[1][i] = new Pawn(1, i, true);
		}
		board[0][0] = new Rook(0, 0, true );
		board[0][7] = new Rook(0, 7, true );
		board[7][0] = new Rook(7, 0, false);
		board[7][7] = new Rook(7, 7, false);
		
		board[0][1] = new Knight(0, 1, true );
		board[0][7] = new Knight(0, 7, true );
		board[7][1] = new Knight(7, 1, false);
		board[7][7] = new Knight(7, 7, false);
		
		board[0][2] = new Bishop(0, 2, true );
		board[0][5] = new Bishop(0, 5, true );
		board[7][2] = new Bishop(7, 2, false);
		board[7][5] = new Bishop(7, 5, false);
		
		board[0][3] = new Queen(0, 3, true );
		board[7][3] = new Queen(7, 3, false);
	}
}
