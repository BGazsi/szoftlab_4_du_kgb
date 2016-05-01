package model.element.movable.player;

import controller.Game;
import model.element.Box;
import model.element.Element;
import model.element.ZPM;
import model.element.movable.Bullet;
import model.element.movable.Movable;
import model.enums.Direction;
import model.enums.PortalColour;
import model.field.Field;

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

	public abstract void pickUpZPM(ZPM z);

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

	@Override
	public void meet(Player p) {

		p.stay();
	}

	public void shoot(PortalColour color) {

		Bullet b = new Bullet(position, direction, color);

		Game.addElement(b, position);
		b.step();
	}

	public void pickUpBox(Box b) {

		if (box == null) {

			box = b;
			Game.removeElement(b);
		}
	}

	public void putDownBox() {

		if (box != null) {

			Game.addElement(box, position.getNeighbour(direction));
			box = null;
		}
	}

	public void die() {

		Game.removeElement(this);
	}

	@Override
	public int getWeight() {

		return box == null ? weight : weight + box.getWeight();
	}

	public Box getBox() {
		return box;
	}
}
