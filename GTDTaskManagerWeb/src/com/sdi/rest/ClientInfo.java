package com.sdi.rest;

import com.sdi.dto.User;


public class ClientInfo {
	
	public static Long id;
	public static String name;
	public static String password;
	
	public static void setInformation(User u){
		id = u.getId();
		name = u.getLogin();
		password = u.getPassword();
		
	}
}
