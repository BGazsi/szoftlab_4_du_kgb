package element;

import java.util.ArrayList;
import java.util.Arrays;

import element.movable.Bullet;
import element.movable.Colonel;
import game.Game;
import portal.Portals;

public class Wall extends Element {

	private boolean isShootable;

	public Wall(boolean isShootable) {

		this.isShootable = isShootable;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	@Override
	public void meet(Colonel c) {

		if (Portals.isPortal(this, c.getPosition())) {

			Portals.send(c);

		} else {

			c.stay();
		}
	}

	@Override
	public void meet(Bullet b) {

		if (isShootable) {
			Portals.createPortal(b.getPortalColour(), this, b.getPosition());
		}

		b.destroy();
	}
}
