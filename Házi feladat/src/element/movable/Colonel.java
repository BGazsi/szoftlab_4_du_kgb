package element.movable;

import java.util.ArrayList;
import java.util.Arrays;

import element.Box;
import element.Element;
import element.ZPM;
import enums.Direction;
import enums.PortalColour;
import field.Field;
import game.Game;

public class Colonel extends Movable {

	private Element element;
	private int ZMPcount;
	private boolean needToStay;

	public Colonel(Field position, Direction direction) {

		super(position, direction);
		this.needToStay = false;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

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

	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	@Override
	public void sunder(Element e) {

		e.leave(this);
	}

	public Bullet shoot(PortalColour color) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		return new Bullet(position, direction, color);
	}

	public void stay() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		needToStay = true;
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

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// TODO
	}

	public Element getElement() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		return element;
	}
}
