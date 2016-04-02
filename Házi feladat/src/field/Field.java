package field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import element.Box;
import element.Element;
import element.movable.Movable;
import enums.Direction;
import game.Game;

// Mezőt reprezentáló osztály
public class Field {

	// mező szomszédai
	private Map<Direction, Field> neighbours;
	// mezőn lévő elementek
	private Set<Element> elements;
	// mezőn lévő boxok
	private Stack<Box> boxes;

	public Field() {

		this.neighbours = new HashMap<Direction, Field>();
		this.elements = new HashSet<Element>();

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// Mezőre történő lépés
	public void enter(Movable m) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		for (Element element : elements) {

			m.collide(element);
		}

		elements.add(m);
	}

	// Mező elhagyása
	public void exit(Movable m) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		for (Element element : elements) {

			m.sunder(element);
		}

		elements.remove(m);
	}

	public void pushBox(Box b) {

		boxes.push(b);
		for (Element e : elements) {
			e.meet(b);
		}
	}

	public Box popBox() {

		Box b = boxes.pop();
		for (Element e : elements) {
			e.leave(b);
		}
		return b;
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
}
