package com.forummanagement.dao;

import java.util.List;

import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.model.Topic;

public interface TopicDao {
	
	StatusMessage saveTopic(Topic topic);
	
	List<Topic> getAllTopics();
	
}
