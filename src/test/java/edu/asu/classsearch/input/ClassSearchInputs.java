package edu.asu.classsearch.input;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassSearchInputs {

	private static Properties PROPERTIES;
	private static String PROP_LOCATION = "src/test/resources/inputdata/input.properties";

	public static String inputload(String key) {
		String value = ",";

		if (PROPERTIES == null) {
			PROPERTIES = new Properties();
			try {
				PROPERTIES.load(new FileInputStream(PROP_LOCATION));
				value = PROPERTIES.getProperty(key);
				value.trim();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			value = PROPERTIES.getProperty(key);
			value.trim();
		}

		return value;
	}

	public static String login() {
		Properties prop = new Properties();
		InputStream input = null;
		String value = null, value1 = null;
		try {

			input = new FileInputStream("resources/inputdata/login.properties");

			// load a properties file
			prop.load(input);
			value = prop.getProperty("username");
			value.trim();
			value1 = prop.getProperty("password");
			value1.trim();

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value + "," + value1;
	}

}
