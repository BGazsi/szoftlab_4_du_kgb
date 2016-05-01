package model.element;

import model.element.movable.Bullet;
import model.element.movable.player.Player;

// Ajtót reprezentáló függvény
public class Door extends Element {

	// ajtó álapotát tároló változó
	private boolean isOpened;

	public Door() {

		this.isOpened = false;
	}

	public Door(boolean isOpened) {

		this.isOpened = isOpened;
	}

	// Playerrel való egy mezőre kerülés
	@Override
	public void meet(Player p) {

		if (!isOpened)
			p.stay();
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

		// TODO meet
	}

	// Allapotának lekérése
	public boolean isOpened() {
		return isOpened;
	}

	// Ajtó kinyitása
	public void open() {

		isOpened = true;
	}

	// Ajtó becsukása
	public void close() {

		isOpened = false;
	}
}
