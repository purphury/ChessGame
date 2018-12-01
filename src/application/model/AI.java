package application.model;

public class AI {
	private double[][] KingStrength;
	private double[][] QueenStrength;
	private double[][] RookStrength;
	private double[][] BishopStrength;
	private double[][] KnightStrength;
	private double[][] PawnStrength;
	public AI() {
		KingStrength = KingStrengthBoard.KingStrengthBoard;
		QueenStrength = QueenStrengthBoard.QueenStrengthBoard;
		RookStrength = RookStrengthBoard.RookStrengthBoard;
		BishopStrength = BishopStrengthBoard.BishopStrengthBoard;
		KnightStrength = KnightStrengthBoard.KnightStrengthBoard;
		PawnStrength = PawnStrengthBoard.PawnStrengthBoard;
	}
	public int evaluateBoard(Board board) {
		return 0;
	}
}
