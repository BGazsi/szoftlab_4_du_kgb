package element.movable.player;

import element.ZPM;
import enums.Direction;
import field.Field;
import game.Game;

// Az Ezredest reprezentáló osztály
public class Colonel extends Player {

	public Colonel(String name, int weight, Field position, Direction direction) {
		super(name, weight, position, direction);

	}

	// Ezredes ZPM felvétele
	@Override
	public void pickUpZPM(ZPM z) {

		ZMPcount++;
		Game.removeElement(z);

		if (ZMPcount % 2 == 0) {
			Game.addElement(new ZPM(null), null, true);
		}
	}

}
