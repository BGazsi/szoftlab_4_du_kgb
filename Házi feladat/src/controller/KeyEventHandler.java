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
		case ',':
			Game.player1PutDownBox();
			break;

		case 'w':
			Game.player2Moved(Direction.NORTH);
			break;
		case 's':
			Game.player2Moved(Direction.SOUTH);
			break;
		case 'd':
			Game.player2Moved(Direction.EAST);
			break;
		case 'a':
			Game.player2Moved(Direction.WEST);
			break;
		case 'q':
			Game.player2Shoot(PortalColour.GREEN);
			break;
		case 'e':
			Game.player2Shoot(PortalColour.RED);
			break;
		case 'c':
			Game.player2PutDownBox();
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
