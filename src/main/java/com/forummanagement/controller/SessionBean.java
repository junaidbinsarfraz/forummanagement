package com.forummanagement.controller;

import com.forummanagement.common.controller.AbstractController;
import com.forummanagement.model.User;

public class SessionBean extends AbstractController {

	private User user;
	private Boolean userLoggedIn = false;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userLoggedIn
	 */
	public Boolean getUserLoggedIn() {
		return userLoggedIn;
	}

	/**
	 * @param userLoggedIn
	 *            the userLoggedIn to set
	 */
	public void setUserLoggedIn(Boolean userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}
	
	public void loadSessionData(User user) {
		
		this.user = user;
		this.userLoggedIn = true;
	}
}
