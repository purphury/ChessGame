package application.model;

import application.model.Board.Type;

public class AI {
	public AI() {
		
	}
	public double evaluateBoard(Board board) {
		double strength = 0;
		double strWhite = 0;
		
		Piece[][] boardM = board.getBoard();
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(boardM[i][j] != null)
					strength += boardM[i][j].getStrength(i, j);
			}
		return strength;
	}
}
