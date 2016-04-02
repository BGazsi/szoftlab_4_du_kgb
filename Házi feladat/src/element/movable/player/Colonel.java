package element.movable.player;

import java.util.ArrayList;
import java.util.Arrays;

import element.ZPM;
import enums.Direction;
import field.Field;
import game.Game;

// Az Ezredest reprezentáló osztály
public class Colonel extends Player {

	public Colonel(int weight, Field position, Direction direction) {
		super(weight, position, direction);

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// Ezredes ZPM felvétele
	@Override
	public void pickUp(ZPM z) {

		ZMPcount++;

		if (ZMPcount == 2) {
			game.addElement(new ZPM(), null);
		}
	}

}
