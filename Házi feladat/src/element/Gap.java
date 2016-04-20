package element;

import element.movable.Replicator;
import element.movable.player.Player;
import game.Game;

// Szakadékot szombolizáló osztály
public class Gap extends Element {

	public Gap(String name) {

		super(name);
	}

	// Playerrel való egy mezőre kerülés
	@Override
	public void meet(Player p) {

		p.die();
	}

	// Replikátorral való egy mezőre kerülés
	@Override
	public void meet(Replicator r) {

		r.die();
		Game.removeElement(this);
	}

	// Dobozzal való egy mezőre kerülés
	@Override
	public void meet(Box b) {

		b.destroy();
	}
}
