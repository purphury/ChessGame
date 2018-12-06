package application.model;

import java.util.ArrayList;

import application.model.Board.Type;

/**
 * This is a class representation of a chess piece
 * 
 * @author Daniel Nix, Jonathan Balraj, Majd Hamoudah
 *	UTSA Application Programming CS3443 Fall 2018
 */
public abstract class Piece 
{
	private Type type;
	private boolean hasMoved;
	private double strength;
	/**
	 * @return
	 */
	public double getStrength() {
		return strength;
	}

	public Piece (Type type, int strength) {
		this.type = type;
		this.setHasMoved(false);
	}
	
	/**
	 * Makes a copy of the piece and returns it
	 * @param piece
	 * @return
	 */
	public abstract Piece copyPiece(Piece piece);
	
	/**
	 * Calculates all possible moves for the piece and returns it as a collection
	 * @param r
	 * @param c
	 * @param board
	 * @return
	 */
	public abstract ArrayList<Coordinate> getAvailableMovements(int r, int c, Board board);
	
	 
	/**
	 * Gets the color for a given piece
	 * @return
	 */
	public Type getType() {
		return type;
	}
	/**
	 * Sets color for the piece
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * Gets the opposing color for this piece
	 * @return
	 */
	public Type otherType() {
		return this.getType() == Type.BLACK ? Type.WHITE : Type.BLACK;
	}
	/**
	 * Returns whether or not the piece has been moved
	 * @return
	 */
	public boolean getHasMoved() {
		return hasMoved;
	}
	/**
	 * Sets the record of whether or not this iece has been moved
	 * @param hasMoved
	 */
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	/**
	 * Helper method to check if a number can be on the board
	 * @param num
	 * @return
	 */
	protected boolean boundsChecker(int num) {
		return num >= 0 && num <= 7;
	}
	/**
	 * Helper method to check if 2 numbers can be on the board
	 * @param num1
	 * @param num2
	 * @return
	 */
	protected boolean boundsChecker(int num1, int num2) {
		return (num1 >= 0 && num1 <= 7 && num2 >= 0 && num2 <= 7);
	}
	/**
	 * Gets strength of the piece (function of type of piece and location on board)
	 * @param r
	 * @param c
	 * @return
	 */
	public abstract double getStrength(int r, int c);
	
	/**
	 * Gets strength of the piece (function of type of piece and location on board)
	 * @param r
	 * @param c
	 * @return
	 */
	public abstract double getStrengthWOStrategy(int r, int c);
	

	public abstract String toString2();
	
	/**
	 * Sets strength of piece
	 * @param strength
	 */
	public void setStrength(double strength) {
		this.strength = strength;
	}
}

