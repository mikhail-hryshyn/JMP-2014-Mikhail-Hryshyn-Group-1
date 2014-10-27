package epam.jmp.task.multitreading.data;

public class Currency
{
	public static enum AvailableId
	{
		USD, EUR, RUR
	}
	
	public static final String ROOT_NODE_NAME = "Currencies";
	public static final String CURRENCY_NODE_NAME = "Currency";
	public static final String BUYING_NODE_NAME = "Buying";
	public static final String SELLING_NODE_NAME = "Selling";
	
	public static final String ID_ATTR_NAME = "id";
	
	private String currency;
	private String buying;
	private String selling;
	
	public String getCurrency()
	{
		return currency;
	}
	
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	
	public String getBuying()
	{
		return buying;
	}
	
	public void setBuying(String buying)
	{
		this.buying = buying;
	}
	
	public String getSelling()
	{
		return selling;
	}
	
	public void setSelling(String selling)
	{
		this.selling = selling;
	}
	
	
}
