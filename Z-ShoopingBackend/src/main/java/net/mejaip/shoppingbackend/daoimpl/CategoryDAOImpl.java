package net.mejaip.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mejaip.shoppingbackend.dao.CategoryDAO;
import net.mejaip.shoppingbackend.dto.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private static List<Category> categories = new ArrayList<>();

	static {
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("Description of Television");
		category.setImageURL("CAT_1.jpg");
		category.setActive(true);

		categories.add(category);

		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Description of Mobile");
		category.setImageURL("CAT_2.jpg");
		category.setActive(true);

		categories.add(category);

		category = new Category();
		category.setId(3);
		category.setName("Camera");
		category.setDescription("Description of Camera");
		category.setImageURL("CAT_3.jpg");
		category.setActive(true);

		categories.add(category);

	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {

		for (Category category : categories) {
			if (category.getId() == id)
				return category;

		}
		return null;
	}

	@Override
	public boolean add(Category category) {
		try {
				sessionFactory.getCurrentSession().persist(category);
			
			
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
}
