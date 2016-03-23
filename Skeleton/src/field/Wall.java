package field;

import element.movable.Bullet;
import singleton.Portals;

public class Wall extends Field {

	private boolean isShootable;

	public Wall(boolean isShootable) {

		this.isShootable = isShootable;
	}

	@Override
	public void enter(Bullet b) {

		b.getPosition().exit(b);
		b.setPosition(this);

		if (isShootable) {

			Field bulletField = b.getPosition();
			Field wallField = bulletField.getNeighbour(b.getDirection());

			Portals.createPortal(b.getPortalColour(), wallField, bulletField);
		}
	}
}
