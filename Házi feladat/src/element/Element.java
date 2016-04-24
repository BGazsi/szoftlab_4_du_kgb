package element;

import element.movable.Bullet;
import element.movable.Replicator;
import element.movable.player.Player;

// Játékban előforduló objektumok ősosztálya
public abstract class Element {

	// következő generálandó ID
	private static int nextID = 0;
	// elementet azonosító név
	protected String name;

	public Element(String name) {

		if (name == null) {
			name = getNewName();
		}

		this.name = name;
	}

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

	public static String getNewName() {
		return "Element#" + Integer.toString(nextID++);
	}

	public int getWeight() {
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
