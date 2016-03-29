package element;

import element.movable.Colonel;

// Szakadékot szombolizáló osztály
public class Gap extends Element {

	// Ezredessel való egy mezőre kerülés
	@Override
	public void meet(Colonel c) {

		c.die();
	}

	// Dobozzal való egy mezőre kerülés
	@Override
	public void meet(Box b) {

		b.destroy();
	}
}
