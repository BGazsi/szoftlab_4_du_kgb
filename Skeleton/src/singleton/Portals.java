package singleton;

import java.util.HashMap;
import java.util.Map;

import element.Portal;
import enums.PortalColour;
import field.Field;

public class Portals {

	private static Map<PortalColour, Portal> portals = new HashMap<PortalColour, Portal>();

	public static void createPortal(PortalColour colour, Field position, Field outputField) {

		Portal portal = new Portal(position, outputField);
		Portal previousPortal = portals.put(colour, portal);

		if (previousPortal != null)
			previousPortal.getPosition().removeElement(previousPortal);

		position.addElement(portal);

	}
}
