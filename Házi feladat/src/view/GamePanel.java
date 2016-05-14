package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Set;

import javax.swing.JPanel;

import controller.Game;
import model.enums.Direction;
import model.enums.FieldStatus;
import model.enums.PortalColour;
import model.field.Field;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int blockSize = 50;

	public GamePanel() {

		Dimension fieldSize = Game.getFieldSize();
		Dimension preferredSize = new Dimension((int) fieldSize.getWidth() * blockSize + 1,
				(int) fieldSize.getHeight() * blockSize + 1);

		setPreferredSize(preferredSize);
	}

	private void drawCenteredString(Graphics2D g2, String text, Rectangle rect) {

		FontMetrics metrics = g2.getFontMetrics(g2.getFont());
		int x = (rect.width - metrics.stringWidth(text)) / 2 + (int) rect.getX();
		int y = ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent() + (int) rect.getY();
		g2.drawString(text, x, y);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Field fieldReference = Game.getFieldReference();
		int x = 0;
		int y = 0;
		for (Field rowStart = fieldReference; rowStart != null; rowStart = rowStart.getNeighbour(Direction.SOUTH)) {

			x = 0;

			for (Field actualField = rowStart; actualField != null; actualField = actualField
					.getNeighbour(Direction.EAST)) {

				Rectangle drawArea = new Rectangle(x * blockSize, y * blockSize, blockSize, blockSize);
				FieldStatus fieldStatus = actualField.getStatus();

				switch (actualField.getStatus()) {
				case COLONEL:
				case JAFFA:
				case REPLICATOR:
					Direction direction = (Direction) fieldStatus.get("direction");

					drawCenteredString(g2, fieldStatus.toString().substring(0, 1), drawArea);

					switch (direction) {
					case EAST:
						g2.fillRect((int) (drawArea.x + drawArea.width * 0.8),
								(int) (drawArea.y + drawArea.height * 0.2), (int) (drawArea.width * 0.1),
								(int) (drawArea.height * 0.6));
						break;
					case NORTH:
						g2.fillRect((int) (drawArea.x + drawArea.width * 0.2),
								(int) (drawArea.y + drawArea.height * 0.1), (int) (drawArea.width * 0.6),
								(int) (drawArea.height * 0.1));
						break;
					case SOUTH:
						g2.fillRect((int) (drawArea.x + drawArea.width * 0.2),
								(int) (drawArea.y + drawArea.height * 0.8), (int) (drawArea.width * 0.6),
								(int) (drawArea.height * 0.1));
						break;
					case WEST:
						g2.fillRect((int) (drawArea.x + drawArea.width * 0.1),
								(int) (drawArea.y + drawArea.height * 0.2), (int) (drawArea.width * 0.1),
								(int) (drawArea.height * 0.6));
						break;
					}
					break;
				case BULLET:
					PortalColour portalColour = (PortalColour) fieldStatus.get("colour");
					direction = (Direction) fieldStatus.get("direction");

					switch (portalColour) {
					case BLUE:
						g2.setColor(Color.BLUE);
						break;
					case GREEN:
						g2.setColor(Color.GREEN);
						break;
					case RED:
						g2.setColor(Color.RED);
						break;
					case YELLOW:
						g2.setColor(Color.YELLOW);
						break;
					}

					switch (direction) {
					case EAST:
						g2.fillOval(drawArea.x + drawArea.width / 2, drawArea.y + drawArea.height / 4,
								drawArea.width / 2, drawArea.height / 2);
						break;
					case NORTH:
						g2.fillOval(drawArea.x + drawArea.width / 4, drawArea.y, drawArea.width / 2,
								drawArea.height / 2);
						break;
					case SOUTH:
						g2.fillOval(drawArea.x + drawArea.width / 4, drawArea.y + drawArea.height / 2,
								drawArea.width / 2, drawArea.height / 2);
						break;
					case WEST:
						g2.fillOval(drawArea.x, drawArea.y + drawArea.height / 4, drawArea.width / 2,
								drawArea.height / 2);
						break;
					}

					break;
				case WALL:
					Boolean shootable = (Boolean) fieldStatus.get("shootable");

					if (shootable) {
						g2.setColor(Color.GRAY);
					} else {
						g2.setColor(Color.DARK_GRAY);
					}

					g2.fillRect(drawArea.x, drawArea.y, drawArea.width, drawArea.height);
					g2.setColor(Color.BLACK);
					g2.drawLine(drawArea.x, drawArea.y, drawArea.x + drawArea.width, drawArea.y + drawArea.height);
					g2.drawLine(drawArea.x + drawArea.width, drawArea.y, drawArea.x, drawArea.y + drawArea.height);
					break;
				case BOX:
					Integer weight = (Integer) fieldStatus.get("weight");

					g2.setColor(new Color(139, 69, 19));
					g2.fillRect(drawArea.x, drawArea.y, drawArea.width, drawArea.height);
					g2.setColor(Color.BLACK);
					drawCenteredString(g2, "(" + weight + ")", drawArea);

					break;
				case DOOR:
					Boolean opened = (Boolean) fieldStatus.get("opened");

					if (!opened) {

						g2.setColor(new Color(205, 133, 63));
						g2.fillRect(drawArea.x, drawArea.y, drawArea.width, drawArea.height);
					}
					break;
				case SCALE:
					Integer allWeight = (Integer) fieldStatus.get("allweight");
					Integer weightLimit = (Integer) fieldStatus.get("weightlimit");

					g2.setColor(new Color(160, 82, 45));
					g2.fillRect(drawArea.x, drawArea.y, drawArea.width, drawArea.height);
					g2.setColor(Color.BLACK);
					drawCenteredString(g2, "(" + allWeight + "/" + weightLimit + ")", drawArea);

					break;
				case GAP:
					g2.setColor(Color.BLACK);
					g2.fillRect(drawArea.x, drawArea.y, drawArea.width, drawArea.height);
					break;
				case PORTAL:
					Set<String> keySet = fieldStatus.keySet();

					g2.setColor(Color.GRAY);
					g2.fillRect(drawArea.x, drawArea.y, drawArea.width, drawArea.height);

					for (String s : keySet) {
						direction = Direction.valueOf(s);
						portalColour = (PortalColour) fieldStatus.get(s);

						switch (portalColour) {
						case BLUE:
							g2.setColor(Color.BLUE);
							break;
						case GREEN:
							g2.setColor(Color.GREEN);
							break;
						case RED:
							g2.setColor(Color.RED);
							break;
						case YELLOW:
							g2.setColor(Color.YELLOW);
							break;
						}

						switch (direction) {
						case EAST:
							g2.fillOval(drawArea.x + drawArea.width / 2, drawArea.y + drawArea.height / 4,
									drawArea.width / 2, drawArea.height / 2);
							break;
						case NORTH:
							g2.fillOval(drawArea.x + drawArea.width / 4, drawArea.y, drawArea.width / 2,
									drawArea.height / 2);
							break;
						case SOUTH:
							g2.fillOval(drawArea.x + drawArea.width / 4, drawArea.y + drawArea.height / 2,
									drawArea.width / 2, drawArea.height / 2);
							break;
						case WEST:
							g2.fillOval(drawArea.x, drawArea.y + drawArea.height / 4, drawArea.width / 2,
									drawArea.height / 2);
							break;
						}
					}
					break;
				case UNKNOWN:
					break;
				default:
					drawCenteredString(g2, fieldStatus.toString().substring(0, 3), drawArea);
					break;
				}

				g2.setColor(Color.BLACK);
				g2.drawRect(x * blockSize, y * blockSize, blockSize, blockSize);

				x++;
			}

			y++;
		}
	}
}
