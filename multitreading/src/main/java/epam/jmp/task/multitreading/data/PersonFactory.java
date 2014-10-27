package epam.jmp.task.multitreading.data;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import epam.jmp.task.multitreading.App;
import epam.jmp.task.multitreading.utils.XmlUtils;

public class PersonFactory
{
	static final Logger logger = Logger.getLogger(PersonFactory.class);
	
	private static final String PATH_BY_ID = "/" + Person.ROOT_NODE_NAME
			+ "/" + Person.PERSON_NODE_NAME
			+ "[" + Person.ID_NODE_NAME + "= '%s']";
	
	private File f = new File(App.PERSON_FILE_NAME);
	private DocumentBuilder builder = XmlUtils.getDocumentBuilder();
	
	public synchronized List<Person> getAllPerson() throws Exception
	{
		if (!f.exists())
		{
			try
			{
				logger.info(Thread.currentThread() + ": getAllPerson() wait");
				wait();
            }
			catch (InterruptedException ie)
			{
                logger.error(ie);
            }
		}
		
		Document doc = builder.parse(f);
		List<Person> p = new CopyOnWriteArrayList<Person>();
		
		NodeList nodeList = doc.getElementsByTagName(Person.ID_NODE_NAME);
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			p.add(getPersonById(doc, nodeList.item(i).getFirstChild().getNodeValue()));
		}
		
		return p;
	}
	
	private synchronized Person getPersonById(Document doc, String id) throws Exception
	{
		XPath xPath =  XPathFactory.newInstance().newXPath();
		
		Person p = new Person();
		String exp = String.format(PATH_BY_ID, id);
		String fnExp = exp + "/" + Person.FN_NODE_NAME;
		String lnExp = exp + "/" + Person.LN_NODE_NAME;
		String aExp = exp + "/" + Person.ACCOUNTS_NODE_NAME + "/" + Person.ACCOUNT_NODE_NAME;
		
		p.setID(id);
		p.setFirstName(xPath.compile(fnExp).evaluate(doc));
		p.setLastName(xPath.compile(lnExp).evaluate(doc));
		
		AccountFactory af = new AccountFactory();
		
		NodeList nodeList = (NodeList) xPath.compile(aExp).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			p.setAccount(af.getAccountByNumber(builder, nodeList.item(i).getFirstChild().getNodeValue()));
		}
		
		return p;
	}
	
	public synchronized void createPersons() throws Exception
	{
		if (!f.exists())
		{
			logger.info("Create \"Person\"");
			f.createNewFile();
			
			Document doc = builder.newDocument();
			Element rootElement = doc.createElement(Person.ROOT_NODE_NAME);
			
			String fN = "FirstName";
			String lN = "LastName";
			
			for (int i = 1; i < 5; i++)
			{
				Element elPerson = XmlUtils.createNode(doc, Person.PERSON_NODE_NAME, null);
				rootElement.appendChild(elPerson);
				
				elPerson.appendChild(XmlUtils.createNode(doc, Person.ID_NODE_NAME, String.valueOf(i)));
				elPerson.appendChild(XmlUtils.createNode(doc, Person.FN_NODE_NAME, fN + String.valueOf(i)));
				elPerson.appendChild(XmlUtils.createNode(doc, Person.LN_NODE_NAME, lN + String.valueOf(i)));
				
				Element elAccounts = XmlUtils.createNode(doc, Person.ACCOUNTS_NODE_NAME, null);
				elPerson.appendChild(elAccounts);
				
				AccountFactory af = new AccountFactory();
				List<String> iDs = af.createAccounts(builder);
				for (String id : iDs)
				{
					elAccounts.appendChild(XmlUtils.createNode(doc, Person.ACCOUNT_NODE_NAME, id));
				}
				
			}
			
			doc.appendChild(rootElement);
			
			XmlUtils.saveToFile(doc, f);
			
			logger.info(Thread.currentThread() + ": createPersons() notify");
			notify();
		}
	}
	
}
