package net.mejaip.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mejaip.shoppingbackend.dao.ProductDAO;
import net.mejaip.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Product get(int id) {
		try {
			return sessionfactory.getCurrentSession().get(Product.class, Integer.valueOf(id));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		String selectAtctiveCategory = "From Product";
		Query query = sessionfactory.getCurrentSession().createQuery(selectAtctiveCategory, Product.class);
		// query.setParameter("active", true);
		return query.getResultList();
	}

	@Override
	public boolean add(Product product) {
		// TODO Auto-generated method stub
		try {
			sessionfactory.getCurrentSession().persist(product);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(Product product) {
		// TODO Auto-generated method stub

		try {
			sessionfactory.getCurrentSession().update(product);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		// TODO Auto-generated method stub
		product.setActive(false);
		try {
			sessionfactory.getCurrentSession().update(product);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Product> listActiveProducts() {
		// TODO Auto-generated method stub
		String selectActiveProducts = "From Product where is_active = :active";
		return sessionfactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();

	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		// TODO Auto-generated method stub
		String selectActiveProductsBycategory = "From Product where is_active = :active AND category_id=:categoryID";
		return sessionfactory.getCurrentSession().createQuery(selectActiveProductsBycategory, Product.class)
				.setParameter("active", true).setParameter("categoryID", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		// TODO Auto-generated method stub
		return sessionfactory.getCurrentSession()
				.createQuery("From Product where active =:active ORDER BY id", Product.class)
				.setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
	}

}
