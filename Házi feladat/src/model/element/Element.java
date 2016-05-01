package model.element;

import model.element.movable.Bullet;
import model.element.movable.Replicator;
import model.element.movable.player.Player;

// Játékban előforduló objektumok ősosztálya
public abstract class Element {

	// Egy elemmel való utközés
	public void collide(Element e) {

		// NOTHING TO DO
	}

	// Playerrel való egy mezőre kerülés
	public void meet(Player p) {

		// NOTHING TO DO
	}

	// Replikátorral való egy mezőre kerülés
	public void meet(Replicator r) {

		// NOTHING TO DO
	}

	// Lövedékkel való egy mezőre kerülés
	public void meet(Bullet b) {

		// NOTHING TO DO
	}

	// Dobozzal való egy mezőre kerülés
	public void meet(Box b) {

		// NOTHING TO DO
	}

	// Egy elemmel való elválás
	public void sunder(Element e) {

		// NOTHING TO DO
	}

	// Playerrel való "búcsúzás"
	public void leave(Player p) {

		// NOTHING TO DO
	}

	// Doboztól való "búcsúzás"
	public void leave(Box b) {

		// NOTHING TO DO
	}

	public int getWeight() {
		return 0;
	}
}
