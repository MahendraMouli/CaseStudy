package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.MenuItem;
import util.DateUtil;

public class MenuItemSqlImpl implements MenuItemDao{

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		
		List<MenuItem> adminMenuList = new ArrayList<>();
		try
		{
			Connection conn = ConnectionHandler.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select id, name, price, active, day(dateOfLaunch), month(dateOfLaunch), year(dateOfLaunch), category, freeDelivery from Menu");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String day= rs.getInt(5) < 10 ? "0"+rs.getString(5) : rs.getString(5);
				String month= rs.getInt(6) < 10 ? "0"+rs.getString(6) : rs.getString(6);
				String date = day + "/" + month + "/" + rs.getString(7);
				
				MenuItem item = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), DateUtil.convertToDate(date),  rs.getString(8), rs.getBoolean(9));
				adminMenuList.add(item);				
			}
			return adminMenuList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {

		List<MenuItem> customerMenuList = new ArrayList<>();
		
		try
		{
			Connection conn = ConnectionHandler.getConnection();
			String sql = "select id, name, price, active, day(dateOfLaunch), month(dateOfLaunch), year(dateOfLaunch), category, freeDelivery from Menu where dateOfLaunch <= '"+LocalDate.now()+"' and active = 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String day= rs.getInt(5) < 10 ? "0"+rs.getString(5) : rs.getString(5);
				String month= rs.getInt(6) < 10 ? "0"+rs.getString(6) : rs.getString(6);
				String date = day + "/" + month + "/" + rs.getString(7);
				
				MenuItem item = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), DateUtil.convertToDate(date),  rs.getString(8), rs.getBoolean(9));
				customerMenuList.add(item);				
			}
			return customerMenuList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {

		try
		{
			Connection conn = ConnectionHandler.getConnection();
			String sql = "update Menu set name = '"+menuItem.getName()+"' , price = "+menuItem.getPrice()+", active = "+menuItem.isActive()+", dateOfLaunch = '"+menuItem.getDateOfLaunch()+"', category = '"+menuItem.getCategory()+"', freeDelivery = "+menuItem.isFreeDelivery()+" where id = "+menuItem.getId();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {

		MenuItem item = null;
		try
		{
			Connection conn = ConnectionHandler.getConnection();
			String sql = "select id, name, price, active, day(dateOfLaunch), month(dateOfLaunch), year(dateOfLaunch), category, freeDelivery from Menu where id = "+menuItemId;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String day= rs.getInt(5) < 10 ? "0"+rs.getString(5) : rs.getString(5);
				String month= rs.getInt(6) < 10 ? "0"+rs.getString(6) : rs.getString(6);
				String date = day + "/" + month + "/" + rs.getString(7);
				
				item = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), DateUtil.convertToDate(date),  rs.getString(8), rs.getBoolean(9));
			}
			return item;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
}
