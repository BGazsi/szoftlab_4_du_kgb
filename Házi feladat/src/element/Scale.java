package element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import element.movable.player.Player;
import game.Game;

// Mérleget szimbolizáló osztály
public class Scale extends Element {

	// mérleghez tartozó ajtó referenciája
	private Door door;
	//
	private int weightLimit;
	// mérlegen lévő elementek
	private Set<Element> elements;

	public Scale(Door door, int weightLimit) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);

		this.door = door;
		this.weightLimit = weightLimit;
		this.elements = new HashSet<Element>();
	}

	// az összes mérlegen lévő súly
	private int allWeight() {

		int sum = 0;
		for (Element e : elements) {
			sum += e.getWeight();
		}
		return sum;
	}

	// Playerrel való egy mezőre kerülés
	@Override
	public void meet(Player p) {

		elements.add(p);
		if (allWeight() >= weightLimit) {
			door.open();
		}
	}

	// Dobozzal való egy mezőre kerülés
	@Override
	public void meet(Box b) {

		elements.add(b);
		if (allWeight() >= weightLimit) {
			door.open();
		}
	}

	// Playerrel való "búcsúzkodás"
	@Override
	public void leave(Player p) {

		elements.remove(p);

		if (allWeight() < weightLimit) {
			door.close();
		}
	}

	// Doboztól való elbúcsúzás
	@Override
	public void leave(Box b) {

		elements.remove(b);

		if (allWeight() < weightLimit) {
			door.close();
		}
	}
}