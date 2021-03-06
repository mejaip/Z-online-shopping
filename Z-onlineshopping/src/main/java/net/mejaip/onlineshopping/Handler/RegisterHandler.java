package net.mejaip.onlineshopping.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.mejaip.onlineshopping.model.RegisterModel;
import net.mejaip.shoppingbackend.dao.UserDAO;
import net.mejaip.shoppingbackend.dto.Address;
import net.mejaip.shoppingbackend.dto.Cart;
import net.mejaip.shoppingbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public RegisterModel init() {
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}

	public String saveAll(RegisterModel model) {
		String transitionValue = "success";

		// Fetch the USer
		User user = model.getUser();

		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//encode the password
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//Save the USer

		userDAO.addUser(user);

		// get the billing address

		Address billing = model.getBilling();

		billing.setUser(user);

		billing.setBilling(true);

		userDAO.addAddress(billing);

		return transitionValue;
	}

	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		// If password matches  confirm password
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password Does not match the confirm Password!")
					.build()
					);
			transitionValue = "failure";
		}
		if(userDAO.getByEmail(user.getEmail()) != null)
		{
				error.addMessage(new MessageBuilder()
						.error()
						.source("email")
						.defaultText("Email Address Already exist!")
						.build()
						);
			transitionValue = "failure";
	}
		return transitionValue;
	}
}
