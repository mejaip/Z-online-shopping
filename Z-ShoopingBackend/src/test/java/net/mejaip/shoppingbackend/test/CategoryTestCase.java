package net.mejaip.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mejaip.shoppingbackend.dao.CategoryDAO;
import net.mejaip.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mejaip.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/*
	 * @Test public void testAddCategory() { category = new Category();
	 * 
	 * category.setName("Test-1");
	 * category.setDescription("Description of Television");
	 * category.setImageURL("CAT_1.jpg");
	 * System.out.println("Persisting ................" + category); //
	 * category.setActive(true);
	 * assertEquals("Successfully Added the Category inside the table", true,
	 * categoryDAO.add(category)); }
	 */

	/*
	 * @Test public void testGetCategory() { category = categoryDAO.get(3);
	 * 
	 * assertEquals("Successfully fetched single category", "Mobile",
	 * category.getName()); }
	 */

	/*
	 * @Test public void testUpdateCategory() { category = categoryDAO.get(5);
	 * category.setName("Cooler");
	 * 
	 * assertEquals("Successfully updated single category", true,
	 * categoryDAO.update(category)); }
	 */

	/*
	 * @Test public void testDeleteCategory() { category = categoryDAO.get(5);
	 * assertEquals("Successfully updated single category", true,
	 * categoryDAO.delete(category)); }
	 */
	/*
	 * @Test public void testListCategory() {
	 * assertEquals("Successfully fetched List category", 4,
	 * categoryDAO.list().size()); }
	 */

	@Test
	public void testCRUDCategory() {
		category = new Category();

		category.setName("cooler");
		category.setDescription("Description of Television");
		category.setImageURL("CAT_1.jpg");
		System.out.println("Persisting ................" + category);
		assertEquals("Successfully Added the Category inside the table", true, categoryDAO.add(category));

		category = categoryDAO.get(8);
		category.setName("Cooler");

		assertEquals("Successfully updated single category", true, categoryDAO.update(category));

		category = categoryDAO.get(8);
		assertEquals("Successfully updated single category", true, categoryDAO.delete(category));

		assertEquals("Successfully fetched List category", 4, categoryDAO.list().size());

	}
}
