package model.element.movable;

import controller.Game;
import model.element.Element;
import model.element.movable.player.Player;
import model.enums.Direction;
import model.field.Field;

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

		Field nextField = position.getNeighbour(direction);

		position.exit(this);
		nextField.enter(this);
		if (needToStay) {

			needToStay = false;
			nextField.exit(this);
			position.enter(this);

		} else {

			position = nextField;
		}
	}

	public void stay() {

		needToStay = true;
	}

	public void die() {

		killed = true;
		Game.removeElement(this);
	}

	@Override
	public void meet(Player p) {

		p.stay();
	}

	@Override
	public void meet(Bullet b) {

		die();
		b.destroy();
	}
}
