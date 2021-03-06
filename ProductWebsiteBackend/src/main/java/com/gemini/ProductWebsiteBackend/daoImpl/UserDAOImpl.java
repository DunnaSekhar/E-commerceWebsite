package com.gemini.ProductWebsiteBackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gemini.ProductWebsiteBackend.dao.UserDAO;
import com.gemini.ProductWebsiteBackend.model.Address;
import com.gemini.ProductWebsiteBackend.model.Cart;
import com.gemini.ProductWebsiteBackend.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {

		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public User getEmailById(String email) {
		String selectQuery = "FROM User where email =: email";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class)
					.setParameter("email", email)
					.getSingleResult();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public Address getByBillingAddress(User user) {
		String selectQuery="FROM Address WHERE user =:user AND billing =:billing";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class)
					.setParameter("user",user)
					.setParameter("billing",true)
					.getSingleResult();
			
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddress(User user) {
		String selectQuery="FROM Address WHERE user =:user AND shipping =:shipping";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class)
					.setParameter("user",user)
					.setParameter("shipping",true)
					.getResultList();
			
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/*
	 * @Override public boolean addCart(Cart cart) { try {
	 * sessionFactory.getCurrentSession().persist(cart); return true; }
	 * 
	 * catch(Exception ex) { ex.printStackTrace(); return false; } }
	 */
	
	/*
	 * @Override public boolean updateCart(Cart cart) { try {
	 * sessionFactory.getCurrentSession().update(cart); return true; }
	 * 
	 * catch(Exception ex) { ex.printStackTrace(); return false; } }
	 */
}

