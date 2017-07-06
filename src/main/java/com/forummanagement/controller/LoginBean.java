package com.forummanagement.controller;

import javax.servlet.http.HttpSession;

import com.forummanagement.common.controller.AbstractController;
import com.forummanagement.common.dao.DaoManager;
import com.forummanagement.common.model.StatusMessage;
import com.forummanagement.util.Constants;
import com.forummanagement.validation.ValidationUtil;
import com.forummanagement.dao.UserDao;
import com.forummanagement.model.User;

public class LoginBean extends AbstractController {

	private String username;
	private String password;
	
	private User user;	
	
	private String page = "login";
	
	private UserDao getUserDao() {
		return DaoManager.getInstance().getDao(UserDao.class);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	private boolean validate(Boolean signup) {
		getValidationErrors().clear();
		
		if(Boolean.FALSE.equals(signup)) {
			ValidationUtil.validateTextField(true, "Username", "Username", this.username, getValidationErrors());
			ValidationUtil.validateTextField(true, "Password", "Password", this.password, getValidationErrors());
		} else  {
			ValidationUtil.validateTextField(true, "Username", "Username", this.user.getUserName(), getValidationErrors());
			ValidationUtil.validateTextField(true, "Password", "Password", this.user.getUserPass(), getValidationErrors());
			ValidationUtil.validateTextField(true, "PasswordAgain", "PasswordAgain", this.username, getValidationErrors());
			ValidationUtil.validateEmail(true, "Email", "Email", this.user.getUserEmail(), getValidationErrors(), 500);
			ValidationUtil.validatePassword("No user password", this.user.getUserPass(), this.password, getValidationErrors()); // PasswordMisMatch field
		}
		
		return !getValidationErrors().getAnyError();
	}
	
	public String login() {
		
		if(validate(false)) {
			User user = getUserDao().login(this.username, this.password);
		
			if(user == null) {
				getValidationErrors().addError("InvalidLogin", "Invalide Username or Password");
			} else {
				HttpSession session = getSession(true);
				session.setAttribute("loginUser", user);
				
				// TODO: Change this
				getSessionBean().loadSessionData(user);
				return Constants.PAGE_SHOPPING_CART;
			}
		}
		
		return Constants.EMPTY_STRING;
	}
	
	public String signup() {
		
		if(validate(true)) {
			User user = getUserDao().getUserByUsername(this.user.getUserName());
			
			if(user == null) {
				StatusMessage statusMessage = getUserDao().register(this.user);
				
				if(StatusMessage.FAILURE.equals(statusMessage.getStatusMessage())) {
					getValidationErrors().addError("GeneralError", "Unable to register");
				} else {
					// TODO: Change it
					return Constants.PAGE_SHOPPING_CART; // 
				}
				
				/*user = (User) statusMessage.getObjects().get("user");
				
				HttpSession session = getSession(true);
				session.setAttribute("loginUser", user);
				
				// TODO: Change this
				getSessionBean().loadSessionData(user);
				return Constants.PAGE_SHOPPING_CART;*/
				
			} else {
				getValidationErrors().addError("Username", "User already exists");
			}
		}
		
		return Constants.EMPTY_STRING;
	}
	
	public String logout() {
		
		HttpSession session = getSession(false);

		if (session != null) {
			session.invalidate();
		}
		
		return Constants.PAGE_HOME;
	}
	
	public String gotoLogin() {
		this.page = "login";
		return Constants.PAGE_HOME;
	}
	
	public String gotoSignup() {
		this.page = "signup";
		return Constants.PAGE_HOME;
	}

}
