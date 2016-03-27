package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import calltree.CallTreeElement;
import element.Box;
import element.Door;
import element.Gap;
import element.Scale;
import element.Wall;
import element.ZPM;
import element.movable.Bullet;
import element.movable.Colonel;
import enums.Direction;
import enums.PortalColour;
import field.Field;
import portal.Portals;

public class Game {

	public static CallTreeElement callTree = new CallTreeElement();

	private static final String[] menuOptions = { "Inicializ�l�s", "Ezredes �res mez�re l�p", "Ezredes szakad�kba l�p",
			"Ezredes z�rt ajt�ra l�pne", "Ezredes nyitott ajt�ra l�p", "Ezredes falra l�pne", "Ezredes m�rlegre l�p",
			"Ezredes lesz�ll a m�rlegr�l", "Ezredes l�", "L�ved�k rep�l", "L�ved�k sima falba csap�dik",
			"L�ved�k speci�lis falba csap�dik", "Ezredes felveszi a dobozt", "Ezredes lerakja a dobozt",
			"F�regj�rat ny�lik", "Ezredes �tl�p a f�regj�raton", "Ezredes felveszi a ZPM-et" };

	public static void init() {

		final int rows = 3, cols = 3;
		Field fields[][] = new Field[rows][cols];

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {

				Field f = new Field();
				fields[y][x] = f;

				if (x != 0)
					f.setNeighbour(Direction.WEST, fields[y][x - 1]);
				if (y != 0)
					f.setNeighbour(Direction.NORTH, fields[y - 1][x]);
			}
		}

		Door d = new Door();

		fields[0][0].addElement(new Scale(d));
		fields[0][2].addElement(new Box());

		fields[1][0].addElement(new Wall(false));
		fields[1][1].addElement(d);
		fields[1][2].addElement(new Wall(false));

		Colonel oneill = new Colonel(fields[0][1], Direction.SOUTH);

		fields[0][1].addElement(oneill);
	}

	public static void colonelToEmptyField() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void colonelToGap() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.addElement(new Gap());

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void colonelToClosedDoor() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.addElement(new Door());

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void colonelToOpenedDoor() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.addElement(new Door(true));

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void colonelToWall() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.addElement(new Wall(false));

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void ColonelToScale() {

		Field f1 = new Field();
		Field f2 = new Field();
		Field f3 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);
		f2.setNeighbour(Direction.EAST, f3);
		f3.setNeighbour(Direction.WEST, f2);

		Door d = new Door();

		f2.addElement(new Scale(d));
		f3.addElement(d);

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void colonelFromScale() {

		Field f1 = new Field();
		Field f2 = new Field();
		Field f3 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);
		f2.setNeighbour(Direction.EAST, f3);
		f3.setNeighbour(Direction.WEST, f2);

		Door d = new Door(true);

		f2.addElement(new Scale(d));
		f3.addElement(d);

		Colonel oneill = new Colonel(f2, Direction.WEST);

		oneill.step();
	}

	public static void colonelShoot() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.shoot(PortalColour.BLUE);
	}

	public static void bulletFly() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		Bullet b = new Bullet(f1, Direction.EAST, PortalColour.BLUE);

		b.step();
	}

	public static void bulletToSimpleWall() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.addElement(new Wall(false));

		Bullet b = new Bullet(f1, Direction.EAST, PortalColour.BLUE);

		b.step();
	}

	public static void bulletToSpecialWall() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.addElement(new Wall(true));

		Bullet b = new Bullet(f1, Direction.EAST, PortalColour.BLUE);

		b.step();
	}

	public static void colonelPickUpBox() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.addElement(new Box());

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void colonelPutDownBox() {

		Field f1 = new Field();

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.putDown(oneill.getElement());
	}

	public static void createPortal() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		Wall w = new Wall(true);

		f2.addElement(w);

		Portals.createPortal(PortalColour.BLUE, w, f1);
	}

	public static void colonelToPortal() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		Wall w = new Wall(true);

		f2.addElement(w);

		Portals.createPortal(PortalColour.BLUE, w, f1);

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void colonelToZPM() {

		Field f1 = new Field();
		Field f2 = new Field();

		f1.setNeighbour(Direction.EAST, f2);
		f2.setNeighbour(Direction.WEST, f1);

		f2.addElement(new ZPM());

		Colonel oneill = new Colonel(f1, Direction.EAST);

		oneill.step();
	}

	public static void main(String[] args) {
		// TODO init

		while (true) {
			for (int i = 0; i < menuOptions.length; i++) {

				System.out.println(i + ".\t" + menuOptions[i]);
			}

			System.out.print("\nK�rem a sorsz�mot: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			try {

				String s = br.readLine();

				System.out.println();

				if (s.equals("exit")) {
					return;
				}

				callTree = new CallTreeElement();
				int i = Integer.parseInt(s);

				switch (i) {
				case 0:
					init();
					break;
				case 1:
					colonelToEmptyField();
					break;
				case 2:
					colonelToGap();
					break;
				case 3:
					colonelToClosedDoor();
					break;
				case 4:
					colonelToOpenedDoor();
					break;
				case 5:
					colonelToWall();
					break;
				case 6:
					ColonelToScale();
					break;
				case 7:
					colonelFromScale();
					break;
				case 8:
					colonelShoot();
					break;
				case 9:
					bulletFly();
					break;
				case 10:
					bulletToSimpleWall();
					break;
				case 11:
					bulletToSpecialWall();
					break;
				case 12:
					colonelPickUpBox();
					break;
				case 13:
					colonelPutDownBox();
					break;
				case 14:
					createPortal();
					break;
				case 15:
					colonelToPortal();
					break;
				case 16:
					colonelToZPM();
					break;
				}

				if (i == 0)
					callTree.printCallTree(3, "     ", 0, "");
				else if (i == 9 || i == 10 || i == 11)
					callTree.printCallTree(1, "     ", 0, "");
				else
					callTree.printCallTree(2, "     ", 0, "");

				br.readLine();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
