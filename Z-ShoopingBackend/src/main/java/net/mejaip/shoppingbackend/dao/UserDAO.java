package net.mejaip.shoppingbackend.dao;

import java.util.List;

import net.mejaip.shoppingbackend.dto.Address;
import net.mejaip.shoppingbackend.dto.Cart;
import net.mejaip.shoppingbackend.dto.User;

public interface UserDAO {
	
	boolean addUser(User user);
	
	User getByEmail(String mail);
	
	boolean addAddress(Address address);
	
	Address getBillingAddress(User user);
	
	List<Address> listShippingAddress(User user);
	
	boolean updateCart(Cart cart);
	
	

}
