package game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import element.Door;
import element.Element;
import element.Scale;
import enums.Direction;
import field.Field;

// A játék színterét leíró osztály
public class Game {

	private static Field fieldReference;
	private static Map<String, Field> elements = new HashMap<String, Field>();

	public static void addElement(Element e, Field f) {

		if (elements.containsKey(e.getName())) {

			String name = Element.getNewName();
			e.setName(name);
		}

		elements.put(e.getName(), f);
		f.enter(e);
	}

	public static void removeElement(Element e) {

		elements.remove(e.getName()).exit(e);

	}

	private static void drawMaze() {

		int fieldWidth = 0;
		for (Field f = fieldReference; f != null; f = f.getNeighbour(Direction.EAST)) {
			fieldWidth++;
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
							Field f = fieldReference;
							for (int i = 0; i < x; i++) {
								f = f.getNeighbour(Direction.EAST);
							}
							for (int i = 0; i < y; i++) {
								f = f.getNeighbour(Direction.SOUTH);
							}

							addElement(d, f);

							System.out
									.println("Created door '" + d.getName() + "' in (" + x + ", " + y + ") position.");
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

							Scale s = new Scale(name, (Door) elements.get(doorName).getElement(doorName), weight);
							Field f = fieldReference;
							for (int i = 0; i < x; i++) {
								f = f.getNeighbour(Direction.EAST);
							}
							for (int i = 0; i < y; i++) {
								f = f.getNeighbour(Direction.SOUTH);
							}

							addElement(s, f);

							System.out.println("Created scale '" + name + "' in (" + x + ", " + y
									+ ") position. Weight limit: " + weight + ".");
							drawMaze();

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
