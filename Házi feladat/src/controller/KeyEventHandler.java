package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.enums.Direction;
import model.enums.PortalColour;

public class KeyEventHandler implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {

		switch (e.getKeyChar()) {
		case 'p':
			Game.player1Moved(Direction.NORTH);
			break;
		case 'é':
			Game.player1Moved(Direction.SOUTH);
			break;
		case 'á':
			Game.player1Moved(Direction.EAST);
			break;
		case 'l':
			Game.player1Moved(Direction.WEST);
			break;
		case 'o':
			Game.player1Shoot(PortalColour.BLUE);
			break;
		case 'ő':
			Game.player1Shoot(PortalColour.YELLOW);
			break;

		// TODO finish
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
