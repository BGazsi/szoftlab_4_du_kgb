package element.movable;

import element.Element;
import enums.Direction;
import field.Field;

// "Mozgásra képes" osztályok ősosztálya
public abstract class Movable extends Element {

	// aktuális pozíció
	protected Field position;
	// irány
	protected Direction direction;

	public Movable(String name, Field position, Direction direction) {

		super(name);

		this.position = position;
		this.direction = direction;

	}

	// A mozgást leíró függvény
	public abstract void step();

	// getter a pozícióhoz
	public Field getPosition() {

		return position;
	}
}