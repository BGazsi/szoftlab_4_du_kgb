package element;

import element.movable.Bullet;
import element.movable.Colonel;

public class Door extends Element {

	private boolean isOpened;

	public Door() {

		this.isOpened = false;
	}

	public void open() {

		isOpened = true;
	}

	public void close() {

		isOpened = false;
	}
	
	@Override
	public void meet(Colonel c) {
		if(!isOpened) {
			c.stepBack();
		}
	}
	
	@Override
	public void meet(Bullet b) {
		if(!isOpened) {
			b.destroy();
		}
	}
	
	@Override
	public void meet(Box b) {
		// TODO
	}
}
