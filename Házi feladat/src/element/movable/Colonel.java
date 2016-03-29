package element.movable;

import java.util.ArrayList;
import java.util.Arrays;

import element.Box;
import element.Element;
import element.ZPM;
import enums.Direction;
import enums.PortalColour;
import field.Field;
import game.Game;

// Az Ezredest reprezentáló osztály
public class Colonel extends Movable {

	// az Ezredesnél lévő tárgy referenciája (ha nincs nála semmi akkor null)
	private Box box;
	// Ezrdes által felvett ZPMek száma
	private int ZMPcount;
	// az Ezredes helyben maradásának szükségességét jelző flag
	private boolean needToStay;

	public Colonel(Field position, Direction direction) {

		super(position, direction);
		this.needToStay = false;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// Az Ezredes mozgását leíró függvény
	@Override
	public void step() {

		// elkéri a szomszédos mezőt
		Field nextField = position.getNeighbour(direction);

		// új mezőre rálép
		nextField.enter(this);
		// ha helyben kell maradnia (mert mondjuk fal van ott ahova lépni akart
		if (needToStay) {

			needToStay = false;
			// az új mezőről "visszalép"
			nextField.exit(this);

		} else {

			// a jelenlegi mezőről lelép
			position.exit(this);
			position = nextField;
		}
	}

	// Egy elementtel való ütközés esetén meghívódó függvény
	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	@Override
	public void sunder(Element e) {

		e.leave(this);
	}

	// Az Ezredes lövését (lövedék letrehozását) kezelő függvény
	public Bullet shoot(PortalColour color) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		return new Bullet(position, direction, color);
	}

	// A függvény ami helyben maradásra utasítja az Ezredest
	public void stay() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		needToStay = true;
	}

	// Egy doboz felvételét reprezentáló függvény
	public void pickUp(Box b) {

		// csak akkor veszünk fel új elemet ha nincs nála épp másik
		if (box == null) {

			box = b;
			position.exit(b);
		}
	}

	// Egy ZPM felvételét reprezentáló függvény
	public void pickUp(ZPM z) {

		ZMPcount++;
		position.exit(z);
	}

	// Doboz letétele
	public void putDown(Box e) {

		position.enter(e);
		box = null;
	}

	// Az Ezredes halálát szimbolizáló függvény
	public void die() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// TODO
	}

	// Visszatér az Ezredesnél lévő doboz referencájával
	public Box getElement() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		return box;
	}
}
