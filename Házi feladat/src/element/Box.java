package element;

import java.util.ArrayList;
import java.util.Arrays;

import game.Game;

// A dobozt szimbolizáló függvény
public class Box extends Element {

	// a doboz súlya
	protected int weight;

	public Box(int weight) {

		this.weight = weight;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// Egy elementtel való ütközéskor hívódó függvény
	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	// Doboz megsemmisülése
	public void destroy() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		game.removeElement(this);
	}

	@Override
	public int getWeight() {

		return weight;
	}
}
