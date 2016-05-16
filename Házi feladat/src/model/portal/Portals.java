package model.portal;

import java.util.HashMap;
import java.util.Map;

import model.element.Wall;
import model.element.movable.player.Player;
import model.enums.FieldStatus;
import model.enums.PortalColour;
import model.field.Field;

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

		if (newField.getStatus() != FieldStatus.COLONEL && newField.getStatus() != FieldStatus.JAFFA) {

			oldField.exit(player);
			player.setPosition(newField);
			newField.enter(player);
		}
	}
}
