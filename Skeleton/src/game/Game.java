package game;

import element.movable.Colonel;
import enums.Direction;
import field.Empty;
import field.Field;
import field.Wall;

public class Game {

	private static Colonel oneill;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Field f1 = new Empty();
		Field f2 = new Wall(true);

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		oneill = new Colonel(f1, Direction.EAST);
		f1.addElement(oneill);

		oneill.step();
	}
}
