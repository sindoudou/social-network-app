package com.sg.social.app.domain;

import java.util.Date;

public class Post {
	private String userName;
	private String message;
	private Date publishDate;

	public Post(String userName, String message) {
		this.userName = userName;
		this.message = message;
		this.publishDate = new Date();
	}

	public Post(String userName, String message, Date publishDate) {
		this.userName = userName;
		this.message = message;
		this.publishDate = publishDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}
