package controller;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.element.Box;
import model.element.Door;
import model.element.Element;
import model.element.Gap;
import model.element.Scale;
import model.element.Wall;
import model.element.ZPM;
import model.element.movable.Bullet;
import model.element.movable.Replicator;
import model.element.movable.player.Colonel;
import model.element.movable.player.Jaffa;
import model.enums.Direction;
import model.enums.PortalColour;
import model.field.Field;
import view.GameFrame;
import view.GamePanel;

// A játék színterét leíró osztály
public class Game {

	private final static long BULLET_DELAY = 250;
	private final static long REPLICATOR_DELAY = 1000;

	private static Field fieldReference;
	private static Dimension fieldSize;
	private static Colonel player1;
	private static Jaffa player2;
	private static Set<Element> elements = new HashSet<Element>();
	private static JPanel panel;

	private static void initGame(int x, int y) {

		Field[] oldRow = null;
		for (int row = 0; row < y; row++) {

			Field[] newRow = new Field[x];
			for (int col = 0; col < x; col++) {

				newRow[col] = new Field();

				if (row == 0 || row == y - 1 || col == 0 || col == x - 1) {
					newRow[col].enter(new Wall(false));
				}

				if (col > 0) {
					newRow[col - 1].setNeighbour(Direction.EAST, newRow[col]);
					newRow[col].setNeighbour(Direction.WEST, newRow[col - 1]);
				}
				if (row > 0) {
					oldRow[col].setNeighbour(Direction.SOUTH, newRow[col]);
					newRow[col].setNeighbour(Direction.NORTH, oldRow[col]);
				}
				if (row == 0 && col == 0) {
					fieldReference = newRow[0];
				}
			}
			oldRow = newRow;
		}

		fieldSize = new Dimension(x, y);
	}

	private static Field findField(Element e) {

		for (Field rowStart = fieldReference; rowStart != null; rowStart = rowStart.getNeighbour(Direction.SOUTH)) {
			for (Field f = rowStart; f != null; f = f.getNeighbour(Direction.EAST)) {
				if (f.contains(e)) {
					return f;
				}
			}
		}

		return null;
	}

	private static Field getField(int x, int y) {

		Field f = fieldReference;
		for (int i = 0; i < x; i++) {
			f = f.getNeighbour(Direction.EAST);
		}
		for (int i = 0; i < y; i++) {
			f = f.getNeighbour(Direction.SOUTH);
		}

		return f;
	}

	public static Dimension getFieldSize() {
		return fieldSize;
	}

	public static Field getFieldReference() {
		return fieldReference;
	}

	public static void addElement(Element e, Field f) {

		if (f == null) {

			Random rnd = new Random();
			int x = -1;
			int y = -1;

			do {
				x = rnd.nextInt((int) fieldSize.getWidth());
				y = rnd.nextInt((int) fieldSize.getHeight());
				f = getField(x, y);
			} while (!f.isEmpty());

		}

		elements.add(e);
		f.enter(e);

	}

	public static void removeElement(Element e) {

		elements.remove(e);
		findField(e).exit(e);
	}

	public static void panelRepaint() {

		panel.revalidate();
		panel.repaint();
	}

	public static void player1Moved(Direction direction) {

		if (player1.getDirection() == direction && !player1.isKilled()) {
			player1.step();
		} else {
			player1.setDirection(direction);
		}
		panelRepaint();
	}

	public static void player1Shoot(PortalColour portalColour) {

		Bullet b = player1.shoot(portalColour);
		new AutoStepController(b, false, BULLET_DELAY).start();
		panelRepaint();
	}

	public static void player1PutDownBox() {

		player1.putDownBox();
		panelRepaint();
	}

	public static void player2Moved(Direction direction) {

		if (player2.getDirection() == direction && !player2.isKilled()) {
			player2.step();
		} else {
			player2.setDirection(direction);
		}
		panelRepaint();
	}

	public static void player2Shoot(PortalColour portalColour) {

		Bullet b = player2.shoot(portalColour);
		new AutoStepController(b, false, BULLET_DELAY).start();
		panelRepaint();
	}

	public static void player2PutDownBox() {

		player2.putDownBox();
		panelRepaint();
	}

	public static void main(String[] args) {

		initGame(10, 10);

		Field player1Field = fieldReference.getNeighbour(8, Direction.EAST).getNeighbour(Direction.SOUTH);
		Field player2Field = fieldReference.getNeighbour(1, Direction.EAST).getNeighbour(Direction.SOUTH);
		Field replicatorField = fieldReference.getNeighbour(8, Direction.EAST).getNeighbour(8, Direction.SOUTH);
		player1 = new Colonel(1, player1Field, Direction.SOUTH);
		player2 = new Jaffa(1, player2Field, Direction.SOUTH);
		Replicator replicator = new Replicator(replicatorField, Direction.WEST);
		Door door = new Door();

		// addElement(new Gap(), fieldReference.getNeighbour(7,
		// Direction.EAST).getNeighbour(8, Direction.SOUTH));
		// addElement(new Gap(), fieldReference.getNeighbour(8,
		// Direction.EAST).getNeighbour(7, Direction.SOUTH));

		addElement(player1, player1Field);
		addElement(player2, player2Field);
		addElement(replicator, replicatorField);
		addElement(new Wall(true), fieldReference.getNeighbour(1, Direction.EAST).getNeighbour(5, Direction.SOUTH));
		addElement(new Wall(true), fieldReference.getNeighbour(3, Direction.EAST).getNeighbour(5, Direction.SOUTH));
		addElement(new Wall(true), fieldReference.getNeighbour(4, Direction.EAST).getNeighbour(5, Direction.SOUTH));
		addElement(new Wall(true), fieldReference.getNeighbour(4, Direction.EAST).getNeighbour(6, Direction.SOUTH));
		addElement(new Wall(true), fieldReference.getNeighbour(4, Direction.EAST).getNeighbour(7, Direction.SOUTH));
		addElement(new Wall(true), fieldReference.getNeighbour(4, Direction.EAST).getNeighbour(8, Direction.SOUTH));
		addElement(door, fieldReference.getNeighbour(2, Direction.EAST).getNeighbour(5, Direction.SOUTH));
		addElement(new Scale(door, 5), fieldReference.getNeighbour(5, Direction.EAST).getNeighbour(8, Direction.SOUTH));
		addElement(new Box(5), fieldReference.getNeighbour(1, Direction.EAST).getNeighbour(4, Direction.SOUTH));
		addElement(new Gap(), fieldReference.getNeighbour(1, Direction.EAST).getNeighbour(8, Direction.SOUTH));
		addElement(new ZPM(), fieldReference.getNeighbour(2, Direction.EAST).getNeighbour(8, Direction.SOUTH));
		addElement(new ZPM(), fieldReference.getNeighbour(3, Direction.EAST).getNeighbour(8, Direction.SOUTH));
		addElement(new ZPM(), fieldReference.getNeighbour(3, Direction.EAST).getNeighbour(7, Direction.SOUTH));

		new AutoStepController(replicator, true, REPLICATOR_DELAY).start();

		panel = new GamePanel();
		JFrame frame = new GameFrame(panel);

		frame.addKeyListener(new KeyEventHandler());
	}
}
