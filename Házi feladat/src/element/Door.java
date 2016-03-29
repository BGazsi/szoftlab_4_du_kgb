package element;

import java.util.ArrayList;
import java.util.Arrays;

import element.movable.Bullet;
import element.movable.Colonel;
import game.Game;

// Ajtót reprezentáló függvény
public class Door extends Element {

	// ajtó álapotát tároló változó
	private boolean isOpened;

	public Door() {

		this.isOpened = false;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	public Door(boolean isOpened) {

		this.isOpened = isOpened;

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 3);
	}

	// Ezredessel való egy mezőre kerülés
	@Override
	public void meet(Colonel c) {

		if (!isOpened)
			c.stay();
	}

	// Lövedékkel való egy mezőre kerülés
	@Override
	public void meet(Bullet b) {

		if (!isOpened)
			b.destroy();
	}

	// Dobozzal való egy mezőre kerülés
	@Override
	public void meet(Box b) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// TODO
	}

	// Ajtó kinyitása
	public void open() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		isOpened = true;
	}

	// Ajtó becsukása
	public void close() {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		isOpened = false;
	}
}
