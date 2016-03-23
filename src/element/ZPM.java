package element;

public class ZPM extends Element {
	
	public void meet(element.movable.Colonel c) {
		c.pickUp(this);
	};

}
