package application.model;

public class Board {
	 private boolean[][] board;
	
	public Board() {
		board = new boolean[8][8];
		boolean color = true; //true is black and false is white
		// for would be coloring the board
		for (int i = 0; i<8; i++) {
			for (int j= 0; j<8 ; j++) {
				board[i][j] = color;
				color = !color;
			}
		}
	}
}