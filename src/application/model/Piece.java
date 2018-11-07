package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public abstract class Piece 
{
	private Type type;
	private boolean hasMoved;
	
	public Piece (Type type) {
		this.type = type;
		this.setHasMoved(false);
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
}
