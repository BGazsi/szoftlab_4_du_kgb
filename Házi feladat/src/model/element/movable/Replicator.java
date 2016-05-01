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

	public void die() {

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
