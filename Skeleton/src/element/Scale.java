package element;

import java.util.HashSet;
import java.util.Set;

import element.movable.Colonel;

public class Scale extends Element {

	private Door door;
	private Set<Element> elements;

	public Scale(Door door) {

		this.door = door;
		this.elements = new HashSet<Element>();
	}

	@Override
	public void meet(Colonel c) {

		elements.add(c);
		door.open();
	}

	@Override
	public void meet(Box b) {

		elements.add(b);
		door.open();
	}

	@Override
	public void leave(Colonel c) {

		elements.remove(c);

		if (elements.isEmpty()) {
			door.close();
		}
	}

	@Override
	public void leave(Box b) {

		elements.remove(b);

		if (elements.isEmpty()) {
			door.close();
		}
	}
}