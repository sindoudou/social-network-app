package com.sg.social.app.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private List<Post> posts = new ArrayList<Post>();
	private List<User> followingUsers = new ArrayList<User>();

	public User(String userName) {
		this.name = userName;
	}

	public void addPost(String message) {
		posts.add(new Post(name, message));
	}

	public void addFollowing(User user) {
		if (!followingUsers.contains(user)) {
			followingUsers.add(user);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<User> getFollowingUsers() {
		return followingUsers;
	}

	public void setFollowingUsers(List<User> followingUsers) {
		this.followingUsers = followingUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}
}
