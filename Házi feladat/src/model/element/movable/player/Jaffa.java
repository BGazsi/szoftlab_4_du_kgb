package model.element.movable.player;

import controller.Game;
import model.element.ZPM;
import model.enums.Direction;
import model.field.Field;

public class Jaffa extends Player {

	public Jaffa(int weight, Field position, Direction direction) {
		super(weight, position, direction);
	}

	@Override
	public void pickUpZPM(ZPM z) {

		ZMPcount++;
		Game.removeElement(z);
	}
}
