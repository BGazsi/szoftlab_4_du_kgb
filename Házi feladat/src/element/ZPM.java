package element;

import element.movable.Colonel;

// ZPMet reprezentáló osztály
public class ZPM extends Element {

	// Ezredessel való egy mezőre kerülés
	@Override
	public void meet(Colonel c) {

		c.pickUp(this);
	}
}
