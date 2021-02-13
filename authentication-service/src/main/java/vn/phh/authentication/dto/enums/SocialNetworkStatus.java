package vn.phh.authentication.dto.enums;

import java.util.HashMap;
import java.util.Map;

public enum SocialNetworkStatus {

	FACEBOOK(1,  "Login by facebook"),

	GOOGLE(2, "Login by facebook");

	private final int key;
	private final String value;

	private static Map<Integer, SocialNetworkStatus> tripMapping;

	private SocialNetworkStatus(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public static SocialNetworkStatus getByKey(Integer key) {
		if (tripMapping == null) {
			initMapping();
		}
		return tripMapping.get(key);
	}

	private static void initMapping() {
		tripMapping = new HashMap<>();
		for (SocialNetworkStatus tripStatus : values()) {
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
