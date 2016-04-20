package element.movable.player;

import element.Element;
import element.movable.Bullet;
import element.movable.Movable;
import enums.Direction;
import enums.PortalColour;
import field.Field;
import game.Game;

public abstract class Player extends Movable {

	protected int weight;
	protected Element element;
	protected int ZMPcount;
	protected boolean needToStay;

	public Player(String name, int weight, Field position, Direction direction) {

		super(name, position, direction);

		this.weight = weight;
		this.needToStay = false;
	}

	public abstract void pickUpZPM();

	@Override
	public void step() {

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

	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	@Override
	public void sunder(Element e) {

		e.leave(this);
	}

	public Bullet shoot(PortalColour color) {

		return new Bullet(null, position, direction, color);
	}

	public void pickUp(Element e) {

		if (element == null) {
			element = e;
			position.exit(e);
		}
	}

	public void putDown() {

		if (element != null) {

			position.enter(element);
			element = null;
		}
	}

	public void die() {

		Game.removeElement(this);
	}

	@Override
	public int getWeight() {

		return weight;
	}
}
