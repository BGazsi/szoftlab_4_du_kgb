package field;

import element.Box;
import element.movable.Bullet;
import element.movable.Colonel;

public class Gap extends Field {

	@Override
	public void enter(Colonel c) {

		c.getPosition().exit(c);
		c.setPosition(this);

		c.kill();
	}

	@Override
	public void enter(Bullet b) {

		b.getPosition().exit(b);
		b.setPosition(this);
		elements.add(b);
	}

	@Override
	public void enter(Box b) {

		b.destroy();
	}

	@Override
	public void exit(Bullet b) {

		elements.remove(b);
	}

}
