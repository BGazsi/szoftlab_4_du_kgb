package portal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import element.Wall;
import element.movable.player.Player;
import enums.PortalColour;
import field.Field;
import game.Game;

// Portálokat szimbolizáló osztály
public class Portals {

	// az épp aktív portálok
	private static Map<PortalColour, Portal> portals = new HashMap<PortalColour, Portal>();

	private static class Portal {

		private Wall position;
		private Field outputField;

		public Portal(Wall position, Field outputField) {

			this.position = position;
			this.outputField = outputField;

			// TODO CallTree
			Game.callTree.addChildCalls(
					new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);
		}
	}

	// Új portál létrehozása
	public static void createPortal(PortalColour colour, Wall position, Field outputField) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		Portal portal = new Portal(position, outputField);

		portals.put(colour, portal);
	}

	// Egy adott falra vonatkozó ellenőrzés, hogy van-e rajta portál
	public static boolean isPortal(Wall position, Field outputField) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		for (Portal p : portals.values()) {

			if (p.position == position && p.outputField == outputField)
				return true;
		}

		return false;
	}

	// Az Playert továbbító függvény
	public static void send(Player c) {

		// TODO send
		c.stay();
	}
}
