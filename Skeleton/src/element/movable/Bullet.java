package element.movable;

import element.Element;
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
	public void collide(Element e) {

		e.meet(this);
	}

	public void destroy() {

		// TODO
	}

	public PortalColour getPortalColour() {

		return portalColour;
	}

}
