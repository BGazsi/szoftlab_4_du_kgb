package element.movable.player;

import element.Box;
import element.Element;
import element.ZPM;
import element.movable.Bullet;
import element.movable.Movable;
import enums.Direction;
import enums.PortalColour;
import field.Field;
import game.Game;

public abstract class Player extends Movable {

	protected int weight;
	protected Box box;
	protected int ZMPcount;
	protected boolean needToStay;

	public Player(String name, int weight, Field position, Direction direction) {

		super(name, position, direction);

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

		Bullet b = new Bullet(null, position, direction, color);

		Game.addElement(b, position, false);
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

			Game.addElement(box, position.getNeighbour(direction), false);
			box = null;
		}
	}

	public void die() {

		Game.removeElement(this);
	}

	@Override
	public int getWeight() {

		return weight;
	}

	public Box getBox() {
		return box;
	}
}
