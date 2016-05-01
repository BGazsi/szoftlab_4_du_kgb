package model.element;

import controller.Game;
import model.element.movable.player.Player;

// A dobozt szimbolizáló függvény
public class Box extends Element {

	// a doboz súlya
	protected int weight;

	public Box(int weight) {

		this.weight = weight;
	}

	// Egy elementtel való ütközéskor hívódó függvény
	@Override
	public void collide(Element e) {

		e.meet(this);
	}

	@Override
	public void sunder(Element e) {

		e.leave(this);
	}

	@Override
	public void meet(Player p) {

		p.pickUpBox(this);
	}

	// Doboz megsemmisülése
	public void destroy() {

		Game.removeElement(this);
	}

	@Override
	public int getWeight() {

		return weight;
	}
}
