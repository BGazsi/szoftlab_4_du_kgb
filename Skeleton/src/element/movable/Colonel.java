package element.movable;

import element.Box;
import element.Element;
import element.ZPM;
import enums.Direction;
import enums.PortalColour;
import field.Field;

public class Colonel extends Movable {

	private Element element;
	private int ZMPcount;

	public Colonel(Field position, Direction direction) {

		super(position, direction);
	}

	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	public Bullet shoot(PortalColour color) {

		return new Bullet(position, direction, color);
	}

	public void stepBack() {

		direction = direction.getOpposite();
		step();
		direction = direction.getOpposite();
	}

	public void pickUp(Box b) {

		if (element == null) {

			element = b;
			position.exit(b);
		}
	}

	public void pickUp(ZPM z) {

		ZMPcount++;
		position.exit(z);
	}

	public void putDown(Element e) {

		position.enter(e);
		element = null;
	}

	public void die() {

		// TODO
	}

}
