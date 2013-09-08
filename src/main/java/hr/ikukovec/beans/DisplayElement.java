package hr.ikukovec.beans;

import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


/**
 * @author Ida
 * @version 1.0
 * @since 26.07.2013
 * 
 * this is an abstract class 
 * it is created for concrete classes for display elements to extend from it
 *
 */
public abstract class DisplayElement {
	
	public enum DisplayElementType {
		CURRENT_WEATHER(1),
		WEATHER_FORECAST(2),
		EMPTY_DISPLAY(0);
		
		private int type;
		private DisplayElementType (int type) {
			this.type = type;
		}
		
		public DisplayElementType getDisplayElementType (int type) {
			if (type == 1) {
				return CURRENT_WEATHER;
			} else if (type == 2) {
				return WEATHER_FORECAST;
			} else if (type == 0) {
				return EMPTY_DISPLAY;
			} else {
				throw new IllegalArgumentException("type " + type + " is not allowed for DisplayElementType");
			}
		}
		
		public int getType() {
			return type;
		}
	}
	
	private DisplayElementType det;
	private String xhtmlFile;
	
	private String weatherDesc;
	private String weatherIconUrl;
	
	protected DisplayElement (DisplayElementType det, String xhtmlFile) {
		this.det  = det;
		this.xhtmlFile = xhtmlFile;
	}
	
	/**
	 * @return
	 */
	public String getWeatherDesc() {
		return weatherDesc;
	}
	/**
	 * @param weatherDesc
	 */
	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}
	/**
	 * @return
	 */
	public String getWeatherIconUrl() {
		return weatherIconUrl;
	}
	/**
	 * @param weatherIconUrl
	 */
	public void setWeatherIconUrl(String weatherIconUrl) {
		this.weatherIconUrl = weatherIconUrl;
	}
	
	/**
	 * @return
	 */
	public String getXhtmlFile() {
		return xhtmlFile;
	}
	/**
	 * @param xhtmlFile
	 */
	public void setXhtmlFile(String xhtmlFile) {
		this.xhtmlFile = xhtmlFile;
	}
	
	/**
	 * @param doc
	 */
	public abstract void loadData(Document doc);
	
}
