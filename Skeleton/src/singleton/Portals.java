package singleton;

import java.util.HashMap;
import java.util.Map;

import element.Wall;
import element.movable.Colonel;
import enums.PortalColour;
import field.Field;

public class Portals {

	private static Map<PortalColour, Portal> portals = new HashMap<PortalColour, Portal>();

	private static class Portal {

		private Wall position;
		private Field outputField;

		public Portal(Wall position, Field outputField) {

			this.position = position;
			this.outputField = outputField;
		}
	}

	public static void createPortal(PortalColour colour, Wall position, Field outputField) {

		Portal portal = new Portal(position, outputField);

		portals.put(colour, portal);
	}

	public static boolean isPortal(Wall position, Field outputField) {

		for (Portal p : portals.values()) {

			if (p.position == position && p.outputField == outputField)
				return true;
		}

		return false;
	}

	public static void send(Colonel c) {

		// TODO
	}
}
