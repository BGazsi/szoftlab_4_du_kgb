package element.movable.player;

import element.ZPM;
import enums.Direction;
import field.Field;
import game.Game;

public class Jaffa extends Player {

	public Jaffa(String name, int weight, Field position, Direction direction) {
		super(name, weight, position, direction);
	}

	@Override
	public void pickUpZPM(ZPM z) {

		ZMPcount++;
		Game.removeElement(z);
	}
}
