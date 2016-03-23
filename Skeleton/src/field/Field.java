package field;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import element.Element;
import enums.Direction;

public class Field {

	protected Map<Direction, Field> neighbours;
	protected Set<Element> elements;

	public Field() {

		this.neighbours = new HashMap<Direction, Field>();
		this.elements = new HashSet<Element>();
	}

	public void enter(Element e) {

		for (Element element : elements) {

			e.collide(element);
		}

		elements.add(e);
	}

	public void exit(Element e) {

		// TODO
		elements.remove(e);
	}

	public Field getNeighbour(Direction d) {

		return neighbours.get(d);
	}

	public void setNeighbour(Direction d, Field f) {

		neighbours.put(d, f);
	}
}
