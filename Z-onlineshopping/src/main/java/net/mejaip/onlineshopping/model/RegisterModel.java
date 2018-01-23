package net.mejaip.onlineshopping.model;

import java.io.Serializable;

import net.mejaip.shoppingbackend.dto.Address;
import net.mejaip.shoppingbackend.dto.User;

public class RegisterModel implements Serializable {
	
	private User user;
	private Address billing;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	

}
