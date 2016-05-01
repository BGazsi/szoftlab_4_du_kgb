package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.enums.Direction;

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
