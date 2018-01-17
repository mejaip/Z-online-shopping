package net.mejaip.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mejaip.shoppingbackend.dao.ProductDAO;
import net.mejaip.shoppingbackend.dto.Product;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;

	private static ProductDAO productDAO;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mejaip.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	/*
	 * @Test public void testCRUDProduct() {
	 * 
	 * Product product = new Product(); product.setName("JAiphonr");
	 * product.setBrand("Jai apple");
	 * product.setDescription("Sell your kidny and buy this phone");
	 * product.setUnitPrice(170000); product.setQuantity(0);
	 * product.setActive(true); product.setCategoryId(3); product.setSupplierId(3);
	 * 
	 * assertEquals("Something went wrong while inserting product", true,
	 * productDAO.add(product));
	 * 
	 * 
	 * 
	 * product = productDAO.get(15); product.setName("Samsug Galaxy");
	 * assertEquals("Something went wrong while updating existing product", true,
	 * productDAO.update(product));
	 * 
	 * product = productDAO.get(15);
	 * assertEquals("Something went Erong while Deleting existing product", true,
	 * productDAO.delete(product));
	 * 
	 * assertEquals("something went wrong while fetching product List", 10,
	 * productDAO.list().size());
	 * 
	 * 
	 * }
	 */

	@Test public void testListActiveProducts() {
	 assertEquals("Something Went wrong while fetching List", 7, productDAO.listActiveProducts().size());
	 }
	
	 @Test 
	 public void testListActiveProductsBycategory() {
	  
	 assertEquals("Something Went wrong while fetching List", 3, productDAO.listActiveProductsByCategory(3).size()); 
	 } 
	 @Test
	 public void testLatestActiveProducts() {
	  
	  assertEquals("Something Went wrong while fetching List", 3,  productDAO.getLatestActiveProducts(3).size());
	  System.out.println("Something Went wrong while fetching List\n");
	  System.out.println("Something Went wrong while fetching List\n");
	  }
}
