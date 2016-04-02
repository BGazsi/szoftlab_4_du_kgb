package element.movable.player;

import element.ZPM;
import enums.Direction;
import field.Field;

public class Jaffa extends Player {

	public Jaffa(int weight, Field position, Direction direction) {
		super(weight, position, direction);
	}

	@Override
	public void pickUp(ZPM z) {

		ZMPcount++;
	}
}
