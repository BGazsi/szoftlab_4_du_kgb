package enums;

public enum Direction {
	NORTH, SOUTH, EAST, WEST;

	public Direction getOpposite() {

		switch (this) {
		case EAST:
			return Direction.WEST;
		case NORTH:
			return Direction.SOUTH;
		case SOUTH:
			return Direction.NORTH;
		case WEST:
			return Direction.EAST;
		default:
			return null;
		}
	}
}
