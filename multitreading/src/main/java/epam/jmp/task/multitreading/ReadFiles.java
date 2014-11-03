package epam.jmp.task.multitreading;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import epam.jmp.task.multitreading.data.Account;
import epam.jmp.task.multitreading.data.AccountFactory;
import epam.jmp.task.multitreading.data.Currency;
import epam.jmp.task.multitreading.data.Person;
import epam.jmp.task.multitreading.data.PersonFactory;

public class ReadFiles implements Runnable
{

	static final Logger logger = Logger.getLogger(ReadFiles.class);
	
	private PersonFactory pf;
	private AccountFactory af;
	
	public ReadFiles(PersonFactory pf, AccountFactory af)
	{
		this.pf = pf;
		this.af = af;
	}
	public void run()
	{
		logger.info(Thread.currentThread());
		
		try
		{
			List<Person> persons = pf.getAllPerson(af);
			
			for (Person person : persons)
			{
				logger.info(person.getID() + ": " + person.getFirstName() + " " + person.getLastName());
				ConcurrentMap<String, Account> accounts = person.getAccounts();
				for (Account account : accounts.values())
				{
					Currency c = account.getCurrency();
					logger.info("\t" + account.getNumber() + ": " + account.getAmount() + " " + c.getCurrency());
					logger.info("\t\t" + c.getBuying() + " " + c.getSelling());
					
				}
				
			}
		}
		catch (Exception e)
		{
			logger.error(e);
		}
	}

}
