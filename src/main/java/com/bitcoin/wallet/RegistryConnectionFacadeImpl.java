package com.bitcoin.wallet;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistryConnectionFacadeImpl implements RegistryConnectionFacade {

	private final OkHttpClient okHttpClient;
	private static final Logger logger = LoggerFactory.getLogger(RegistryConnectionFacadeImpl.class);
	private final String BASE_URL = "http://localhost:1337/";

	public RegistryConnectionFacadeImpl(OkHttpClient okHttpClient, Connection connection) {
		this.okHttpClient = okHttpClient;
		this.connect(connection);
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
			this.heartbeat(connection);
		}, 200, 500, TimeUnit.MILLISECONDS);
	}

	@Override
	public void connect(Connection connection) {
		JsonObject content = convertToJson(connection);
		Request request = createRequest("add", content);
		try {
			Response response = executeCall(request);
			System.out.println(response.body());
		} catch (RuntimeException e) {
			logger.error("Could not establish connection to registry will exit application now");
			exit();
		}
	}

	private Request createRequest(String url, JsonObject content) {
		return new Builder()
				.url(BASE_URL + (url))
				.post(RequestBody.create(MediaType.parse("application/json"), content.toString())).build();
	}

	private JsonObject convertToJson(Connection connection) {
		JsonObject content = new JsonObject();
		content.addProperty("id", connection.getConnectionId().getId());
		JsonObject instance = new JsonObject();
		instance.addProperty("port", connection.getInstance().getPort());
		instance.addProperty("ip", connection.getInstance().getIp());
		instance.addProperty("instanceId", connection.getInstance().getInstanceId().getId());
		content.add("instanceRequest", instance);
		return content;
	}

	@Override
	public void heartbeat(Connection connection) {
		executeCall(createRequest("heartbeat", convertToJson(connection)));
	}

	private Response executeCall(Request request) {
		try {
			return okHttpClient.newCall(request).execute();
		} catch (Exception e) {
			throw new RuntimeException("Could not establish connection");
		}
	}

	private void exit() {
		System.exit(-1);
	}
}
