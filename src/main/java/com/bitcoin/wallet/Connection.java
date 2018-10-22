package com.bitcoin.wallet;

import java.util.Objects;

public class Connection {
	private final ConnectionId connectionId;
	private final Instance instance;

	private Connection(ConnectionId connectionId, Instance instance) {
		this.connectionId = connectionId;
		this.instance = Objects.requireNonNull(instance);
	}

	public static Connection create(ConnectionId connectionId, Instance instance) {
		return new Connection(connectionId, instance);
	}

	public ConnectionId getConnectionId() {
		return connectionId;
	}

	public Instance getInstance() {
		return instance;
	}

	@Override
	public String toString() {
		return "Connection [" +
				"connectionId=" + connectionId +
				", instance=" + instance +
				']';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Connection that = (Connection) o;
		return Objects.equals(connectionId, that.connectionId) &&
				Objects.equals(instance, that.instance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(connectionId, instance);
	}
}
