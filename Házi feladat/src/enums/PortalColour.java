package enums;

// A portál színét jelölő enumeráció
public enum PortalColour {
	BLUE, YELLOW, RED, GREEN;

	public PortalColour getPair() {

		switch (this) {
		case BLUE:
			return PortalColour.YELLOW;
		case GREEN:
			return RED;
		case RED:
			return PortalColour.GREEN;
		case YELLOW:
			return BLUE;
		}

		return this;
	}
}
