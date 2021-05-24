package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.DateUtil;
import model.MenuItem;

public class MenuItemCollectionImpl implements MenuItemDao {

	List<MenuItem> menuItemList = new ArrayList<>();
	
	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {

		List <MenuItem> customerMenuList = new ArrayList<>();
		for(MenuItem item : menuItemList)
		{
			if(item.getDateOfLaunch().isBefore(LocalDate.now()) && item.isActive())
				customerMenuList.add(item);
		}
		return customerMenuList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {

		for(MenuItem item : menuItemList)
		{
			if(item.equals(menuItem))
				 menuItemList.set(menuItemList.indexOf(item),menuItem);
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {

		for(MenuItem item : menuItemList)
		{
			if(item.getId() == menuItemId)
				return item;
		}
		return null;
	}

	MenuItemCollectionImpl() 
	{
		MenuItem menuItem1 = new MenuItem(1,"Sandwich",99,true,DateUtil.convertToDate("15/03/2017"),"Main Course",true);
		menuItemList.add(menuItem1);
		
		MenuItem menuItem2 = new MenuItem(2,"Burger",129,true,DateUtil.convertToDate("23/12/2017"),"Main Course",false);
		menuItemList.add(menuItem2);
		
		MenuItem menuItem3 = new MenuItem(3,"Pizza",149,true,DateUtil.convertToDate("21/08/2018"),"Main Course",false);
		menuItemList.add(menuItem3);
		
		MenuItem menuItem4 = new MenuItem(4,"French Fries",57,false,DateUtil.convertToDate("02/07/2017"),"Starters",true);
		menuItemList.add(menuItem4);
		
		MenuItem menuItem5 = new MenuItem(5,"Chocolate Brownie",32,true,DateUtil.convertToDate("02/11/2022"),"Dessert",true);
		menuItemList.add(menuItem5);

		/*
		System.out.println("Enter the number of Menu items to be added");
		int n = in.nextInt();

		for (int i = 1; i <= n; i++) {
			
			System.out.println("Enter the ID");
			long id = in.nextLong();
			
			in.nextLine();
			System.out.println("Enter the Product Name");
			String name = in.nextLine();
			
			System.out.println("Enter the price");
			double price = in.nextDouble();
			
			System.out.println("Is it Active or not? (yes/no)");
			boolean active = in.next().equalsIgnoreCase("yes");
		
			boolean checkDate = false;
			System.out.println("Enter the date of launch eg. 16/05/2021");
			do {
				LocalDate date = DateUtil.convertToDate(in.next());
				if(date == null)
					checkDate = true;
			}while(checkDate);
			
			System.out.println("Enter the category");
			String category = in.next();
			
			System.out.println("Free Delivery (yes/no)");
			boolean freeDelivery = in.next().equalsIgnoreCase("yes"); 
		}	
		*/
	}
}
