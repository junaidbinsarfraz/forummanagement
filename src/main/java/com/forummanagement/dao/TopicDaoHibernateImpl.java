package com.forummanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.dao.util.HibernateUtil;
import com.forummanagement.model.Topic;

public class TopicDaoHibernateImpl implements TopicDao {
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	public StatusMessage saveTopic(Topic topic) {

		StatusMessage statusMessage = new StatusMessage();

		Session session = null;
		Transaction tx = null;

		try {

			session = getSession();

			tx = session.beginTransaction();
			session.saveOrUpdate(topic);
			tx.commit();

			statusMessage.setStatusMessage(StatusMessage.SUCCESS);
			statusMessage.getObjects().put("topic", topic);

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

	public List<Topic> getAllTopics() {

		List<Topic> topics = null;

		try {

			Session session = getSession();

			Criteria criteria = session.createCriteria(Topic.class);

			topics = criteria.list();
			
			session.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return topics;
		
	}

}
