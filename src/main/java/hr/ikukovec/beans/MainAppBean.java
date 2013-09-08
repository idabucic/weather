package hr.ikukovec.beans;

import hr.ikukovec.beans.DisplayElement.DisplayElementType;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.xml.parsers.*;
import org.w3c.dom.*;

//http://balusc.blogspot.com/2006/09/debug-jsf-lifecycle.html
/**
 * @author Ida
 * @version 1.0
 * @since 26.07.2013
 * 
 *        this is a main bean for the application initializes during first HTTP
 *        request
 * 
 * 
 */
@ManagedBean(name = "main")
public class MainAppBean {

	@EJB
	private SingletonBean sb;

	private DisplayElement display;

	private DisplayElementFactory factory = new DisplayElementFactory();

	/**
	 * this is a default value for selected DisplayElement
	 */
	private int displayType = DisplayElementType.CURRENT_WEATHER.getType();
	private int numberOfDays = 3;

	private String city;

	/**
	 * sets default DisplayElement
	 */
	@PostConstruct
	public void init() {
		System.err.println("inišalajz!");

		DisplayElement de = factory
				.createDisplayElement(DisplayElementType.EMPTY_DISPLAY);
		setDisplay(de);
		this.display.loadData(null);
	}

	/**
	 * @param query
	 * @return loads data for PrimeFaces component
	 *         <p:autoComplete id="city" ... />
	 */
	public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();
		results = sb.getCities();
		return results;
	}

	/**
	 * actionListener for
	 * <p:commandButton value="Obriši" ... />
	 * executes on button click
	 */
	public void clean() {
		setDisplay(factory
				.createDisplayElement(DisplayElementType.EMPTY_DISPLAY));
		this.display.loadData(null);
		setCity(null);
	}

	/**
	 * actionListener for
	 * <p:commandButton value="Dohvati" ... />
	 * executes on button click sends request to a web service sends response
	 * data to DisplayElements via their loadData method
	 */
	public void getWheather() {

		System.out.println("getWheather for " + getCity() + " type getnumber: "
				+ getDisplayType());
		initializeDisplay();

		System.out
				.println("-------------- parametri za web servis -----------------");
		// String qValue = "Zagreb";
		String url = sb.getURL();
		String charset = sb.getCharset();
		String formatValue = sb.getFormatValue();
		String numOfDays = sb.getNumberOfDays();
		String keyValue = sb.getKeyValue();
		String qValue = getCity();

		System.out.println("url: " + url);
		System.out.println("charset: " + charset);
		System.out.println("formatValue: " + formatValue);
		System.out.println("numOfDays: " + numOfDays);
		System.out.println("keyValue: " + keyValue);
		System.out.println("qValue: " + qValue);

		System.out
				.println("--------------------------------------------------------");

		try {
			String query = String.format(
					"q=%s&format=%s&num_of_days=%s&key=%s",
					URLEncoder.encode(qValue, charset),
					URLEncoder.encode(formatValue, charset),
					URLEncoder.encode(numOfDays, charset),
					URLEncoder.encode(keyValue, charset));
			URLConnection connection = new URL(url + "?" + query)
					.openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			// ovo mi treba za ispis
			// InputStream response = connection.getInputStream();
			// String responseString = getStringFromInputStream(response);
			// System.err.println("responseString: " + responseString);

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(connection.getInputStream());
			doc.getDocumentElement().normalize();

			display.loadData(doc);

		} catch (NullPointerException e) {
			setDisplay(factory
					.createDisplayElement(DisplayElementType.EMPTY_DISPLAY));
			this.display.setWeatherDesc("Greška! Grad ne postoji!");
			e.printStackTrace();

		} catch (Exception e) {
			setDisplay(factory
					.createDisplayElement(DisplayElementType.EMPTY_DISPLAY));
			this.display.setWeatherDesc("Greška!");
			e.printStackTrace();
		}

	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/*
	 * converts InputStream to String
	 */
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	/**
	 * @return
	 */
	public int getDisplayType() {
		return displayType;
	}

	/**
	 * @param displayType
	 */
	public void setDisplayType(int displayType) {
		System.err.println("set display type: " + displayType + "!!!");
		this.displayType = displayType;
	}

	/**
	 * @return
	 */
	public DisplayElement getDisplay() {
		return display;
	}

	/**
	 * @param display
	 */
	public void setDisplay(DisplayElement display) {
		this.display = display;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	/**
	 * uses DisplayElementFactory to create DisplayElement chosen by user
	 */
	private void initializeDisplay() {
		if (getDisplayType() == 1) {
			setDisplay(factory
					.createDisplayElement(DisplayElementType.CURRENT_WEATHER));
		} else if (getDisplayType() == 2) {
			setDisplay(factory
					.createDisplayElement(DisplayElementType.WEATHER_FORECAST));
		} else if (getDisplayType() == 0) {
			setDisplay(factory
					.createDisplayElement(DisplayElementType.EMPTY_DISPLAY));
		} else {
			throw new IllegalArgumentException("number " + getDisplayType()
					+ " is not allowed for DisplayElementType");
		}
	}

	/**
	 * @return
	 */
	public DisplayElementFactory getFactory() {
		return factory;
	}

	/**
	 * @param factory
	 */
	public void setFactory(DisplayElementFactory factory) {
		this.factory = factory;
	}

	private Boolean viewGrid = true;

	public Boolean getViewGrid() {
		return viewGrid;
	}

	public void setViewGrid(Boolean viewGrid) {
		this.viewGrid = viewGrid;
	}

	public void changeView(ActionEvent evt) {
		System.err.println("mijenjam view na: " + !getViewGrid());

		boolean newViewGrid = !getViewGrid();
		setViewGrid(newViewGrid);
		evt.getComponent().setRendered(true);

	}

}