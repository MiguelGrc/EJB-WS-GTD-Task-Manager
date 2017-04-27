package com.sdi.client;


import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class Main {

	private GTDRestService client;

	public static void main(String[] args) {
		Main main = new Main();
	}

	public Main() {
		client = new ResteasyClientBuilder().build()
				.register(new Authenticator("sdi", "password"))
				.target("http://localhost:8280/Notaneitor_v6.0Web/rest/")
				.proxy(GTDRestService.class);
	}

}
