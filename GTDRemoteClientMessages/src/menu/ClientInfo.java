package menu;

import alb.util.console.Console;

public class ClientInfo {
	public static String name;
	public static String password;
	
	public static void saveClientInformation(){
		name = Console.readString("User");
		password = Console.readString("password");
		
	}
}
