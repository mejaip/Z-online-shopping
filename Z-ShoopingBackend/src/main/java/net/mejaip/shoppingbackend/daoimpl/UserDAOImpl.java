package net.mejaip.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.mejaip.shoppingbackend.dao.UserDAO;
import net.mejaip.shoppingbackend.dto.Address;
import net.mejaip.shoppingbackend.dto.Cart;
import net.mejaip.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub

		String selectQuery = "FROM User WHERE email = :email";

		System.out.println("Here the query is =========" + selectQuery);

		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Address getBillingAddress(User user) {
		// TODO Auto-generated method stub
		String selectQuery = "FROM Address where user = :user AND billing = :billing";

		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("billing", true).getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Address> listShippingAddress(User user) {
		String selectQuery = "FROM Address where user = :user AND shipping = :shipping";

		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("shipping", true).getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
