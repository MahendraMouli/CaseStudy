package dao;

@SuppressWarnings("serial")
public class CartEmptyException extends Exception{
	
	public CartEmptyException(String str)
	{
		super(str);
	}
}
