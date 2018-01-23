package net.mejaip.onlineshopping.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.mejaip.onlineshopping.model.RegisterModel;
import net.mejaip.shoppingbackend.dao.UserDAO;
import net.mejaip.shoppingbackend.dto.Address;
import net.mejaip.shoppingbackend.dto.Cart;
import net.mejaip.shoppingbackend.dto.User;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO ;
	
	public  RegisterModel init()
	{
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user)
	{
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing)
	{
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model)
	{
		String transitionValue ="success";
		
		//Fetch the USer
		User user=model.getUser();
		
		if(user.getRole().equals("USER"))
		{
			Cart cart= new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		userDAO.addUser(user);
		
		//get the billing address
		
		Address billing = model.getBilling();
		
		billing.setUser(user);
		
		billing.setBilling(true);
		
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
}
