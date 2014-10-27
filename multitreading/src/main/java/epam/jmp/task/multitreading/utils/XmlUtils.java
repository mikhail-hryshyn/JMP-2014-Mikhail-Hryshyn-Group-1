package epam.jmp.task.multitreading.utils;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlUtils
{

	static final Logger logger = Logger.getLogger(XmlUtils.class);
	
	public static DocumentBuilder getDocumentBuilder()
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try
		{
		    builder = builderFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			logger.error(e);
		}
		
		return builder;
	}
	
	public static Element createNode(Document doc, String node, String value)
	{
		Element newEl = doc.createElement(node);
		if (value != null)
		{
			newEl.appendChild(doc.createTextNode(value));
		}
		
		return newEl;
	}
	
	public static void saveToFile(Document doc, File f) throws Exception
	{
		FileOutputStream fOS = null;
		try
		{
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
	        
			DOMSource ds = new DOMSource(doc);
			fOS = new FileOutputStream(f);
			StreamResult sr = new StreamResult(fOS);
			
			t.transform(ds, sr);
		}
		finally
		{
			if (fOS != null) fOS.close();
		}
	}
}
