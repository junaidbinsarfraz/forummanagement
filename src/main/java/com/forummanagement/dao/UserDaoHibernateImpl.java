package com.forummanagement.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.dao.util.HibernateUtil;
import com.forummanagement.model.User;

public class UserDaoHibernateImpl implements UserDao {

	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	public User login(String username, String password) {

		User user = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("userName", username))
					.add(Restrictions.eq("userPass", password));

			user = (User) criteria.uniqueResult();

			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return user;
	}

	public StatusMessage register(User user) {
		
		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;
		
		user.setUserDate(new Date());

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(user);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("user", user);

			session.close();

		} catch (Exception e) {
			statusMessage.setStatusMessage(StatusMessage.FAILURE);

			if (tx != null) {
				tx.rollback();
			}

			if (session != null) {
				session.close();
			}

			System.err.println(e);
		}

		return statusMessage;
	}

	public List<User> getAllUsers() {

		List<User> users = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(User.class);

			users = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return users;
		
	}

	public User getUserByUsername(String username) {

		User user = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("userName", username));

			user = (User) criteria.uniqueResult();

			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return user;
		
	}

}
