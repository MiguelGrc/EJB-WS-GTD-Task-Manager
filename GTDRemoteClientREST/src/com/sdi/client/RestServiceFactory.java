package com.sdi.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class RestServiceFactory {
	
	public static GTDRestService client = 
			new ResteasyClientBuilder()
					.build()
					.register(new Authenticator(ClientInfo.name, ClientInfo.password))
					.target("http://localhost:8280/GTDTaskManagerWeb/rest/")
					.proxy(GTDRestService.class);
	
	public static GTDRestService getClient(){
		return client;
	}

}
