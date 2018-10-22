package com.bitcoin.wallet.config;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.bitcoin.wallet.Connection;
import com.bitcoin.wallet.ConnectionId;
import com.bitcoin.wallet.Instance;
import com.bitcoin.wallet.InstanceId;
import com.bitcoin.wallet.RegistryConnectionFacade;
import com.bitcoin.wallet.RegistryConnectionFacadeImpl;

import okhttp3.OkHttpClient;

@Configuration
public class Config {

	private static final Logger logger = LoggerFactory.getLogger(Config.class);

	@Bean
	public OkHttpClient okHttpClient () {
		return new OkHttpClient();
	}

	@Bean
	public InstanceId instanceId () {
		return InstanceId.create(UUID.randomUUID().toString());
	}

	@Bean
	public Instance instance (InstanceId instanceId, Environment environment) {
		String property = Optional.ofNullable(environment.getProperty("server.port")).orElseThrow(() -> new RuntimeException("Could not find server.port"));
		return Instance.create(instanceId,"http://localhost", Integer.parseInt(property));
	}

	@Bean
	public ConnectionId connectionId () {
		return ConnectionId.create("transaction-service");
	}

	@Bean
	public Connection connection (ConnectionId connectionId, Instance instance) {
		Connection connection = Connection.create(connectionId, instance);
		logger.info("Running with current connection={}", connection);
		return connection;
	}

	@Bean
	public RegistryConnectionFacade registryConnectionFacade (OkHttpClient okHttpClient, Connection connection) {
		return new RegistryConnectionFacadeImpl(okHttpClient, connection);
	}

}