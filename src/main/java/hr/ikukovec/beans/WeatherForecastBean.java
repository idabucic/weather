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
 * @version 1.0
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
	 * 
	 **/
	public void loadData(Document doc) {
			System.out.println("weather forecast");
			
			NodeList list0 = doc.getElementsByTagName("request");
			Node n0 = list0.item(0);
			if (n0.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n0;
				String query = e.getElementsByTagName("query").item(0).getTextContent();
				System.out.println("queryCity " + query);
				setQueryCity(query);
			}
			NodeList nList = doc.getElementsByTagName("weather");
		 
			//for (int temp = 0; temp < nList.getLength(); temp++) {
				//Node nNode = nList.item(temp);
				Node nNode = nList.item(1);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element e = (Element) nNode;
					
					String date = e.getElementsByTagName("date").item(0).getTextContent();
					System.out.println("date " + date);
					setDate(date);
					
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
			//}
		
	}
	
}
