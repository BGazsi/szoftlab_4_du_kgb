package field;

import element.Box;
import element.Element;
import element.movable.Bullet;
import element.movable.Colonel;

public class Empty extends Field {

	@Override
	public void enter(Colonel c) {

		c.getPosition().exit(c);
		c.setPosition(this);

		for (Element element : elements) {
			element.meet(c);
		}

		elements.add(c);
	}

	@Override
	public void enter(Bullet b) {

		b.getPosition().exit(b);
		b.setPosition(this);
		elements.add(b);
	}

	@Override
	public void enter(Box b) {

		for (Element element : elements) {
			element.meet(b);
		}

		elements.add(b);
	}

	@Override
	public void exit(Colonel c) {

		for (Element element : elements) {
			element.divide(c);
		}

		elements.remove(c);
	}

	@Override
	public void exit(Bullet b) {

		elements.remove(b);
	}

	@Override
	public void exit(Box b) {

		for (Element element : elements) {
			element.divide(b);
		}

		elements.remove(b);
	}
}
