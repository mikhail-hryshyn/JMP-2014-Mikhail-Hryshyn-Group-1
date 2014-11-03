package epam.jmp.task.multitreading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;

import org.junit.AfterClass;
import org.junit.Test;

import epam.jmp.task.multitreading.data.AccountFactory;
import epam.jmp.task.multitreading.data.Currency;
import epam.jmp.task.multitreading.data.CurrencyFactory;
import epam.jmp.task.multitreading.data.PersonFactory;
import epam.jmp.task.multitreading.utils.XmlUtils;


public class FilesTest
{
	
	private static final String TEST_ACCOUNT_FILE_NAME = "AccountTest.xml";
	private static final String TEST_CURRENCY_FILE_NAME = "CurrencyTest.xml";
	private static final String TEST_PERSON_FILE_NAME = "PersonTest.xml";
	
	private DocumentBuilder builder = XmlUtils.getDocumentBuilder();

	@Test
	public void currencyFile() throws Exception
	{
		CurrencyFactory cf = new CurrencyFactory(TEST_CURRENCY_FILE_NAME);
		cf.createCurrencies();
		
		File f = new File(TEST_CURRENCY_FILE_NAME);
		assertTrue(f.exists());
		
		Currency currency =  cf.getCurrencyByName(builder, Currency.AvailableId.EUR.toString());
		assertEquals(currency.getCurrency(), Currency.AvailableId.EUR.toString());
	}
	
	@Test
	public void personFile() throws Exception
	{
		PersonFactory pf = new PersonFactory(TEST_PERSON_FILE_NAME);
		AccountFactory af = new AccountFactory(TEST_ACCOUNT_FILE_NAME);
		pf.createPersons(af);
		
		File f = new File(TEST_PERSON_FILE_NAME);
		assertTrue(f.exists());
		
		f = new File(TEST_ACCOUNT_FILE_NAME);
		assertTrue(f.exists());
	}
	
	@AfterClass
	public static void after()
	{
		File f = new File(TEST_CURRENCY_FILE_NAME);
		if (f.exists())
		{
			f.delete();
		}
		
		f = new File(TEST_PERSON_FILE_NAME);
		if (f.exists())
		{
			f.delete();
		}
		
		f = new File(TEST_ACCOUNT_FILE_NAME);
		if (f.exists())
		{
			f.delete();
		}
	}
}
