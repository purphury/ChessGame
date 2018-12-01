package application.model;

public class AI {
	public int evaluateBoard(Board board) {
		int strength = 0;
		Piece[][] boardM = board.getBoard();
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(boardM[i][j] != null) {
					System.out.println(boardM[i][j].getStrength(i, j));
					strength += boardM[i][j].getStrength(i, j);
				}
			}
		return strength;
	}
}
