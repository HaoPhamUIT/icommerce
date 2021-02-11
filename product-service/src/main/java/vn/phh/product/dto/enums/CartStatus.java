package vn.phh.product.dto.enums;

import java.util.HashMap;
import java.util.Map;

public enum CartStatus {

	IN_CART(0,  "Waiting order"),

	ORDERED(1, "Customer order");

	private final int key;
	private final String value;

	private static Map<Integer, CartStatus> tripMapping;

	private CartStatus(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public static CartStatus getByKey(Integer key) {
		if (tripMapping == null) {
			initMapping();
		}
		return tripMapping.get(key);
	}

	private static void initMapping() {
		tripMapping = new HashMap<>();
		for (CartStatus tripStatus : values()) {
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
