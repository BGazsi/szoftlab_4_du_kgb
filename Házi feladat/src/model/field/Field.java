package model.field;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.element.Box;
import model.element.Door;
import model.element.Element;
import model.element.Gap;
import model.element.Scale;
import model.element.Wall;
import model.element.ZPM;
import model.element.movable.Movable;
import model.enums.Direction;
import model.enums.PortalColour;
import model.portal.Portals;

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

		Set<Element> elementsCopy = new HashSet<Element>(elements);

		elements.add(e);

		for (Element element : elementsCopy) {

			e.collide(element);
		}
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
			} else if (e instanceof Wall && 1 > priority) {
				priority = 1;
				Wall w = (Wall) e;
				PortalColour pc = Portals.findPortal(w, null);

				if (pc != null) {
					c = ("" + pc.toString().charAt(0)).toLowerCase();
				} else {
					c = "" + e.getClass().getSimpleName().charAt(0);
				}
			} else if (e instanceof Box && 1 > priority) {
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

		return c;
	}

	public boolean contains(Element e) {
		return elements.contains(e);
	}

	// Adott irányban lévő szomszédok elkérése
	public Field getNeighbour(Direction d) {

		return neighbours.get(d);
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	// Adott irányba lévő szomszédok beállítása
	public void setNeighbour(Direction d, Field f) {

		neighbours.put(d, f);
	}

}