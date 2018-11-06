package application.model;

public class Coordinate {
	private int rowIndex;
	private int columnIndex;
	
	public Coordinate() {
		rowIndex = -1;
		columnIndex = -1;
	} 
	
	public Coordinate(int rowIndex, int columnIndex) {
		super();
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
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
	
	public boolean equals(Coordinate coord) {
		if(coord.getRowIndex() == this.rowIndex && coord.getColumnIndex() == this.columnIndex)
			return true;
		return false;
	}
	
}
