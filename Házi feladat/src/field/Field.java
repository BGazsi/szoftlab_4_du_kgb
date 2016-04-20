package field;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import element.Box;
import element.Door;
import element.Element;
import element.Gap;
import element.Scale;
import element.Wall;
import element.ZPM;
import element.movable.Movable;
import enums.Direction;

// Mezőt reprezentáló osztály
public class Field {

	// mező szomszédai
	private Map<Direction, Field> neighbours;
	// mezőn lévő elementek
	private Set<Element> elements;

	public Field() {

		this.neighbours = new HashMap<Direction, Field>();
		this.elements = new HashSet<Element>();

	}

	// Mezőre történő lépés
	public void enter(Element e) {

		for (Element element : elements) {

			e.collide(element);
		}

		elements.add(e);
	}

	// Mező elhagyása
	public void exit(Element e) {

		for (Element element : elements) {

			e.sunder(element);
		}

		elements.remove(e);
	}

	// A fieldet szimbolizáló karakter
	public String getStatusString() {

		int priority = 0;
		String c = " ";

		for (Element e : elements) {

			if (e instanceof Gap && 1 > priority) {
				priority = 1;
				c = "X";
			} else if (e instanceof Door && 1 > priority) {
				priority = 1;
				Door d = (Door) e;
				if (d.isOpened()) {
					c = "\\";
				} else {
					c = "|";
				}
			} else if ((e instanceof Wall || e instanceof Box) && 1 > priority) {
				priority = 1;
				c = "" + e.getClass().getSimpleName().charAt(0);
			} else if (e instanceof ZPM && 2 > priority) {
				priority = 2;
				c = "Z";
			} else if (e instanceof Scale && 2 > priority) {
				priority = 2;
				Scale s = (Scale) e;
				c = Integer.toString(s.allWeight());
			} else if (e instanceof Movable && 3 > priority) {
				priority = 3;
				c = "" + e.getClass().getSimpleName().charAt(0);
			}
		}

		// TODO Portals character

		return c;
	}

	public Element getElement(String name) {

		for (Element e : elements) {
			if (e.getName().equals(name)) {
				return e;
			}
		}

		return null;
	}

	// Adott irányban lévő szomszédok elkérése
	public Field getNeighbour(Direction d) {

		return neighbours.get(d);
	}

	// Adott irányba lévő szomszédok beállítása
	public void setNeighbour(Direction d, Field f) {

		neighbours.put(d, f);
	}
}
