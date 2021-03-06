package model.element.movable;

import controller.Game;
import model.element.Element;
import model.enums.Direction;
import model.enums.PortalColour;
import model.field.Field;

// Magát a lövedéket reprezentáló osztály
public class Bullet extends Movable {

	// a golyó által létrejövő portál színe
	private PortalColour portalColour;

	public Bullet(Field position, Direction direction, PortalColour portalColour) {

		super(position, direction);
		this.portalColour = portalColour;

	}

	// A golyó haladásáért felelős függvény
	@Override
	public void step() {

		// elkéri az adott irányba lévő mezőt
		Field nextField = position.getNeighbour(direction);

		// régi mezőről lelép
		position.exit(this);
		// az új mezőre rélép
		nextField.enter(this);
		position = nextField;

	}

	// Egy mezőn lévő elementel való ütközés függvénye
	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	// A lövedék megsemmisülésére vonatkozó függvény
	public void destroy() {

		killed = true;
		Game.removeElement(this);
	}

	// Visszatér a lövedék által létrjövő portál színével
	public PortalColour getPortalColour() {

		return portalColour;
	}

	@Override
	public void meet(Replicator r) {

		destroy();
		r.die();
	}

}
