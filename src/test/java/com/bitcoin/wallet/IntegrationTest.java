package com.bitcoin.wallet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.JsonParser;

@RunWith(SpringRunner.class)
public class IntegrationTest {

	@LocalServerPort
	int port;

	@Autowired
	TestRestTemplate testRestTemplate;

	JsonParser jsonParser = new JsonParser();

	@Test
	public void addsConnection_andGetConnection_shouldReturn_instance() {

	}
}
