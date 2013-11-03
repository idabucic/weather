package hr.ikukovec.beans;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import hr.ikukovec.beans.DisplayElement.DisplayElementType;

/**
 * @author Ida
 * @version 1.0
 * @since 26.07.2013
 * 
 * this is a concrete class for a DisplayElement
 * bean holds values to be shown in currentWeather.xhtml
 * it is instantiated by a DisplayElementFactory
 *
 */
@ManagedBean(name = "cwbean")
public class CurrentWeatherBean extends DisplayElement {
	
	private String queryCity;
	private String temp;
	private String humidity;
	private String pressure;
	private String cloudcover;
	private String windspeedKmph;
	
	/**
	 * @param det
	 * @param xhtmlFile
	 * constructor
	 */
	public CurrentWeatherBean (DisplayElementType det, String xhtmlFile) {
		super(det, xhtmlFile );
	}
	
	/**
	 * @return
	 */
	public String getTemp() {
		return temp;
	}
	/**
	 * @param temp
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}
	/**
	 * @return
	 */
	public String getHumidity() {
		return humidity;
	}
	/**
	 * @param humidity
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	/**
	 * @return
	 */
	public String getPressure() {
		return pressure;
	}
	/**
	 * @param pressure
	 */
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	/**
	 * @return
	 */
	public String getCloudcover() {
		return cloudcover;
	}
	/**
	 * @param cloudcover
	 */
	public void setCloudcover(String cloudcover) {
		this.cloudcover = cloudcover;
	}
	/**
	 * @return
	 */
	public String getWindspeedKmph() {
		return windspeedKmph;
	}
	/**
	 * @param windspeedKmph
	 */
	public void setWindspeedKmph(String windspeedKmph) {
		this.windspeedKmph = windspeedKmph;
	}
	/**
	 * @return
	 */
	public String getQueryCity() {
		return queryCity;
	}

	/**
	 * @param queryCity
	 */
	public void setQueryCity(String queryCity) {
		this.queryCity = queryCity;
	}

	/* (non-Javadoc)
	 * @see hr.ikukovec.beans.DisplayElement#loadData(org.w3c.dom.Document)
	 */
	/** 
	 * loads data from web service response to properties of CurrentWeatherBean
	 * 
	 **/
	public void loadData(Document doc) {
			System.out.println("current conditions");
			NodeList list0 = doc.getElementsByTagName("request");
			Node n0 = list0.item(0);
			
			String qCity = Util.readQueryCity(doc);
			setQueryCity(qCity);
			
			NodeList list = doc.getElementsByTagName("current_condition");				 
			Node n = list.item(0);
		
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;				
				String temp = e.getElementsByTagName("temp_C").item(0).getTextContent();
				System.out.println("temp " + temp);
				setTemp(temp);
				
				String humidity = e.getElementsByTagName("humidity").item(0).getTextContent();
				System.out.println("humidity " + humidity);
				setHumidity(humidity);
				
				String pressure = e.getElementsByTagName("pressure").item(0).getTextContent();
				System.out.println("pressure " + pressure);
				setPressure(pressure);
				
				String cloudcover = e.getElementsByTagName("cloudcover").item(0).getTextContent();
				System.out.println("cloudcover " + cloudcover);
				setCloudcover(cloudcover);
				
				String windspeedKmph = e.getElementsByTagName("windspeedKmph").item(0).getTextContent();
				System.out.println("windspeedKmph " + windspeedKmph);
				setWindspeedKmph(windspeedKmph);
				
				String weatherDesc = e.getElementsByTagName("weatherDesc").item(0).getTextContent();
				System.out.println("weatherDesc " + weatherDesc);
				setWeatherDesc(weatherDesc);
				
				String weatherIconUrl = e.getElementsByTagName("weatherIconUrl").item(0).getTextContent();
				System.out.println("weatherIconUrl " + weatherIconUrl);
				setWeatherIconUrl(weatherIconUrl);
				
				
			}
		
	}
	
	
}
