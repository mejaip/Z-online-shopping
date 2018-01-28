package net.mejaip.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mejaip.onlineshopping.model.UserModel;
import net.mejaip.shoppingbackend.dao.CartLineDAO;
import net.mejaip.shoppingbackend.dao.ProductDAO;
import net.mejaip.shoppingbackend.dto.Cart;
import net.mejaip.shoppingbackend.dto.CartLine;
import net.mejaip.shoppingbackend.dto.Product;

@Service
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private HttpSession session;

	@Autowired
	private ProductDAO productDAO;

	// Return the Cart of the user who logged in
	private Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	// returns the entire cart
	public List<CartLine> getCartLines() {
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());
	}

	public String updateCartLine(int cartLineId, int count) {
		// TODO Auto-generated method stub
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null) {
			return "result=error";
		} else {
			Product product = cartLine.getProduct();

			double oldTotal = cartLine.getTotal();

			if (product.getQuantity() <= count) {
				count = product.getQuantity();
			}

			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);

			cartLineDAO.update(cartLine);

			Cart cart = this.getCart();

			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());

			cartLineDAO.updateCart(cart);

			return "result=updated";
		}

	}

	public String deleteCartLine(int cartLineId) {
		// TODO Auto-generated method stub

		CartLine cartLine = cartLineDAO.get(cartLineId);

		if (cartLine == null) {
			return "result=error";
		} else {

			// Update the cart
			Cart cart = this.getCart();

			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());

			cart.setCartLines(cart.getCartLines() - 1);

			cartLineDAO.updateCart(cart);

			// Remove the Cartline

			cartLineDAO.remove(cartLine);
			return "result=Deleted";

		}

	}

	public String addCartLine(int productId) {
		// TODO Auto-generated method stub
		String response = null;

		Cart cart = this.getCart();

		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);

		if (cartLine == null) {
			// Add new cartLine
			cartLine = new CartLine();

			// Fetch the Product

			Product product = productDAO.get(productId);

			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			cartLineDAO.add(cartLine);
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);

			response = "result=added";
		}

		else {
			Product product = productDAO.get(productId);
			cartLine.setProductCount(cartLine.getProductCount() + 1);
			cartLine.setTotal(cartLine.getTotal() + product.getUnitPrice());
			cartLineDAO.update(cartLine);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());

			cartLineDAO.updateCart(cart);

			response = "result=added";

		}

		return response;
	}

}
