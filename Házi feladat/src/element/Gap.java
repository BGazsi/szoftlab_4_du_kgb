package element;

import element.movable.Replicator;
import element.movable.player.Player;

// Szakadékot szombolizáló osztály
public class Gap extends Element {

	// Playerrel való egy mezőre kerülés
	@Override
	public void meet(Player p) {

		p.die();
	}

	// Replikátorral való egy mezőre kerülés
	@Override
	public void meet(Replicator r) {

		r.die();
		game.removeElement(this);
	}

	// Dobozzal való egy mezőre kerülés
	@Override
	public void meet(Box b) {

		b.destroy();
	}
}
