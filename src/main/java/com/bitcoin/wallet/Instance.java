package com.bitcoin.wallet;

import java.util.Objects;

public class Instance {

	private final String ip;
	private final InstanceId instanceId;
	private final int port;

	private Instance(InstanceId instanceId, String ip, int port) {
		this.instanceId = Objects.requireNonNull(instanceId);
		this.ip = Objects.requireNonNull(ip);
		this.port = port;
	}

	public static Instance create(InstanceId instanceId, String ip, int port) {
		return new Instance(instanceId, ip, port);
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public InstanceId getInstanceId() {
		return instanceId;
	}

	@Override
	public String toString() {
		return "Instance [" +
				"ip=" + ip +
				", instanceId=" + instanceId +
				", port=" + port +
				']';
	}
}
