package epam.jmp.task.multitreading.data;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import epam.jmp.task.multitreading.App;
import epam.jmp.task.multitreading.utils.XmlUtils;

public class AccountFactory
{
	static final Logger logger = Logger.getLogger(AccountFactory.class);
	
	private static final String PATH_BY_NUMBER = "/" + Account.ROOT_NODE_NAME
			+ "/" + Account.ACCOUNT_NODE_NAME
			+ "[" + Account.NUMBER_NODE_NAME + "= '%s']";
	
	private File f;
	
	public AccountFactory()
	{
		this(App.ACCOUNT_FILE_NAME);
	}
	
	public AccountFactory(String fileName)
	{
		f = new File(fileName);
	}
	
	public Account getAccountByNumber(DocumentBuilder builder, String number) throws Exception
	{
		XPath xPath =  XPathFactory.newInstance().newXPath();
		Document doc = builder.parse(f);
		
		String exp = String.format(PATH_BY_NUMBER, number);
		String cExp = exp + "/" + Account.CURRENCY_NODE_NAME;
		String aExp = exp + "/" + Account.AMOUNT_NODE_NAME;
		
		Account a = new Account();
		a.setNumber(number);
		a.setAmount(xPath.compile(aExp).evaluate(doc));
		
		CurrencyFactory cf = new CurrencyFactory(); 
		a.setCurrency(cf.getCurrencyByName(builder, xPath.compile(cExp).evaluate(doc)));
		
		
		return a;
	}
	
	public List<String> createAccounts(DocumentBuilder builder) throws Exception
	{
		Document doc;
		Element rootElement;
		if (!f.exists())
		{
			logger.info("Create \"Account\"");
			f.createNewFile();
			
			doc = builder.newDocument();
			rootElement = doc.createElement(Account.ROOT_NODE_NAME);
			doc.appendChild(rootElement);
		}
		else
		{
			logger.info("Update \"Account\"");
			doc = builder.parse(f);
			rootElement = (Element) doc.getFirstChild();
		}
		
		List<String> iDs = new CopyOnWriteArrayList<String>();
		for (Currency.AvailableId currency : Currency.AvailableId.values())
		{
			Element elAccount = XmlUtils.createNode(doc, Account.ACCOUNT_NODE_NAME, null);
			rootElement.appendChild(elAccount);
			
			String id = UUID.randomUUID().toString().toUpperCase();
			iDs.add(id);
			
			elAccount.appendChild(XmlUtils.createNode(doc, Account.NUMBER_NODE_NAME, id));
			elAccount.appendChild(XmlUtils.createNode(doc, Account.CURRENCY_NODE_NAME, currency.toString()));
			elAccount.appendChild(XmlUtils.createNode(doc, Account.AMOUNT_NODE_NAME, "10000000"));
		}
		
		XmlUtils.saveToFile(doc, f);
		
		return iDs;
	}
	
}
