package dao;

import java.util.List;
import java.util.Scanner;

import model.MenuItem;
import util.DateUtil;


public class MenuItemCollectionTest {

	MenuItemDao menuItemDao = new MenuItemCollectionImpl();
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		MenuItemCollectionTest testObj = new MenuItemCollectionTest();
		System.out.println("Menu List of Admin");
		testObj.testGetMenuItemListAdmin();
		
		System.out.println("Menu List of Customers");
		testObj.testGetMenuItemListCustomer();
		
		System.out.println("Modified Menu List");
		MenuItem item = new MenuItem(1,"Sandwich",130,true,DateUtil.convertToDate("15/03/2017"),"Main Course",true);
		testObj.testModifyMenuItem(item);
		
		System.out.println("Admin");
		testObj.testGetMenuItemListAdmin();
		System.out.println("Customer");
		testObj.testGetMenuItemListCustomer();
		
		System.out.println("Enter the product id to be searched");
		long id = in.nextLong();
		testObj.testGetMenuItem(id);
	}
	
	public void testGetMenuItemListAdmin()
	{
		List <MenuItem> adminMenuList = menuItemDao.getMenuItemListAdmin();
		for(MenuItem item : adminMenuList)
			System.out.println(item.toString());
		System.out.println("\n");
	}
	
	public void testGetMenuItemListCustomer()
	{
		List <MenuItem> customerMenuList = menuItemDao.getMenuItemListCustomer();
		for(MenuItem item : customerMenuList)
			System.out.println(item.toString());
		System.out.println("\n");
	}
	
	public void testModifyMenuItem(MenuItem item)
	{
		menuItemDao.modifyMenuItem(item);
	}
	
	public void testGetMenuItem(long menuItemId)
	{
		MenuItem item = menuItemDao.getMenuItem(menuItemId);
		if(item != null)
			System.out.println(item.toString());
		else
			System.out.println("No menu item for this ID "+ menuItemId);
	}
}
