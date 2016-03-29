package element;

import java.util.ArrayList;
import java.util.Arrays;

import element.movable.Bullet;
import element.movable.Colonel;
import game.Game;

// Játékban előforduló objektumok ősosztálya
public abstract class Element {

	// Egy elemmel való utközés
	public void collide(Element e) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

		// NOTHING TO DO
	}

	// Ezredessel való egy mezőre kerülés
	public void meet(Colonel c) {

		// TODO CallTree
		Game.callTree.addChildCalls(
				new ArrayList<StackTraceElement>(Arrays.asList(Thread.currentThread().getStackTrace())), null, 1);

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

	// Ezredestől való "búcsúzás"
	public void leave(Colonel c) {

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
}
