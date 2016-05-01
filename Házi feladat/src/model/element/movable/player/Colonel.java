package model.element.movable.player;

import controller.Game;
import model.element.ZPM;
import model.enums.Direction;
import model.field.Field;

// Az Ezredest reprezentáló osztály
public class Colonel extends Player {

	public Colonel(int weight, Field position, Direction direction) {

		super(weight, position, direction);
	}

	// Ezredes ZPM felvétele
	@Override
	public void pickUpZPM(ZPM z) {

		ZMPcount++;
		Game.removeElement(z);

		if (ZMPcount % 2 == 0) {
			Game.addElement(new ZPM(), null);
		}
	}
}
