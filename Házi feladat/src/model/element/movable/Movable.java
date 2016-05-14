package model.element.movable;

import model.element.Element;
import model.enums.Direction;
import model.field.Field;

// "Mozgásra képes" osztályok ősosztálya
public abstract class Movable extends Element {

	// aktuális pozíció
	protected Field position;
	// irány
	protected Direction direction;
	// él-e még
	protected boolean killed;

	public Movable(Field position, Direction direction) {

		this.position = position;
		this.direction = direction;
		this.killed = false;
	}

	// A mozgást leíró függvény
	public abstract void step();

	// getter a pozícióhoz
	public Field getPosition() {

		return position;
	}

	public Direction getDirection() {
		return direction;
	}

	public boolean isKilled() {
		return killed;
	}

	public void setPosition(Field position) {
		this.position = position;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}