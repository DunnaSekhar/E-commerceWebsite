package com.gemini.ProductWebsiteBackend.daoImpl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gemini.ProductWebsiteBackend.dao.CartLineDAO;
import com.gemini.ProductWebsiteBackend.model.Cart;
import com.gemini.ProductWebsiteBackend.model.CartLine;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId=:cartId";
		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
				.getResultList();
	}

	@Override
	public CartLine get(int id) {
		return sessionFactory.getCurrentSession().get(CartLine.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLine WHERE cartId=: cartId AND product.id=:productId";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
					.setParameter("productId", productId).getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId=: cartId AND available=:available";
		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
				.setParameter("available", true).getResultList();
	}
}