package com.forummanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.dao.util.HibernateUtil;
import com.forummanagement.model.Category;

public class CategoryDaoHibernateImpl implements CategoryDao {
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	public StatusMessage saveCategory(Category category) {

		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(category);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("category", category);

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

	public List<Category> getAllCategories() {

		List<Category> categories = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Category.class);

			categories = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return categories;
		
	}

}
