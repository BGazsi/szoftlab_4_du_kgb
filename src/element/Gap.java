package element;

import element.movable.Colonel;

public class Gap extends Element {

	@Override
	public void meet(Colonel c) {

		c.die();
	}

	@Override
	public void meet(Box b) {

		b.destroy();
	}
}
