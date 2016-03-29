package field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import element.Element;
import enums.Direction;
import game.Game;

// Mezőt reprezentáló osztály
public class Field {

	// mező szomszédai
	protected Map<Direction, Field> neighbours;
	// mezőn lévő elementek
	protected Set<Element> elements;

	public Field() {

		this.neighbours = new HashMap<Direction, Field>();
		this.elements = new HashSet<Element>();

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// Mezőre történő lépés
	public void enter(Element e) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		for (Element element : elements) {

			e.collide(element);
		}

		elements.add(e);
	}

	// Mező elhagyása
	public void exit(Element e) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		for (Element element : elements) {

			e.sunder(element);
		}

		elements.remove(e);
	}

	// Adott irányban lévő szomszédok elkérése
	public Field getNeighbour(Direction d) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		return neighbours.get(d);
	}

	// Adott irányba lévő szomszédok beállítása
	public void setNeighbour(Direction d, Field f) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);

		neighbours.put(d, f);
	}

	// Egy elem mezőhöz adása (ha nem akarjuk hogy minden elemmel "találkozzon")
	public void addElement(Element e) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);

		elements.add(e);
	}
}
