package com.forummanagement.dao;

import java.util.List;

import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.model.Post;

public interface PostDao {

	StatusMessage savePost(Post post);
	
	List<Post> getAllPosts();
	
}
