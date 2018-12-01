package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public abstract class Piece 
{
	private Type type;
	private boolean hasMoved;
	private double strength;
	
	public Piece (Type type, int strength) {
		this.type = type;
		this.setHasMoved(false);
		this.strength = strength;
	}
	public abstract ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board);
	
	// gets the color for a given piece
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Type otherType() {
		return this.getType() == Type.BLACK ? Type.WHITE : Type.BLACK;
	}
	public boolean getHasMoved() {
		return hasMoved;
	}
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	protected boolean boundsChecker(int num) {
		return num >= 0 && num <= 7;
	}
	protected boolean boundsChecker(int num1, int num2) {
		return (num1 >= 0 && num1 <= 7 && num2 >= 0 && num2 <= 7);
	}
	public abstract double getStrength(int r, int c);
	public void setStrength(double strength) {
		this.strength = strength;
	}
}
