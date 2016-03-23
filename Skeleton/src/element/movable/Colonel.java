package element.movable;

import java.util.HashSet;
import java.util.Set;

import element.Element;
import enums.Direction;
import field.Field;

public class Colonel extends Movable {

	private Set<Element> elements;

	public Colonel(Field position, Direction direction) {

		super(position, direction);
		this.elements = new HashSet<Element>();
	}

	@Override
	public void step() {

		Field nextField = position.getNeighbour(direction);
		nextField.enter(this);
	}

	public void moveBox() {

		// TODO
		Field field = position.getNeighbour(direction);

		if (elements.size() == 0) {

			field.pickUpBox();

		} else {

			field.putDownBox(null);
		}
	}

	public void kill() {

		// TODO
	}
}
