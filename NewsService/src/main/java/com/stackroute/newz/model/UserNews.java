package com.stackroute.newz.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user.news")
public class UserNews {

	@Id
	private String userId;
	private List<News> newsList;

	public UserNews(String userId, List<News> newsList) {
		super();
		this.userId = userId;
		this.newsList = newsList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<News> getNewslist() {
		return newsList;
	}

	public void setNewslist(List<News> newsList) {
		this.newsList = newsList;
	}

	public UserNews() {
		super();
	}

	@Override
	public String toString() {
		return "UserNews [userId=" + userId + ", newsList=" + newsList + "]";
	}

}
