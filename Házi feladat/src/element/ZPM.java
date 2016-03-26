package element;

import element.movable.Colonel;

public class ZPM extends Element {

	@Override
	public void meet(Colonel c) {

		c.pickUp(this);
	}
}
