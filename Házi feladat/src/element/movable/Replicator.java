package element.movable;

import element.Element;
import enums.Direction;
import field.Field;

public class Replicator extends Movable {

	private boolean needToStay;

	public Replicator(Field position, Direction direction) {
		super(position, direction);

		this.needToStay = false;
	}

	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	@Override
	public void step() {

		// FIXME auto

		Field nextField = position.getNeighbour(direction);

		nextField.enter(this);
		if (needToStay) {

			needToStay = false;
			nextField.exit(this);

		} else {

			position.exit(this);
			position = nextField;
		}
	}

	public void stay() {

		needToStay = true;
	}

	public void die() {

		game.removeElement(this);
	}

	@Override
	public void meet(Bullet b) {

		die();
	}
}
