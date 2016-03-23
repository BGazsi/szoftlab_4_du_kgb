package field;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import element.Box;
import element.Element;
import element.movable.Bullet;
import element.movable.Colonel;
import enums.Direction;

public abstract class Field {

	protected Map<Direction, Field> neighbours;
	protected Set<Element> elements;

	public Field() {

		this.neighbours = new HashMap<Direction, Field>();
		this.elements = new HashSet<Element>();
	}

	public void enter(Colonel c) {

		// NOTHING TO DO
	}

	public void enter(Bullet b) {

		// NOTHING TO DO
	}

	public void enter(Box b) {

		// NOTHING TO DO
	}

	public void exit(Colonel c) {

		// NOTHING TO DO
	}

	public void exit(Bullet b) {

		// NOTHING TO DO
	}

	public void exit(Box b) {

		// NOTHING TO DO
	}

	public Box pickUpBox() {

		// TODO
		return null;
	}

	public void putDownBox(Box b) {

		// TODO
	}

	public void addElement(Element e) {

		elements.add(e);
	}

	public void removeElement(Element e) {

		elements.remove(e);
	}

	public Field getNeighbour(Direction d) {

		return neighbours.get(d);
	}

	public void setNeighbour(Direction d, Field f) {

		neighbours.put(d, f);
	}
}
