package element.movable;

import java.util.ArrayList;
import java.util.Arrays;

import element.Element;
import enums.Direction;
import enums.PortalColour;
import field.Field;
import game.Game;

public class Bullet extends Movable {

	private PortalColour portalColour;

	public Bullet(Field position, Direction direction, PortalColour portalColour) {

		super(position, direction);
		this.portalColour = portalColour;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 2);
	}

	@Override
	public void step() {

		Field nextField = position.getNeighbour(direction);

		nextField.enter(this);
		position.exit(this);
		position = nextField;
	}

	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	public void destroy() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// TODO
	}

	public PortalColour getPortalColour() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		return portalColour;
	}
}
