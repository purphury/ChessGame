package application.model;

/**
 * Class representation of a coordinate on a Chess board
 * 
 * @author Chris Crabtree
 * UTSA Application Programming CS3443 Fall 2018
 */
public class Coordinate {
	private int rowIndex;
	private int columnIndex;
	
	/**
	 * Constructor for a Coordinate
	 */
	public Coordinate() {
		rowIndex = -1;
		columnIndex = -1;
	} 
	
	/**
	 * Constructor for a Coordinate
	 * 
	 * @param rowIndex
	 * @param columnIndex
	 */
	public Coordinate(int rowIndex, int columnIndex) {
		super();
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
	/**
	 * Gets the row index
	 * 
	 * @return int
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * Sets the row index
	 * 
	 * @param rowIndex
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	/**
	 * Gets the column index
	 * 
	 * @return int
	 */
	public int getColumnIndex() {
		return columnIndex;
	}
	
	/**
	 * Sets the column index index
	 * 
	 * @param rowIndex
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	/**
	 * To String for Coordinate class
	 * 
	 * @return String
	 */
	public String toString() {
		return "("+getRowIndex()+", "+getColumnIndex()+")";	
	}
	
	/**
	 * Checks if two coordinates are equal
	 * 
	 * @param coord Coordinate to check
	 * @return Boolean
	 */
	public boolean equals(Coordinate coord) {
		if(coord.getRowIndex() == this.rowIndex && coord.getColumnIndex() == this.columnIndex)
			return true;
		return false;
	}
	
}
