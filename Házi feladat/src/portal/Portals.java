package portal;

import java.util.HashMap;
import java.util.Map;

import element.Wall;
import element.movable.player.Player;
import enums.PortalColour;
import field.Field;

// Portálokat szimbolizáló osztály
public class Portals {

	// az épp aktív portálok
	private static Map<PortalColour, Portal> portals = new HashMap<PortalColour, Portal>();

	private static class Portal {

		private Wall wall;
		private Field outputField;

		public Portal(Wall wall, Field outputField) {

			this.wall = wall;
			this.outputField = outputField;

		}
	}

	// Új portál létrehozása
	public static void createPortal(PortalColour colour, Wall wall, Field outputField) {

		Portal portal = new Portal(wall, outputField);

		portals.put(colour, portal);
	}

	// Egy adott falra vonatkozó ellenőrzés, hogy van-e rajta portál
	public static PortalColour findPortal(Wall wall, Field outputField) {

		for (PortalColour pc : portals.keySet()) {

			Portal p = portals.get(pc);
			if (p.wall == wall && (outputField == null || p.outputField == outputField))
				return pc;
		}

		return null;
	}

	// Az Playert továbbító függvény
	public static void send(Player player, Wall wall) {

		player.stay();
		Field oldField = player.getPosition();
		Field newField = portals.get(findPortal(wall, oldField).getPair()).outputField;

		oldField.exit(player);
		player.setPosition(newField);
		newField.enter(player);
	}
}
