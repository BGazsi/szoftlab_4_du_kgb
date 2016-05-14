package controller;

import java.util.Random;

import model.element.movable.Movable;
import model.enums.Direction;

public class AutoStepController extends Thread {

	private Movable movable;
	private boolean randomDirection;
	private long delay;

	public AutoStepController(Movable movable, boolean randomDirection, long delay) {

		this.movable = movable;
		this.randomDirection = randomDirection;
		this.delay = delay;
	}

	@Override
	public void run() {
		while (!movable.isKilled()) {

			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (!movable.isKilled()) {

				if (randomDirection) {
					Random rnd = new Random();
					Direction newDirection = Direction.values()[rnd.nextInt(Direction.values().length)];
					movable.setDirection(newDirection);
				}

				movable.step();
				Game.panelRepaint();
			}
		}
	}
}
