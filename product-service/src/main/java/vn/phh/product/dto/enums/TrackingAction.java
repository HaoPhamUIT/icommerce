package vn.phh.product.dto.enums;

import java.util.HashMap;
import java.util.Map;

public enum TrackingAction {

	SEARCH(0,  "Search product"),

	FILTER(1, "Filter product"),

	VIEWER(2, "Viewer product");

	private final int key;
	private final String value;

	private static Map<Integer, TrackingAction> tripMapping;

	private TrackingAction(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public static TrackingAction getByKey(Integer key) {
		if (tripMapping == null) {
			initMapping();
		}
		return tripMapping.get(key);
	}

	private static void initMapping() {
		tripMapping = new HashMap<>();
		for (TrackingAction tripStatus : values()) {
			tripMapping.put(tripStatus.getKey(), tripStatus);
		}
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
