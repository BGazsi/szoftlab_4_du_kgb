package view;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import controller.Game;
import model.enums.Direction;
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

				g2.drawRect(x * blockSize, y * blockSize, blockSize, blockSize);
				drawCenteredString(g2, actualField.getStatusString(),
						new Rectangle(x * blockSize, y * blockSize, blockSize, blockSize));

				x++;
			}

			y++;
		}
	}
}
