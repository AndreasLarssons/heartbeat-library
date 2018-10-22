package com.bitcoin.wallet;

public interface RegistryConnectionFacade {
	void connect(Connection connection);
	void heartbeat(Connection connection);
}
