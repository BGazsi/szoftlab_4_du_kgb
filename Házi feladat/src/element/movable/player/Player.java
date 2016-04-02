package element.movable.player;

import element.Box;
import element.Element;
import element.ZPM;
import element.movable.Bullet;
import element.movable.Movable;
import enums.Direction;
import enums.PortalColour;
import field.Field;

public abstract class Player extends Movable {

	protected int weight;
	protected Box box;
	protected int ZMPcount;
	protected boolean needToStay;

	public Player(int weight, Field position, Direction direction) {

		super(position, direction);

		this.weight = weight;
		this.needToStay = false;
	}

	public abstract void pickUp(ZPM z);

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

		return new Bullet(position, direction, color);
	}

	public void pickUpBox() {

		if (box == null) {

			Field nextField = position.getNeighbour(direction);
			box = nextField.popBox();
		}
	}

	public void putDownBox() {

		Field nextField = position.getNeighbour(direction);
		nextField.pushBox(box);
		box = null;
	}

	public void die() {

		game.removeElement(this);
	}

	public Box getBox() {

		return box;
	}

	@Override
	public int getWeight() {

		return weight;
	}
}
