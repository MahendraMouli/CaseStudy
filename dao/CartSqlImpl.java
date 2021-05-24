package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.MenuItem;
import util.DateUtil;

public class CartSqlImpl implements CartDao{

	@Override
	public void addCartItem(long userId, long menuItemId) {
	
		try
		{
			Connection conn = ConnectionHandler.getConnection();
			String sql = "insert into cart values ("+userId+", "+menuItemId+")";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {

		try
		{
			Connection conn = ConnectionHandler.getConnection();
			List<MenuItem> menuItemList = new ArrayList<>();
			Cart cart = new Cart(menuItemList,0);
			
			String sql = "select m.id, m.name, m.price, m.active, day(m.dateOfLaunch), month(m.dateOfLaunch), year(m.dateOfLaunch), m.category, m.freeDelivery "
					+ "from Menu m join Cart c on c.menuItemId = m.id where c.userId = "+userId;
				
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String day= rs.getInt(5) < 10 ? "0"+rs.getString(5) : rs.getString(5);
				String month= rs.getInt(6) < 10 ? "0"+rs.getString(6) : rs.getString(6);
				String date = day + "/" + month + "/" + rs.getString(7);
				
				MenuItem item = new MenuItem(rs.getLong(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), DateUtil.convertToDate(date),  rs.getString(8), rs.getBoolean(9));
				menuItemList.add(item);
			}
			
			sql = "select sum(m.price) as total_price from menu m join cart c on c.menuItemId = m.id where c.userId ="+userId;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				cart.setTotal(rs.getDouble(1));
			}
			return cart;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
	
		try
		{
			Connection conn = ConnectionHandler.getConnection();
			String sql = "delete from cart where userId = "+userId+" and menuItemId = "+menuItemId;
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
}
