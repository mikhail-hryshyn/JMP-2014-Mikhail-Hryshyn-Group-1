package epam.jmp.task.multitreading.data;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import epam.jmp.task.multitreading.App;

public class CurrencyFactory
{
	static final Logger logger = Logger.getLogger(CurrencyFactory.class);

	private static final String PATH_BY_NAME = "/" + Currency.ROOT_NODE_NAME
			+ "/" + Currency.CURRENCY_NODE_NAME
			+ "[@" + Currency.ID_ATTR_NAME + "= '%s']";

	private File f = new File(App.CURRENCY_FILE_NAME);
	
	public Currency getCurrencyByName(DocumentBuilder builder, String name) throws Exception
	{
		XPath xPath =  XPathFactory.newInstance().newXPath();
		Document doc = builder.parse(f);
		
		String exp = String.format(PATH_BY_NAME, name);
		String bExp = exp + "/" + Currency.BUYING_NODE_NAME;
		String sExp = exp + "/" + Currency.SELLING_NODE_NAME;
		
		Currency c = new Currency();
		c.setCurrency(name);
		c.setBuying(xPath.compile(bExp).evaluate(doc));
		c.setSelling(xPath.compile(sExp).evaluate(doc));
		
		return c;
	}
}
