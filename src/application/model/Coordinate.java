package application.model;

public class Coordinate {
	private int rowIndex;
	private int columnIndex;
	
	public Coordinate() {
		rowIndex = -1;
		columnIndex = -1;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	public String toString() {
		return "("+getRowIndex()+", "+getColumnIndex()+")";	
	}
	
}
