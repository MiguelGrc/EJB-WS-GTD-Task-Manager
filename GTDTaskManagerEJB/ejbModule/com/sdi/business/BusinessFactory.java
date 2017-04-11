package com.sdi.business;

import com.sdi.business.services.Services;

public class BusinessFactory {
	private static String CONFIG_FILE = "/factories.properties";
	
	public static Services businessService = (Services) FactoriesHelper.createFactory(CONFIG_FILE, "SERVICES_FACTORY");
	
	

}
