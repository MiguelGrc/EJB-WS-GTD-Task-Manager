package com.sdi.client;

import alb.util.console.Console;

public class ClientInfo {
	public static String name;
	public static String password;
	
	public static void saveClientInformation(){
		while(name == null || name.isEmpty())
			name = Console.readString("User");
		while(password == null || password.isEmpty())
			password = Console.readString("Password");
	}
	
}
