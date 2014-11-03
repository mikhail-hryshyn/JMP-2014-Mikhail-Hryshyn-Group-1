package epam.jmp.task.multitreading;

import org.apache.log4j.Logger;

import epam.jmp.task.multitreading.data.AccountFactory;
import epam.jmp.task.multitreading.data.CurrencyFactory;
import epam.jmp.task.multitreading.data.PersonFactory;

public class GenerateFiles implements Runnable
{
	static final Logger logger = Logger.getLogger(GenerateFiles.class);
	
	private PersonFactory pf;
	private AccountFactory af;
	
	public GenerateFiles(PersonFactory pf, AccountFactory af)
	{
		this.pf = pf;
		this.af = af;
	}

	public void run()
	{
		logger.info(Thread.currentThread());
		
		try
		{
			CurrencyFactory cf = new CurrencyFactory();
			cf.createCurrencies();
			pf.createPersons(af);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
	}
}
