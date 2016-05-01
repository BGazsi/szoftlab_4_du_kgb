package model.element;

import model.element.movable.player.Player;

// ZPMet reprezentáló osztály
public class ZPM extends Element {

	// Playerrel való egy mezőre kerülés
	@Override
	public void meet(Player p) {

		p.pickUpZPM(this);
	}
}
