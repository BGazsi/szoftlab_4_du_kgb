package element;

import element.movable.Bullet;
import element.movable.Colonel;

public abstract class Element {

	public void collide(Element e) {

		// NOTHING TO DO
	}

	public void meet(Colonel c) {

		// NOTHING TO DO
	}

	public void meet(Bullet b) {

		// NOTHING TO DO
	}

	public void meet(Box b) {

		// NOTHING TO DO
	}

	public void sunder(Element e) {

		// NOTHING TO DO
	}

	public void leave(Colonel c) {

		// NOTHING TO DO
	}

	public void leave(Box b) {

		// NOTHING TO DO
	}
}
