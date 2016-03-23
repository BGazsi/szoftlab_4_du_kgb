package enums;

public enum Direction {
	NORTH, SOUTH, EAST, WEST;
	
	public Direction getOpposite() {
		switch(this) {
		case EAST:
			return WEST;
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		case WEST:
			return EAST;
		default:
			return null;			
		}
	}
}
