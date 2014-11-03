package epam.jmp.task.multitreading.data;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import epam.jmp.task.multitreading.App;
import epam.jmp.task.multitreading.utils.XmlUtils;

public class CurrencyFactory
{
	static final Logger logger = Logger.getLogger(CurrencyFactory.class);

	private static final String PATH_BY_NAME = "/" + Currency.ROOT_NODE_NAME
			+ "/" + Currency.CURRENCY_NODE_NAME
			+ "[@" + Currency.ID_ATTR_NAME + "= '%s']";

	private File f = new File(App.CURRENCY_FILE_NAME);
	
	public CurrencyFactory()
	{
		this(App.CURRENCY_FILE_NAME);
	}
	
	public CurrencyFactory(String fileName)
	{
		f = new File(fileName);
	}
	
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
	
	public void createCurrencies() throws Exception
	{
		DocumentBuilder builder = XmlUtils.getDocumentBuilder();
		if (!f.exists())
		{
			logger.info("Create \"Currency\"");
			f.createNewFile();
			
			Document doc = builder.newDocument();
			Element rootElement = doc.createElement(Currency.ROOT_NODE_NAME);
			
			int i = 1;
			for (Currency.AvailableId currency : Currency.AvailableId.values())
			{
				Element elCurrency = XmlUtils.createNode(doc, Currency.CURRENCY_NODE_NAME, null);
				elCurrency.setAttribute(Currency.ID_ATTR_NAME, currency.toString());
				rootElement.appendChild(elCurrency);

				elCurrency.appendChild(XmlUtils.createNode(doc, Currency.BUYING_NODE_NAME, String.valueOf(i * 4000)));
				elCurrency.appendChild(XmlUtils.createNode(doc, Currency.SELLING_NODE_NAME, String.valueOf(i * 5000)));
				i++;
			}
			
			doc.appendChild(rootElement);
			
			XmlUtils.saveToFile(doc, f);
		}
	}
}
