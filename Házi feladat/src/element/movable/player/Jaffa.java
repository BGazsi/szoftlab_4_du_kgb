package element.movable.player;

import enums.Direction;
import field.Field;

public class Jaffa extends Player {

	public Jaffa(String name, int weight, Field position, Direction direction) {
		super(name, weight, position, direction);
	}

	@Override
	public void pickUpZPM() {

		ZMPcount++;
	}
}
