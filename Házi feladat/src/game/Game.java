package game;

import java.util.List;

import calltree.CallTreeElement;
import element.Wall;
import element.movable.Bullet;
import element.movable.Colonel;
import enums.Direction;
import field.Field;

public class Game {

	public static CallTreeElement callTree = new CallTreeElement();

	private static Colonel oneill;
	private static List<Bullet> bullets;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.enter(new Wall(true));

		oneill = new Colonel(f1, Direction.EAST);
		// Bullet b = new Bullet(f1, Direction.EAST, PortalColour.BLUE);

		// b.step();
		oneill.step();

		callTree.printCallTree(" ", 0);
	}
}
