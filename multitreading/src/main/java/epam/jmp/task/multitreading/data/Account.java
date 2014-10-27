package epam.jmp.task.multitreading.data;

public class Account
{
	public static final String ROOT_NODE_NAME = "Accounts";
	public static final String ACCOUNT_NODE_NAME = "Account";
	public static final String NUMBER_NODE_NAME = "Number";
	public static final String CURRENCY_NODE_NAME = "Currency";
	public static final String AMOUNT_NODE_NAME = "Amount";
	
	private String number;
	private Currency currency;
	private String amount;
	
	public String getNumber()
	{
		return number;
	}
	
	public void setNumber(String number)
	{
		this.number = number;
	}
	
	public Currency getCurrency()
	{
		return currency;
	}
	
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}
	
	public String getAmount()
	{
		return amount;
	}
	
	public void setAmount(String amount)
	{
		this.amount = amount;
	}
	
}
