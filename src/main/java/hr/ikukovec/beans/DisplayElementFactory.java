package hr.ikukovec.beans;

import javax.ejb.Local;
import javax.faces.bean.ManagedBean;

import hr.ikukovec.beans.DisplayElement.DisplayElementType;


/**
 * @author Ida
 * @version 1.0
 * @since 26.07.2013
 * 
 * this is a factory class 
 * encapsulates object creation for all DisplayElements
 *
 */
public class DisplayElementFactory {
	
	public void logMethod() {
		System.out.println("DisplayElementFactory alive");
	}
	
	/**
	 * @param det
	 * @return
	 * depending on DisplayElementType
	 * instantiates concrete implementations of DisplayElement class
	 * sets value for xhtmlFile property using constructors
	 */
	public DisplayElement createDisplayElement(DisplayElementType det) {
		System.out.println("DisplayElementFactory creating concrete DisplayElement type: " + det.getType());
		DisplayElement de = null;
		if (det.getType() == DisplayElementType.CURRENT_WEATHER.getType()) {
			String xhtmlFile = "currentWeather.xhtml";
			de = new CurrentWeatherBean(det, xhtmlFile);
		} else if (det.getType() == DisplayElementType.WEATHER_FORECAST.getType()){
			String xhtmlFile = "weatherForecast.xhtml";
			de = new WeatherForecastBean(det, xhtmlFile);
		} else if (det.getType() == DisplayElementType.EMPTY_DISPLAY.getType()){
			String xhtmlFile = "emptyDisplay.xhtml";
			de = new EmptyDisplayBean(det, xhtmlFile);
		}
		return de;
	}
}
