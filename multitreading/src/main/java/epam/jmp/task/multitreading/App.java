package epam.jmp.task.multitreading;

import org.apache.log4j.Logger;

import epam.jmp.task.multitreading.data.AccountFactory;
import epam.jmp.task.multitreading.data.PersonFactory;

public class App 
{
	static final Logger logger = Logger.getLogger(App.class);
	
	public static final String ACCOUNT_FILE_NAME = "Account.xml";
	public static final String CURRENCY_FILE_NAME = "Currency.xml";
	public static final String PERSON_FILE_NAME = "Person.xml";

    public static void main( String[] args ) throws Exception
    {
    	PersonFactory pf = new PersonFactory();
    	AccountFactory af = new AccountFactory();
    	Thread thread1 = new Thread(new GenerateFiles(pf, af));
    	Thread thread2 = new Thread(new ReadFiles(pf, af));
    	thread2.start();
        thread1.start();
    }
}
