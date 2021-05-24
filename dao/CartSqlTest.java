package dao;

import model.Cart;
import model.MenuItem;

public class CartSqlTest {

	CartDao cartDao = new CartSqlImpl();
	
	public static void main(String[] args) throws CartEmptyException
	{
		CartSqlTest testObj = new CartSqlTest();
		System.out.println("Cart items:");
		testObj.testAddCartItem();
		testObj.testGetAllCartItems();
		
		System.out.println("Cart items after remove:");
		testObj.testRemoveCartItem();
		testObj.testGetAllCartItems();
	}
	
	public void testRemoveCartItem()
	{
		cartDao.removeCartItem(2, 4);
		cartDao.removeCartItem(2, 3);
	}
	
	 public void testAddCartItem()
	 {
		 cartDao.addCartItem(1, 1);
		 cartDao.addCartItem(1, 2);
		 cartDao.addCartItem(2, 3);
		 cartDao.addCartItem(2, 4);
	 }
	 
	 public void testGetAllCartItems() throws CartEmptyException
	 {
		 Cart cart = cartDao.getAllCartItems(2);
		 
		 for(MenuItem item : cart.getMenuItemList())
			 System.out.println(item.toString());
		 
		 System.out.println(cart.getTotal() + "\n");
	 }

}
