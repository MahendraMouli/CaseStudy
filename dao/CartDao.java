package dao;

import model.Cart;

public interface CartDao {

	void addCartItem(long userId, long menuItemId);
	Cart getAllCartItems(long userId) throws CartEmptyException;
	void removeCartItem(long userId, long menuItemId);
}
