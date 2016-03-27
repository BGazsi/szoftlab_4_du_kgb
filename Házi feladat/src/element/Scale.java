package element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import element.movable.Colonel;
import game.Game;

public class Scale extends Element {

	private Door door;
	private Set<Element> elements;

	public Scale(Door door) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);

		this.door = door;
		this.elements = new HashSet<Element>();
	}

	@Override
	public void meet(Colonel c) {

		elements.add(c);
		door.open();
	}

	@Override
	public void meet(Box b) {

		elements.add(b);
		door.open();
	}

	@Override
	public void leave(Colonel c) {

		elements.remove(c);

		if (elements.isEmpty()) {
			door.close();
		}
	}

	@Override
	public void leave(Box b) {

		elements.remove(b);

		if (elements.isEmpty()) {
			door.close();
		}
	}
}