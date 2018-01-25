package net.mejaip.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mejaip.shoppingbackend.dao.CartLineDAO;
import net.mejaip.shoppingbackend.dao.ProductDAO;
import net.mejaip.shoppingbackend.dao.UserDAO;
import net.mejaip.shoppingbackend.dto.Cart;
import net.mejaip.shoppingbackend.dto.CartLine;
import net.mejaip.shoppingbackend.dto.Product;
import net.mejaip.shoppingbackend.dto.User;



public class CartLineTestCase {
	

	private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private CartLine cartLine = null;
	private User user=null;
	private Cart cart=null;
	private Product product=null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mejaip.shoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	
	@Test
	public void testAddCartLine()
	{
		// 1. get the user
		
		user = userDAO.getByEmail("amd@gmail.com");
		
		// 2. Fetch the cart
		
		cart = user.getCart();
		
		// 3. get the product
		
		product = productDAO.get(1);
		
		// 4. Create new cartline
		
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setTotal(cartLine.getProductCount()* product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the cartline", true,  cartLineDAO.add(cartLine));
		
		
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("Failed to add the cart", true,  cartLineDAO.updateCart(cart));
	}

}
