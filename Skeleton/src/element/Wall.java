package element;

import element.movable.Bullet;
import element.movable.Colonel;
import singleton.Portals;

public class Wall extends Element {

	private boolean isShootable;

	public Wall(boolean isShootable) {

		this.isShootable = isShootable;
	}

	@Override
	public void meet(Colonel c) {

		if (Portals.isPortal(this, c.getPosition())) {

			Portals.send(c);

		} else {

			c.stepBack();
		}
	}

	@Override
	public void meet(Bullet b) {

		if (isShootable) {
			Portals.createPortal(b.getPortalColour(), this, b.getPosition());
		}

		b.destroy();
	}

	// public void enter(Bullet b) {
	//
	// b.getPosition().exit(b);
	// b.setPosition(this);
	//
	// if (isShootable) {
	//
	// Field bulletField = b.getPosition();
	// Field wallField = bulletField.getNeighbour(b.getDirection());
	//
	// Portals.createPortal(b.getPortalColour(), wallField, bulletField);
	// }
	// }
}