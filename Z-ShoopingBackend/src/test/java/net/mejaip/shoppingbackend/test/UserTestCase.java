package net.mejaip.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;

import net.mejaip.shoppingbackend.dao.UserDAO;
import net.mejaip.shoppingbackend.dto.Address;
import net.mejaip.shoppingbackend.dto.Cart;
import net.mejaip.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mejaip.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	/*
	 * @Test public void testAdd() { user = new User();
	 * user.setFirstName("Hrithik"); user.setLastName("Roshan");
	 * user.setEmail("he@gmail.com"); user.setContactNumber("1234512345");
	 * user.setRole("USER"); user.setPassword("12345");
	 * 
	 * // add user
	 * 
	 * assertEquals("Failed to add User", true, userDAO.addUser(user));
	 * 
	 * address = new Address();
	 * 
	 * address.setAddressLineOne("101/B Jadoo Socity, Kriss Nagar");
	 * address.setAddressLineTwo("Near Kabil Store"); address.setCity("Mumbai");
	 * address.setState("Maharashra"); address.setCountry("India");
	 * address.setPostalCode("12345"); address.setBilling(true);
	 * 
	 * //Link the User with the address using user id
	 * 
	 * address.setUserId(user.getId());
	 * 
	 * assertEquals("Faild to add Address", true, userDAO.addAddress(address));
	 * 
	 * if(user.getRole().equals("USER")) { // Creat cart for user
	 * 
	 * cart = new Cart(); cart.setUser(user);
	 * 
	 * assertEquals("faild to add cart", true, userDAO.addCart(cart));
	 * 
	 * // add the shipping address address = new Address();
	 * address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
	 * address.setAddressLineTwo("Near Kudrat Store"); address.setCity("Mumbai");
	 * address.setState("Maharashtra"); address.setCountry("India");
	 * address.setPostalCode("400001"); //Linking with the user
	 * 
	 * address.setUserId(user.getId());
	 * assertEquals("Failed to add the shipping address!", true,
	 * userDAO.addAddress(address)); } }
	 */

	/*
	 * @Test public void testAdd() { user = new User();
	 * user.setFirstName("Hrithik-4"); user.setLastName("Roshan");
	 * user.setEmail("he@gmail.com"); user.setContactNumber("1234512345");
	 * user.setRole("USER"); user.setPassword("12345");
	 * 
	 * // add user
	 * 
	 * if(user.getRole().equals("USER")) { // Creat cart for user
	 * 
	 * cart = new Cart(); cart.setUser(user);
	 * 
	 * //attach cart with the User
	 * 
	 * user.setCart(cart); } assertEquals("Failed to add User", true,
	 * userDAO.addUser(user));
	 * 
	 * }
	 */

	/*
	 * @Test public void testUpdateCart() { user =
	 * userDAO.getByEmail("he@gmail.com");
	 * 
	 * cart = user.getCart();
	 * 
	 * cart.setGrandTotal(5555);
	 * 
	 * cart.setCartLines(2);
	 * 
	 * assertEquals("Failed to update cart", true, userDAO.updateCart(cart)); }
	 */

	/*@Test
	public void testAddress() {
		// Adding the User
		user = new User();
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("he@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setPassword("12345");

		// add user

		assertEquals("Failed to add User", true, userDAO.addUser(user));

		// Adding the Address for Billing

		address = new Address();

		address.setAddressLineOne("101/B Jadoo Socity, Kriss Nagar");
		address.setAddressLineTwo("Near Kabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashra");
		address.setCountry("India");
		address.setPostalCode("12345");
		address.setBilling(true);

		address.setUser(user);

		assertEquals("Failed to add Address", true, userDAO.addAddress(address));
		// Adding the Address for Shipping

		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");

		address.setUser(user);

		assertEquals("Failed to add Address", true, userDAO.addAddress(address));
	}*/
	/*@Test
	public void testAddAddress() {
		
		// Need to add an User
		
		user = new User();
		user.setFirstName("Hrithik-5");
		user.setLastName("Roshan");
		user.setEmail("he1@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setPassword("12345");

		// add user

		assertEquals("Failed to add User", true, userDAO.addUser(user));
		
		// Add Address for billing 
		
		
		address = new Address();

		address.setAddressLineOne("99101/B Jadoo Socity, Kriss Nagar");
		address.setAddressLineTwo("Near Kabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashra");
		address.setCountry("India");
		address.setPostalCode("999999");
		address.setBilling(true);

		address.setUser(user);
		
		assertEquals("Failed to add Address", true, userDAO.addAddress(address));
		
		// Add Address for Shipping
		
		address = new Address();
		address.setAddressLineOne("99201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");

		address.setUser(user);

		assertEquals("Failed to add Shipping Address", true, userDAO.addAddress(address));
	}*/
	
	/*@Test
	public void testAddress()
	{	
		user = userDAO.getByEmail("he1@gmail.com");	
		address = new Address();
		address.setAddressLineOne("123/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Jaipur");
		address.setState("Rajasthan");
		address.setCountry("India");
		address.setPostalCode("400001");
		
		address.setShipping(true);
		
		address.setUser(user);
		
		assertEquals("Failed to add Shipping Address", true, userDAO.addAddress(address));
		
	}*/
	
	@Test
	public void testGetAddress() {
		user = userDAO.getByEmail("he1@gmail.com");
		assertEquals("Failed to fetch list of Address", 2, userDAO.listShippingAddress(user).size());
		
		
		assertEquals("Failed to fetch billing Address", "Mumbai", userDAO.getBillingAddress(user).getCity());
	}
}
