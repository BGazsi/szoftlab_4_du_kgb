package element.movable;

import element.Element;
import enums.Direction;
import field.Field;

public abstract class Movable extends Element {

	protected Field position;
	protected Direction direction;

	public Movable(Field position, Direction direction) {

		this.position = position;
		this.direction = direction;
	}

	public abstract void step();

	public Field getPosition() {

		return position;
	}

	public Direction getDirection() {

		return direction;
	}

	public void setPosition(Field position) {

		this.position = position;
	}
}