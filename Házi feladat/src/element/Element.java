package element;

import java.util.ArrayList;
import java.util.Arrays;

import element.movable.Bullet;
import element.movable.Replicator;
import element.movable.player.Player;
import game.Game;

// Játékban előforduló objektumok ősosztálya
public abstract class Element {

	// elementet tartalmazó Game objektum refrenciája
	protected Game game;

	public void setGame(Game game) {
		this.game = game;
	}

	// Egy elemmel való utközés
	public void collide(Element e) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// NOTHING TO DO
	}

	// Playerrel való egy mezőre kerülés
	public void meet(Player p) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// NOTHING TO DO
	}

	// Replikátorral való egy mezőre kerülés
	public void meet(Replicator r) {

		// NOTHING TO DO
	}

	// Lövedékkel való egy mezőre kerülés
	public void meet(Bullet b) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// NOTHING TO DO
	}

	// Dobozzal való egy mezőre kerülés
	public void meet(Box b) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// NOTHING TO DO
	}

	// Egy elemmel való elválás
	public void sunder(Element e) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// NOTHING TO DO
	}

	// Playerrel való "búcsúzás"
	public void leave(Player p) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// NOTHING TO DO
	}

	// Doboztól való "búcsúzás"
	public void leave(Box b) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// NOTHING TO DO
	}

	public int getWeight() {
		return 0;
	}
}
