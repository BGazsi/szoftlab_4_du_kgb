package element;

import element.movable.player.Player;

// ZPMet reprezentáló osztály
public class ZPM extends Element {

	public ZPM(String name) {

		super(name);
	}

	// Playerrel való egy mezőre kerülés
	@Override
	public void meet(Player p) {

		p.pickUpZPM(this);
	}
}
