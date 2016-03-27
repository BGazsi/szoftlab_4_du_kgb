package element;

import java.util.ArrayList;
import java.util.Arrays;

import element.movable.Colonel;
import game.Game;

public class Box extends Element {

	public Box() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	@Override
	public void meet(Colonel c) {

		c.pickUp(this);
	}

	public void destroy() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// TODO
	}
}
