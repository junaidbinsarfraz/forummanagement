package com.forummanagement.dao;

import java.util.List;

import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.model.Category;

public interface CategoryDao {

	StatusMessage saveCategory(Category category);
	
	List<Category> getAllCategories();
	
}
