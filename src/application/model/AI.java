package application.model;

import application.model.Board.Type;

public class AI {
<<<<<<< HEAD
	public AI() {
		
	}
	public double evaluateBoard(Board board) {
		double strength = 0;
		double strWhite = 0;
		
=======
	public int evaluateBoard(Board board) {
		int strength = 0;
>>>>>>> branch 'AIBranch' of https://github.com/UTSA-CS-3443/ChessGame.git
		Piece[][] boardM = board.getBoard();
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(boardM[i][j] != null)
					strength += boardM[i][j].getStrength(i, j);
			}
		return strength;
	}
}
