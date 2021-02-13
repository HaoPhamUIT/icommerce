package vn.phh.order.dto.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {

	ORDERED(0,  "Got Ordered"),

	DELIVERED(1, "Got delivered");

	private final int key;
	private final String value;

	private static Map<Integer, OrderStatus> tripMapping;

	private OrderStatus(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public static OrderStatus getByKey(Integer key) {
		if (tripMapping == null) {
			initMapping();
		}
		return tripMapping.get(key);
	}

	private static void initMapping() {
		tripMapping = new HashMap<>();
		for (OrderStatus tripStatus : values()) {
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
