package application.model;

/**
 * Class representation of the value of a movement
 * 
 * @author Chris Crabtree
 * UTSA Application Programming CS3443 Fall 2018
 */
public class MoveValue {
	private Coordinate coordinateFrom;
	private Coordinate coordinateTo;
	private Double value;
	
	/**
	 * Constructor for a MoveValue object
	 * 
	 * @param c Coordinate of the start of the move
	 * @param c2 Coordinate of the end of the move
	 * @param d Calue of the move
	 */
	public MoveValue(Coordinate c, Coordinate c2, Double d) {
		this.coordinateFrom = c;
		this.coordinateTo = c2;
		this.value = d;
	}

	/**
	 * Gets coordinateFrom
	 * 
	 * @return Coordinate
	 */
	public Coordinate getCoordinateFrom() {
		return coordinateFrom;
	}

	/**
	 * Sets coordinateFrom
	 * 
	 * @param coordinareFrom Coordinate
	 */
	public void setCoordinateFrom(Coordinate coordinateFrom) {
		this.coordinateFrom = coordinateFrom;
	}

	/**
	 * Gets coordinateTo
	 * 
	 * @return Coordinate
	 */
	public Coordinate getCoordinateTo() {
		return coordinateTo;
	}

	/**
	 * Sets coordinateTo
	 * 
	 * @param coordinareTo Coordinate
	 */
	public void setCoordinateTo(Coordinate coordinateTo) {
		this.coordinateTo = coordinateTo;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}


	
	
	
}
