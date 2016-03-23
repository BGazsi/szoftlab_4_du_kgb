package field;

import element.movable.Bullet;
import element.movable.Colonel;

public class Door extends Field {

	private boolean isOpened;

	public Door() {

		this.isOpened = false;
	}

	@Override
	public void enter(Colonel c) {

		if (isOpened) {

			c.getPosition().exit(c);
			c.setPosition(this);
			elements.add(c);
		}
	}

	@Override
	public void enter(Bullet b) {

		if (isOpened) {

			b.getPosition().exit(b);
			b.setPosition(this);
			elements.add(b);
		}
	}

	@Override
	public void exit(Colonel c) {

		elements.remove(c);
	}

	@Override
	public void exit(Bullet b) {

		elements.remove(b);
	}

	public void open() {

		isOpened = true;
	}

	public void close() {

		isOpened = false;
	}
}
