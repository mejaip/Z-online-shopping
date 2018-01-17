package net.mejaip.shoppingbackend.dao;

import java.util.List;

import net.mejaip.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	
	Category get(int id);
	
	boolean add(Category category);
	
	boolean delete(Category category);
	
	boolean update(Category category);
	

}
