package controller;

import java.util.Random;

import model.element.movable.Movable;
import model.enums.Direction;

public class AutoStepController extends Thread {

	private Movable movable;
	private boolean randomDirection;

	public AutoStepController(Movable movable, boolean randomDirection) {

		this.movable = movable;
		this.randomDirection = randomDirection;
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

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
