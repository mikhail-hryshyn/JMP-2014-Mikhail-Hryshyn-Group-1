package epam.jmp.task.multitreading;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import epam.jmp.task.multitreading.data.Currency;
import epam.jmp.task.multitreading.data.PersonFactory;
import epam.jmp.task.multitreading.utils.XmlUtils;

public class GenerateFiles implements Runnable
{
	static final Logger logger = Logger.getLogger(GenerateFiles.class);
	
	private PersonFactory pf;
	
	public GenerateFiles(PersonFactory pf)
	{
		this.pf = pf;
	}

	public void run()
	{
		logger.info(Thread.currentThread());
		
		try
		{
			createCurrencies();
			pf.createPersons();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
	}
	
	
	

	private void createCurrencies() throws Exception
	{
		DocumentBuilder builder = XmlUtils.getDocumentBuilder();
		File f = new File(App.CURRENCY_FILE_NAME);
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
