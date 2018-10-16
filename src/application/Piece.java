package application;


public abstract class Piece 
{
	private int x, y; //x is the x position , and y is the y position
	private boolean color;
	
	public Piece (int x, int y, boolean color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}
	//get the column where the piece is positioned 
	public int getX()
	{
		return x;
	}
	//setting a new column position for the piece
	public void setX(int x)
	{
		this.x = x;
	}
	//gets the row where the piece is positioned
	public int getY()
	{
		return y;
	}
	//setting a new row for the piece
	public void setY(int y)
	{
		this.y = y;
	}
	// gets the color for a given piece
	public boolean getColor()
	{
		return color;
	}
	public void setColor(boolean color)
	{
		this.color = color;
	}
	
}
