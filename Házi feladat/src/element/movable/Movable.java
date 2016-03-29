package element.movable;

import java.util.ArrayList;
import java.util.Arrays;

import element.Element;
import enums.Direction;
import field.Field;
import game.Game;

// "Mozgásra képes" osztályok ősosztálya
public abstract class Movable extends Element {

	// aktuális pozíció
	protected Field position;
	// irány
	protected Direction direction;

	public Movable(Field position, Direction direction) {

		this.position = position;
		this.direction = direction;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// A mozgást leíró függvény
	public abstract void step();

	// getter a pozícióhoz
	public Field getPosition() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		return position;
	}
}