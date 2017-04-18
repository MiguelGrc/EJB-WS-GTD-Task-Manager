package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class JndiData {
	private static JndiData instance = new JndiData();
	private Properties prop = new Properties();
	
	private JndiData(){
		try {
			prop.load(new FileReader("jndiKey.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JndiData getInstance(){
		return instance;
	}
	
	public String getJndiKey(){
		return prop.getProperty("USER_SERVICE_JNDI_KEY");
		
	}
}
