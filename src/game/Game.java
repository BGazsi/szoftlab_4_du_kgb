package game;

import java.util.List;

import element.Wall;
import element.movable.Bullet;
import element.movable.Colonel;
import enums.Direction;
import enums.PortalColour;
import field.Field;

public class Game {

	private static Colonel oneill;
	private List<Bullet> bullets;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.enter(new Wall(true));

		oneill = new Colonel(f1, Direction.EAST);
		Bullet b = new Bullet(f1, Direction.EAST, PortalColour.BLUE);

		b.step();
		oneill.step();
	}
}
