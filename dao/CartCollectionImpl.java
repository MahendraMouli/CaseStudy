package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.*;

public class CartCollectionImpl implements CartDao{
	
	Map<Long, Cart> userCarts = null;
	
	public CartCollectionImpl()
	{
		if(userCarts == null)
			userCarts = new HashMap<>();
	}
	
	@Override
	public void addCartItem(long userId, long menuItemId) 
	{
		MenuItemDao menuItemDao = new MenuItemCollectionImpl();
		MenuItem item = menuItemDao.getMenuItem(menuItemId);
		
		if(userCarts.keySet().contains(userId))
		{
			Cart cart = userCarts.get(userId);
			List<MenuItem> menuItemList = cart.getMenuItemList();
			menuItemList.add(item);
		} 
		else
		{
			List<MenuItem> newList = new ArrayList<>();
			newList.add(item);
			Cart cart = new Cart(newList, 0);
			userCarts.put(userId, cart);			
		}
	}
	
	public Cart getAllCartItems(long userId) throws CartEmptyException
	{
		Cart cart = userCarts.get(userId);
		List <MenuItem> menuItemList = cart.getMenuItemList();
		if(menuItemList.isEmpty())
			throw new CartEmptyException("Cart is Empty");
		else
		{
			double sum = 0;
			for(MenuItem item: menuItemList)
				sum += item.getPrice();
			cart.setTotal(sum);
		}
		return cart;
	}
	
//	public boolean
	
	public void removeCartItem(long userId, long menuItemId)
	{
		Cart cart = userCarts.get(userId);
		List<MenuItem> menuItemList = cart.getMenuItemList();
		boolean check = false;
		for(MenuItem item : menuItemList)
		{
			if(item.getId() == menuItemId)
			{
				menuItemList.remove(item);
				check = true;
				break;
			}
		}
		if(!check)
			System.out.println("This id "+menuItemId+" is not in the cart of "+userId);
	}
}
