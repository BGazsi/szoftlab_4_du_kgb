package element;

import element.movable.Colonel;

public class Box extends Element {

	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	@Override
	public void meet(Colonel c) {

		c.pickUp(this);
	}

	public void destroy() {

		// TODO
	}
}
