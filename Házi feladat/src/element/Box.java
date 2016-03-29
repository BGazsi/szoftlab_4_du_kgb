package element;

import java.util.ArrayList;
import java.util.Arrays;

import element.movable.Colonel;
import game.Game;

// A dobozt szimbolizáló függvény
public class Box extends Element {

	public Box() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// Egy elementtel való ütközéskor hívódó függvény
	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	// Ezredessel való egy mezőre kerülés
	@Override
	public void meet(Colonel c) {

		c.pickUp(this);
	}

	// Doboz megsemmisülése
	public void destroy() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// TODO
	}
}
