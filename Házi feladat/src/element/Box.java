package element;

import element.movable.player.Player;
import game.Game;

// A dobozt szimbolizáló függvény
public class Box extends Element {

	// a doboz súlya
	protected int weight;

	public Box(String name, int weight) {

		super(name);

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
