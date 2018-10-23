package application.model;

import java.awt.Color;

public class Board {
	public static enum Type {
		BLACK,
		WHITE
	}
	public Piece[][] board;
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
	}
}
