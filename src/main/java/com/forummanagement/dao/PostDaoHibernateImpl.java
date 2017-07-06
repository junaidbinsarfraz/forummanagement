package com.forummanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.dao.util.HibernateUtil;
import com.forummanagement.model.Post;

public class PostDaoHibernateImpl implements PostDao {
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	public StatusMessage savePost(Post post) {

		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(post);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("user", post);

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

	public List<Post> getAllPosts() {

		List<Post> posts = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Post.class);

			posts = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return posts;
		
	}

}
