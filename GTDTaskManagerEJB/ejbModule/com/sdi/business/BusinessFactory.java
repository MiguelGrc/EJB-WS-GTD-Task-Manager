package com.sdi.business;

import com.sdi.business.services.Services;

public class BusinessFactory {
	
	public static Services businessService = new LocalEjbServicesLocator();

}
