package com.sg.social.app.repository;

import com.sg.social.app.domain.User;

public interface Repository {
	/**
	 * Retrieves the specified user from the repository, if the user does not
	 * exist, this method will create it.
	 * 
	 * @param userName
	 *            The username.
	 * @return The requested user.
	 * 
	 */
	public User getOrCreateUser(String userName);

}
