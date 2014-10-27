package epam.jmp.task.multitreading;

import org.apache.log4j.Logger;

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
    	Thread thread1 = new Thread(new GenerateFiles(pf));
    	Thread thread2 = new Thread(new ReadFiles(pf));
    	thread2.start();
        thread1.start();
    }
}
