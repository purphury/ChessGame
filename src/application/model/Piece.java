package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

public abstract class Piece 
{
	private Type type;
	
	public Piece (Type type) {
		this.type = type;
	}
	public abstract ArrayList<Integer[]> getAvailableMovements(int x, int y);
	
	// gets the color for a given piece
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
}
