package com.bitcoin.wallet;

import java.util.Objects;

public class InstanceId {
	private final String id;

	private InstanceId(String id) {
		this.id = id;
	}

	public static InstanceId create(String id) {
		return new InstanceId(id);
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InstanceId that = (InstanceId) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "InstanceId [" +
				"id=" + id +
				']';
	}
}
