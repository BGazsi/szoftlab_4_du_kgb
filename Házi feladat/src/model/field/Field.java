package model.field;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.element.Box;
import model.element.Door;
import model.element.Element;
import model.element.Scale;
import model.element.Wall;
import model.element.movable.Bullet;
import model.element.movable.Movable;
import model.enums.Direction;
import model.enums.FieldStatus;
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

	// A fieldet szimbolizáló státusz
	public FieldStatus getStatus() {

		FieldStatus result = FieldStatus.UNKNOWN;

		for (Element e : elements) {

			FieldStatus fieldStatus = FieldStatus.valueOf(e.getClass().getSimpleName().toUpperCase());

			if (e instanceof Wall) {

				Wall wall = (Wall) e;

				for (Direction direction : Direction.values()) {

					PortalColour portalColour = Portals.findPortal(wall, getNeighbour(direction));
					if (portalColour != null) {

						if (fieldStatus == FieldStatus.WALL) {

							fieldStatus = FieldStatus.PORTAL;
							fieldStatus.clear();
						}

						fieldStatus.put(direction.toString(), portalColour);
					}
				}

				if (fieldStatus == FieldStatus.WALL) {
					fieldStatus.put("shootable", wall.isShootable());
				}

			} else if (e instanceof Bullet) {

				Bullet bullet = (Bullet) e;
				fieldStatus.put("colour", bullet.getPortalColour());
				fieldStatus.put("direction", bullet.getDirection());

			} else if (e instanceof Movable) {

				Movable movable = (Movable) e;
				fieldStatus.put("direction", movable.getDirection());

			} else if (e instanceof Box) {

				Box box = (Box) e;
				fieldStatus.put("weight", box.getWeight());

			} else if (e instanceof Door) {

				Door door = (Door) e;
				fieldStatus.put("opened", door.isOpened());
			} else if (e instanceof Scale) {

				Scale scale = (Scale) e;
				fieldStatus.put("weightlimit", scale.getWeightLimit());
				fieldStatus.put("allweight", scale.allWeight());
			}

			if (fieldStatus.getPriority() > result.getPriority()) {
				result = fieldStatus;
			}
		}

		return result;
	}

	public boolean contains(Element e) {
		return elements.contains(e);
	}

	// Adott irányban lévő szomszédok elkérése
	public Field getNeighbour(Direction d) {

		return neighbours.get(d);
	}

	// Adott irányban lévő step-edik szomszéd elkérése
	public Field getNeighbour(int step, Direction d) {

		Field field = this;

		for (int i = 0; i < step; i++) {
			field = field.getNeighbour(d);
		}

		return field;
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	// Adott irányba lévő szomszédok beállítása
	public void setNeighbour(Direction d, Field f) {

		neighbours.put(d, f);
	}

}