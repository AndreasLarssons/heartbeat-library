package com.bitcoin.wallet;

import java.util.Objects;

public class ConnectionId {

	private final String id;

	private ConnectionId(String id) {
		this.id = Objects.requireNonNull(id);
	}

	public static ConnectionId create(String id) {
		return new ConnectionId(id);
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ConnectionId [" +
				"id=" + id +
				']';
	}
}
