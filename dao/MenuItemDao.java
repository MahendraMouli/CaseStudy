package dao;

import model.MenuItem;
import java.util.*;

public interface MenuItemDao {

	List <MenuItem> getMenuItemListAdmin();
	List <MenuItem> getMenuItemListCustomer();
	void modifyMenuItem(MenuItem menuItem);
	MenuItem getMenuItem(long menuItemId);
}
