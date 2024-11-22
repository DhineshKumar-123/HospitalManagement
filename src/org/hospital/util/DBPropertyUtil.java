package org.hospital.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil 
{
	public static String createstring(String filename)
	{
		String url=null;
		try {
			FileInputStream fis =  new FileInputStream(filename);
			Properties prop = new Properties();
			prop.load(fis);
			url = prop.getProperty("url")+"/"+prop.getProperty("database")+"?"+"user="+prop.getProperty("user")+"&password="+prop.getProperty("password");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return url;
	}

}
