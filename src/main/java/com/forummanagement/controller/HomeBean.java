package com.forummanagement.controller;

import java.util.ArrayList;
import java.util.List;

import com.forummanagement.model.Category;
import com.forummanagement.model.Post;
import com.forummanagement.model.Topic;

public class HomeBean extends HomeController {

	Category category = new Category();
	Topic topic = new Topic();
	Post post = new Post();

	List<Category> categories = new ArrayList<Category>();
	List<Topic> topics = new ArrayList<Topic>();
	List<Post> posts = new ArrayList<Post>();

	// TODO: Contact us form
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


}
