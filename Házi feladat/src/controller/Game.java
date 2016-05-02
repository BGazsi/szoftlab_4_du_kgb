package controller;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.element.Element;
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

		if (player1.getDirection() == direction) {
			player1.step();
			panelRepaint();
		} else {
			player1.setDirection(direction);
		}
	}

	public static void player1Shoot(PortalColour portalColour) {

		Bullet b = player1.shoot(portalColour);
		new AutoStepController(b, false).start();
		panelRepaint();
	}

	public static void player2Moved(Direction direction) {

		if (player2.getDirection() == direction) {
			player2.step();
			panelRepaint();
		} else {
			player2.setDirection(direction);
		}
	}

	public static void player2Shoot(PortalColour portalColour) {

		Bullet b = player2.shoot(portalColour);
		new AutoStepController(b, false).start();
		panelRepaint();
	}

	public static void main(String[] args) {

		initGame(5, 5);

		Replicator replicator = new Replicator(fieldReference.getNeighbour(Direction.EAST).getNeighbour(Direction.SOUTH)
				.getNeighbour(Direction.EAST).getNeighbour(Direction.SOUTH), Direction.SOUTH);

		addElement(replicator, fieldReference.getNeighbour(Direction.EAST).getNeighbour(Direction.SOUTH)
				.getNeighbour(Direction.EAST).getNeighbour(Direction.SOUTH));
		new AutoStepController(replicator, true).start();

		panel = new GamePanel();
		JFrame frame = new GameFrame(panel);

		frame.addKeyListener(new KeyEventHandler());
	}
}
