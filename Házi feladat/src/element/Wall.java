package element;

import java.util.ArrayList;
import java.util.Arrays;

import element.movable.Bullet;
import element.movable.player.Player;
import game.Game;
import portal.Portals;

// Falat reprezentáló osztály
public class Wall extends Element {

	// lőhető, azaz speciális-e
	private boolean isShootable;

	public Wall(boolean isShootable) {

		this.isShootable = isShootable;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// Playerrel való egy mezőre kerülés
	@Override
	public void meet(Player p) {

		if (Portals.isPortal(this, p.getPosition())) {

			Portals.send(p);

		} else {

			p.stay();
		}
	}

	// Lövedékkel való egy mezőre kerülés
	@Override
	public void meet(Bullet b) {

		if (isShootable) {
			Portals.createPortal(b.getPortalColour(), this, b.getPosition());
		}

		b.destroy();
	}
}
