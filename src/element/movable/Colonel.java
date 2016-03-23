package element.movable;

import java.util.HashSet;
import java.util.Set;

import element.Box;
import element.Element;
import element.ZPM;
import enums.Direction;
import enums.PortalColour;
import field.Field;

public class Colonel extends Movable {

	private Element element;
	private int zpmCount;

	public Colonel(Field position, Direction direction) {

		super(position, direction);
	}

	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	public void moveBox() {
		
		// TODO
	}

	public void pickUp(Box b) {
		if(element == null) {
			element = b;
			position.exit(b);
		}
	}
	
	public void pickUp(ZPM z) {
		this.zpmCount++;
		position.exit(z);
	}

	public void putDown(Element e) {
		position.enter(e);
		this.element = null;
	}

	public void die() {

		// TODO
	}
	
	public Bullet shoot(PortalColour colour) {
		Bullet b = new Bullet(this.position, this.direction, colour);
		return b;
	}
	
	public void stepBack() {
		this.direction = this.direction.getOpposite();
		step();
		this.direction = this.direction.getOpposite();
	}

}
