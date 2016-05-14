package model.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum FieldStatus {

	UNKNOWN(0), COLONEL(9), JAFFA(9), REPLICATOR(9), BULLET(8), ZPM(7), PORTAL(6), WALL(5), SCALE(4), GAP(3), DOOR(
			2), BOX(1);

	private int priority;
	private Map<String, Object> data;

	private FieldStatus(int priority) {
		this.priority = priority;
		this.data = new HashMap<String, Object>();
	}

	public int getPriority() {
		return priority;
	}

	public Object get(String key) {
		return data.get(key);
	}

	public Object put(String key, Object value) {
		return data.put(key, value);
	}

	public Set<String> keySet() {
		return data.keySet();
	}

	public void clear() {
		data.clear();
	}

}
