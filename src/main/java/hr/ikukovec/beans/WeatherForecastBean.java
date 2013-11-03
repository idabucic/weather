package hr.ikukovec.beans;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Ida
 * @version 1.1
 * @since 26.07.2013
 * 
 * this is a concrete class for a DisplayElement
 * bean holds values to be shown in weatherForecast.xhtml
 * it is instantiated by a DisplayElementFactory
 *
 */
@ManagedBean(name = "wfbean")
public class WeatherForecastBean extends DisplayElement {
	
	private String queryCity;
	private String date;
	private String tempMaxC;
	private String tempMinC;
	
	/**
	 * @param det
	 * @param xhtmlFile
	 * constructor
	 */
	public WeatherForecastBean (DisplayElementType det, String xhtmlFile) {
			super(det, xhtmlFile );
	}
	
	/**
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return
	 */
	public String getTempMaxC() {
		return tempMaxC;
	}
	/**
	 * @param tempMaxC
	 */
	public void setTempMaxC(String tempMaxC) {
		this.tempMaxC = tempMaxC;
	}
	/**
	 * @return
	 */
	public String getTempMinC() {
		return tempMinC;
	}
	/**
	 * @param tempMinC
	 */
	public void setTempMinC(String tempMinC) {
		this.tempMinC = tempMinC;
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
	 * loads data from web service response to properties of WeatherForecastBean
	 * for independent use
	 **/
	public void loadData(Document doc) {
			System.out.println("weather forecast");
			String qCity = Util.readQueryCity(doc);
			setQueryCity(qCity);
			readDailyData(doc, 1);		
	}
	
	/** 
	 * loads data from web service response to properties of WeatherForecastBean
	 * called from ForecastBean
	 **/
	public void readDailyData(Document doc, int day) {
		NodeList nList = doc.getElementsByTagName("weather");
		Node nNode = nList.item(day);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {	 
			Element e = (Element) nNode;
			
			String date = e.getElementsByTagName("date").item(0).getTextContent();
			System.out.println("date " + date);
			setDate(Util.formatDate(date));
			
			String tempMaxC = e.getElementsByTagName("tempMaxC").item(0).getTextContent();
			System.out.println("tempMaxC " + tempMaxC);
			setTempMaxC(tempMaxC);
			
			String tempMinC = e.getElementsByTagName("tempMinC").item(0).getTextContent();
			System.out.println("tempMinC " + tempMinC);
			setTempMinC(tempMinC);
			
			String weatherDesc = e.getElementsByTagName("weatherDesc").item(0).getTextContent();
			System.out.println("weatherDesc " + weatherDesc);
			setWeatherDesc(weatherDesc);
			
			String weatherIconUrl = e.getElementsByTagName("weatherIconUrl").item(0).getTextContent();
			System.out.println("weatherIconUrl " + weatherIconUrl);
			setWeatherIconUrl(weatherIconUrl);
		}
	}
	
	
	
}
