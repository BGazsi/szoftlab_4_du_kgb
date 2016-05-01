package model.element;

import model.element.movable.Bullet;
import model.element.movable.player.Player;
import model.portal.Portals;

// Falat reprezentáló osztály
public class Wall extends Element {

	// lőhető, azaz speciális-e
	private boolean isShootable;

	public Wall(boolean isShootable) {

		this.isShootable = isShootable;
	}

	// Playerrel való egy mezőre kerülés
	@Override
	public void meet(Player p) {

		if (Portals.findPortal(this, p.getPosition()) != null) {

			Portals.send(p, this);

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
