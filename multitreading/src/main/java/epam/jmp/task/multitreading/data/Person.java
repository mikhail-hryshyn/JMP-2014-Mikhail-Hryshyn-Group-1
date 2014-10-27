package epam.jmp.task.multitreading.data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Person
{
	public static final String ROOT_NODE_NAME = "Persons";
	public static final String PERSON_NODE_NAME = "Person";
	
	public static final String ID_NODE_NAME = "ID";
	public static final String FN_NODE_NAME = "FirstName";
	public static final String LN_NODE_NAME = "LastName";
	public static final String ACCOUNTS_NODE_NAME = "Accounts";
	public static final String ACCOUNT_NODE_NAME = "Account";
	
	private String id;
	private String firstName;
	private String lastName;
	private ConcurrentMap<String, Account> Accounts = new ConcurrentHashMap<String, Account>();
	
	public String getID()
	{
		return id;
	}

	public void setID(String id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public ConcurrentMap<String, Account> getAccounts()
	{
		return Accounts;
	}
	
	public void setAccounts(ConcurrentMap<String, Account> accounts) {
		Accounts = accounts;
	}
	
	public Account getAccount(Integer number)
	{
		return Accounts.get(number);
	}
	
	public void setAccount(Account account) {
		Accounts.put(account.getNumber(), account);
	}
}
