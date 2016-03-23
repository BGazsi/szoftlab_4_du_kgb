package element.movable;

import enums.Direction;
import enums.PortalColour;
import field.Field;

public class Bullet extends Movable {

	private PortalColour portalColour;

	public Bullet(Field position, Direction direction, PortalColour portalColour) {

		super(position, direction);
		this.portalColour = portalColour;
	}

	@Override
	public void step() {

		Field nextField = position.getNeighbour(direction);
		nextField.enter(this);
	}

	public PortalColour getPortalColour() {

		return portalColour;
	}
}
