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
 * bean holds values to be shown in emptyDisplay.xhtml
 * it is instantiated by a DisplayElementFactory
 *
 */
@ManagedBean(name = "embean")
public class EmptyDisplayBean extends DisplayElement {
	
	
	/**
	 * @param det
	 * @param xhtmlFile
	 * constructor
	 */
	public EmptyDisplayBean (DisplayElementType det, String xhtmlFile) {
		super(det, xhtmlFile );
	}
	
	/* (non-Javadoc)
	 * @see hr.ikukovec.beans.DisplayElement#loadData(org.w3c.dom.Document)
	 */
	/**
	 * does not load data from web service response 
	 * sets properties of EmptyDisplayBean to default values
	 */
	public void loadData(Document doc) {								
		setWeatherDesc("Upišite grad, odaberite vrstu vremenskog izvješæa i kliknite na 'Dohvati'...");
		setWeatherIconUrl("white.jpg");
	}
	
	
}
