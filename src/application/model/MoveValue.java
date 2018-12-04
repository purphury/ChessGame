package application.model;

public class MoveValue {
	private Coordinate coordinateFrom;
	private Coordinate coordinateTo;
	private Double value;
	
	public MoveValue(Coordinate c, Coordinate c2, Double d) {
		this.coordinateFrom = c;
		this.coordinateTo = c2;
		this.value = d;
	}

	public Coordinate getCoordinateFrom() {
		return coordinateFrom;
	}

	public void setCoordinateFrom(Coordinate coordinateFrom) {
		this.coordinateFrom = coordinateFrom;
	}

	public Coordinate getCoordinateTo() {
		return coordinateTo;
	}

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
