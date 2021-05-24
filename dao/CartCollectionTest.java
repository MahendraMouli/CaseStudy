package dao;

import model.Cart;
import model.MenuItem;

public class CartCollectionTest {

	CartDao cartDao = new CartCollectionImpl();
	
	public static void main(String[] args) throws CartEmptyException 
	{	
		CartCollectionTest testObj = new CartCollectionTest();
		System.out.println("Cart items:");
		testObj.testAddCartItem();
		testObj.testGetAllCartItems();
		
		System.out.println("Cart items after remove:");
		testObj.testRemoveCartItem();
		testObj.testGetAllCartItems();
	}
	
	public void testAddCartItem()
	{
		cartDao.addCartItem(1, 1);
		cartDao.addCartItem(1, 2);
		cartDao.addCartItem(2, 3);
		cartDao.addCartItem(2, 4);
	}
	public void testRemoveCartItem()
	{
		cartDao.removeCartItem(2, 4);
	}
	
	public void testGetAllCartItems() throws CartEmptyException
	{
		Cart cart = cartDao.getAllCartItems(2);
	
//		for(MenuItem item : cart.getMenuItemList())
//			System.out.println(item.toString());
		
		System.out.println(cart.toString());
//		System.out.println("Total Bill Amount : "+cart.getTotal()+"\n");
	}
	
}
