package isu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil { 


	public InputStream getPropertyFile(String fileName){

		InputStream input = null;
		
		try {

			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
			if(input==null){
				System.out.println("Sorry, unable to find " + fileName);
			}
		} finally{
			
		}
		return input;

	}

	public String getPropertyValue(String propertyName,InputStream input) throws IOException {
		
		Properties prop = new Properties();
		
		prop.load(input);

		return prop.getProperty(propertyName);
		
	}
	
}
