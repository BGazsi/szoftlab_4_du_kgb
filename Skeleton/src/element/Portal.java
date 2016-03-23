package element;

import element.movable.Colonel;
import field.Field;

public class Portal extends Element {

	private Field position;
	private Field outputField;

	public Portal(Field position, Field outputField) {

		this.position = position;
		this.outputField = outputField;
	}

	@Override
	public void meet(Colonel c) {
		// TODO Auto-generated method stub
		super.meet(c);
	}

	public Field getPosition() {
		return position;
	}

	public Field getOutputField() {
		return outputField;
	}
}
