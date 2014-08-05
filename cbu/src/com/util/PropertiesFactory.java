package com.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFactory {

	private Properties properties;

	public PropertiesFactory(File file) {
		this.properties = new Properties();
		try {
			InputStream in = new FileInputStream(file);
			this.properties.load(in);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PropertiesFactory(InputStream input) {
		this.properties = new Properties();
		try {
			this.properties.load(input);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getParam(String name) {
		if(this.properties.getProperty(name) == null){
			return null;
		}
		return this.properties.getProperty(name).trim();
	}

}
