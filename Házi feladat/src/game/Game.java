package game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import element.Box;
import element.Door;
import element.Element;
import element.Gap;
import element.Scale;
import element.Wall;
import element.ZPM;
import element.movable.Bullet;
import element.movable.Movable;
import element.movable.Replicator;
import element.movable.player.Colonel;
import element.movable.player.Jaffa;
import element.movable.player.Player;
import enums.Direction;
import enums.PortalColour;
import field.Field;
import portal.Portals;

// A játék színterét leíró osztály
public class Game {

	private static Field fieldReference;
	private static int fieldWidth = -1;
	private static int fieldHeight = -1;
	private static Element lastAdded = null;
	private static Element lastRemoved = null;
	private static Map<String, Element> elements = new HashMap<String, Element>();

	private static Field findField(String elementName) {

		for (Field rowStart = fieldReference; rowStart != null; rowStart = rowStart.getNeighbour(Direction.SOUTH)) {
			for (Field f = rowStart; f != null; f = f.getNeighbour(Direction.EAST)) {
				if (f.getElement(elementName) != null) {
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

	private static Entry<Integer, Integer> getCoordinate(Field f) {

		int x = 0;
		int y = 0;
		Field result = fieldReference;
		Field rowStart = fieldReference;

		while (result != f) {

			result = result.getNeighbour(Direction.EAST);
			x++;

			if (result == null) {

				rowStart = rowStart.getNeighbour(Direction.SOUTH);
				result = rowStart;
				x = 0;
				y++;
			}
		}

		return new AbstractMap.SimpleEntry<Integer, Integer>(x, y);
	}

	public static boolean addElement(Element e, Field f, boolean setFull) {

		if (f == null) {

			Random rnd = new Random();
			int x = -1;
			int y = -1;

			do {
				x = rnd.nextInt(fieldWidth);
				y = rnd.nextInt(fieldHeight);
				f = getField(x, y);
			} while (f.isFull());

		}

		if (elements.containsKey(e.getName())) {

			String name = Element.getNewName();
			e.setName(name);
		}

		if (!f.isFull()) {

			f.setFull(setFull);
			elements.put(e.getName(), e);
			lastAdded = e;
			f.enter(e);
			return true;

		} else {
			return false;
		}
	}

	public static void removeElement(Element e) {

		elements.remove(e.getName());
		findField(e.getName()).exit(e);
		lastRemoved = e;
	}

	private static void drawMaze() {

		for (int i = 0; i < fieldWidth; i++) {
			if (i == 0) {
				System.out.print('+');
			}
			System.out.print('-');
			if (i == fieldWidth - 1) {
				System.out.print('+');
			}
		}
		System.out.println();
		for (Field rowStart = fieldReference; rowStart != null; rowStart = rowStart.getNeighbour(Direction.SOUTH)) {

			for (Field f = rowStart; f != null; f = f.getNeighbour(Direction.EAST)) {

				if (f == rowStart) {
					System.out.print('|');
				}
				System.out.print(f.getStatusString());
				if (f.getNeighbour(Direction.EAST) == null) {
					System.out.print('|');
				}
			}

			System.out.println();
		}
		for (int i = 0; i < fieldWidth; i++) {
			if (i == 0) {
				System.out.print('+');
			}
			System.out.print('-');
			if (i == fieldWidth - 1) {
				System.out.print('+');
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {

		// syntax: java <progam-neve> [<input-file>] [<output-file>]

		switch (args.length) {
		case 2:

			try {

				PrintStream ps = new PrintStream(args[1]);
				System.setOut(ps);

			} catch (FileNotFoundException e) {

				System.err.println(args[1] + " nevű file nem található!");

			}

		case 1:

			try {

				FileInputStream fis = new FileInputStream(args[0]);
				System.setIn(fis);

			} catch (FileNotFoundException e) {

				System.err.println(args[0] + " nevű file nem található!");
			}

			break;
		}

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line;
			while ((line = br.readLine()) != null) {

				try {

					if (line.startsWith("create maze")) {

						String[] params = line.substring("create maze".length() + 1).split(" ");
						if (params.length == 2) {

							final int x = Integer.parseInt(params[0]);
							final int y = Integer.parseInt(params[1]);

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

							fieldWidth = x;
							fieldHeight = y;

							System.out.println("Created a " + x + " x " + y + " sized maze.");
							drawMaze();

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create door")) {

						String[] params = line.substring("create door".length() + 1).split(" ");
						if (params.length == 3) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);

							Door d = new Door(name);
							Field f = getField(x, y);

							boolean success = addElement(d, f, true);

							System.out
									.println("Created door '" + d.getName() + "' in (" + x + ", " + y + ") position.");
							drawMaze();
							if (!success) {
								System.out.println("Failed to create door.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create zpm")) {

						String[] params = line.substring("create zpm".length() + 1).split(" ");
						if (params.length == 3) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);

							ZPM z = new ZPM(name);
							Field f = getField(x, y);

							boolean success = addElement(z, f, true);

							System.out.println("Created zpm '" + z.getName() + "' in (" + x + "," + y + ") position.");
							drawMaze();
							if (!success) {
								System.out.println("Failed to create zpm.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create box")) {

						String[] params = line.substring("create box".length() + 1).split(" ");
						if (params.length == 3) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);

							Box b = new Box(name, 1);
							Field f = getField(x, y);

							addElement(b, f, false);

							System.out.println("Created box '" + b.getName() + "' in (" + x + "," + y + ") position.");
							drawMaze();

						} else {
							throw new IllegalArgumentException(line);
						}

					}

					else if (line.startsWith("create scale")) {

						String[] params = line.substring("create scale".length() + 1).split(" ");
						if (params.length == 5) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);
							final int weight = Integer.parseInt(params[3]);
							final String doorName = params[4];

							Scale s = new Scale(name, (Door) elements.get(doorName), weight);
							Field f = getField(x, y);

							boolean success = addElement(s, f, false);

							System.out.println("Created scale '" + s.getName() + "' in (" + x + ", " + y
									+ ") position. Weight limit: " + weight + ".");
							drawMaze();
							if (!success) {
								System.out.println("Failed to create scale.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create gap")) {

						String[] params = line.substring("create gap".length() + 1).split(" ");
						if (params.length == 3) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);

							Gap g = new Gap(name);
							Field f = getField(x, y);

							boolean success = addElement(g, f, true);

							System.out.println("Created gap '" + g.getName() + "' in (" + x + ", " + y + ") position.");
							drawMaze();
							if (!success) {
								System.out.println("Failed to create gap.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create wall")) {

						String[] params = line.substring("create wall".length() + 1).split(" ");
						if (params.length == 3) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);

							Wall w = new Wall(name, true);
							Field f = getField(x, y);

							boolean success = addElement(w, f, true);

							System.out
									.println("Created wall '" + w.getName() + "' in (" + x + ", " + y + ") position.");
							drawMaze();
							if (!success) {
								System.out.println("Failed to create wall.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create portal")) {

						String[] params = line.substring("create portal".length() + 1).split(" ");
						if (params.length == 5) {

							final String name = params[0];
							final PortalColour portalColour = PortalColour.valueOf(params[1]);
							final Wall wall = (Wall) elements.get(params[2]);
							final int x = Integer.parseInt(params[3]);
							final int y = Integer.parseInt(params[4]);

							Field f = getField(x, y);

							Portals.createPortal(portalColour, wall, f);

							drawMaze();

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create colonel")) {

						String[] params = line.substring("create colonel".length() + 1).split(" ");
						if (params.length == 3) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);

							Field f = getField(x, y);
							Colonel c = new Colonel(name, 1, f, Direction.NORTH);

							boolean success = addElement(c, f, true);

							System.out.println(
									"Created colonel '" + c.getName() + "' in (" + x + ", " + y + ") position.");
							drawMaze();
							if (!success) {
								System.out.println("Failed to create colonel.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create jaffa")) {

						String[] params = line.substring("create jaffa".length() + 1).split(" ");
						if (params.length == 3) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);

							Field f = getField(x, y);
							Jaffa j = new Jaffa(name, 1, f, Direction.NORTH);

							boolean success = addElement(j, f, true);

							System.out
									.println("Created jaffa '" + j.getName() + "' in (" + x + ", " + y + ") position.");
							drawMaze();
							if (!success) {
								System.out.println("Failed to create jaffa.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("create replicator")) {

						String[] params = line.substring("create replicator".length() + 1).split(" ");
						if (params.length == 3) {

							final String name = params[0];
							final int x = Integer.parseInt(params[1]);
							final int y = Integer.parseInt(params[2]);

							Field f = getField(x, y);
							Replicator r = new Replicator(name, f, Direction.NORTH);

							boolean success = addElement(r, f, true);

							System.out.println(
									"Created replicator '" + r.getName() + "' in (" + x + ", " + y + ") position.");
							drawMaze();
							if (!success) {
								System.out.println("Failed to create replicator.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("step")) {

						String[] params = line.substring("step".length() + 1).split(" ");
						if (params.length == 1) {

							final String name = params[0];

							Movable m = (Movable) elements.get(name);
							Direction d = m.getDirection();

							Field oldField = m.getPosition();
							Element oldLastAdded = lastAdded;
							m.step();
							Element newLastAdded = lastAdded;
							Field newField = m.getPosition();

							if (oldField != newField) {
								System.out
										.println("'" + m.getName() + "' stepped " + m.getDirection().toString() + ".");
								// TODO remove next line
								drawMaze();
								if (newField.isEmpty()) {

									Entry<Integer, Integer> e = getCoordinate(newField);

									System.out.println("Created empty field in (" + e.getKey() + "," + e.getValue()
											+ ") position.");
								}
							} else {
								System.out.println("Step failed.");
							}

							if (oldLastAdded != newLastAdded) {

								Entry<Integer, Integer> e = getCoordinate(findField(newLastAdded.getName()));

								System.out.println("Created zpm '" + newLastAdded + "' in (" + e.getKey() + ","
										+ e.getValue() + ") position.");
								drawMaze();
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("rotate")) {

						String[] params = line.substring("rotate".length() + 1).split(" ");
						if (params.length == 2) {

							final String name = params[0];
							final Direction direction = Direction.valueOf(params[1]);

							Movable m = (Movable) elements.get(name);
							m.setDirection(direction);

							System.out.println("'" + m.getName() + "' has been rotated in the direction '"
									+ m.getDirection().toString() + "'.");
							drawMaze();

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("pickup")) {

						String[] params = line.substring("pickup".length() + 1).split(" ");
						if (params.length == 1) {

							final String name = params[0];

							Player p = (Player) elements.get(name);
							Box bb = p.getBox();

							p.step();
							p.setDirection(p.getDirection().getOpposite());
							p.step();
							p.setDirection(p.getDirection().getOpposite());

							Box ba = p.getBox();

							if (bb != ba) {
								System.out.println("'" + p.getName() + "' picked up a box '" + ba.getName() + "'.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("putdown")) {

						String[] params = line.substring("putdown".length() + 1).split(" ");
						if (params.length == 1) {

							final String name = params[0];

							Player p = (Player) elements.get(name);

							p.putDownBox();

							System.out.println("'" + p.getName() + "' put down a box '" + lastAdded + "'.");

						} else {
							throw new IllegalArgumentException(line);
						}

					} else if (line.startsWith("shoot")) {

						String[] params = line.substring("shoot".length() + 1).split(" ");
						if (params.length == 2) {

							final PortalColour portalColour = PortalColour.valueOf(params[0]);
							final String name = params[1];

							Player p = (Player) elements.get(name);

							System.out.println("'" + p.getName() + "' shot a " + portalColour.toString() + " bullet.");

							p.shoot(portalColour);

							if (lastRemoved instanceof Bullet) {
								System.out.println("Bullet has been destroyed.");
							}

						} else {
							throw new IllegalArgumentException(line);
						}

					}

					// else if (line.startsWith("")) {
					//
					// String[] params = line.substring("".length() +
					// 1).split("
					// ");
					// if (params.length == 2) {
					//
					// } else {
					// throw new IllegalArgumentException(line);
					// }
					//
					// }

					else {
						System.err.println("Unknown command: " + line);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
