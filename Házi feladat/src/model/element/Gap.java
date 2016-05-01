package model.element;

import controller.Game;
import model.element.movable.Replicator;
import model.element.movable.player.Player;

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
		Game.removeElement(this);
	}

	// Dobozzal való egy mezőre kerülés
	@Override
	public void meet(Box b) {

		b.destroy();
	}
}
