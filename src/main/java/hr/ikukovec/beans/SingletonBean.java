package hr.ikukovec.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author Ida
 * @version 1.0
 * @since 26.07.2013
 * 
 * this is a SingletonBean instantiated at startup
 * bean holds values of configuration properties 
 *
 */
@Startup
@Singleton
public class SingletonBean {
	/**
	 * ResourceBundle for config.properties file
	 */
	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	Properties prop = null;
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		System.out.println("SingletonBean Ready");
	}
	
	/**
	 * @return
	 */
	public List<String> getCities() {
		return readMultipleFromConfigFile("city");
	}
	
	/**
	 * @return
	 */
	public String getNumberOfDays() {
		return readFromConfigFile("number_of_days");
	}
	
	/**
	 * @return
	 */
	public String getURL() {
		return readFromConfigFile("url");
	}
	
	/**
	 * @return
	 */
	public String getCharset() {
		return readFromConfigFile("charset");
	}
	
	/**
	 * @return
	 */
	public String getFormatValue() {
		return readFromConfigFile("formatValue");
	}
	
	/**
	 * @return
	 */
	public String getKeyValue() {
		return readFromConfigFile("keyValue");
	}
	
	
	
	/**
	 * @param property
	 * @return
	 * gets value of a configuration parameter
	 */
	public String readFromConfigFile(String property ) {
		String result = "";
		try {
			//get the property value
			result = rb.getString(property);            
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param property
	 * @return
	 * gets values of a configuration parameter
	 */
	public List<String> readMultipleFromConfigFile(String property ) {
		List<String> resultList = new ArrayList<String>();
		try {
			//get the property values 
			String[] resultArray = rb.getString(property).split(",");
			resultList = Arrays.asList(resultArray);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultList;
	}
}
