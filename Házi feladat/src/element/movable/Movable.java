package element.movable;

import java.util.ArrayList;
import java.util.Arrays;

import element.Element;
import enums.Direction;
import field.Field;
import game.Game;

public abstract class Movable extends Element {

	protected Field position;
	protected Direction direction;

	public Movable(Field position, Direction direction) {

		this.position = position;
		this.direction = direction;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	public abstract void step();

	public Field getPosition() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		return position;
	}
}