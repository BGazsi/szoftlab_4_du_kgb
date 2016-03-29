package element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import element.movable.Colonel;
import game.Game;

// Mérleget szimbolizáló osztály
public class Scale extends Element {

	// mérleghez tartozó ajtó referenciája
	private Door door;
	// mérlegen lévő elementek
	private Set<Element> elements;

	public Scale(Door door) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);

		this.door = door;
		this.elements = new HashSet<Element>();
	}

	// Ezredessel való egy mezőre kerülés
	@Override
	public void meet(Colonel c) {

		elements.add(c);
		door.open();
	}

	// Dobozzal való egy mezőre kerülés
	@Override
	public void meet(Box b) {

		elements.add(b);
		door.open();
	}

	// Ezredessel való "búcsúzkodás"
	@Override
	public void leave(Colonel c) {

		elements.remove(c);

		if (elements.isEmpty()) {
			door.close();
		}
	}

	// Doboztól való elbúcsúzás
	@Override
	public void leave(Box b) {

		elements.remove(b);

		if (elements.isEmpty()) {
			door.close();
		}
	}
}