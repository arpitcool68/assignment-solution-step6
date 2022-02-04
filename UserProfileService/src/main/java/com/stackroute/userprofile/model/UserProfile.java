package com.stackroute.userprofile.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user.profile")
public class UserProfile {

	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String contact;
	private String email;
	private Date createdAt;

    
	public UserProfile() {
		super();
	}

	public UserProfile(String userId, String firstName, String lastName, String contact, String email) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt() {
		LocalDateTime dateTime = LocalDateTime.now();
		Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
		this.createdAt = Date.from(i);
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", contact="
				+ contact + ", email=" + email + ", createdAt=" + createdAt + "]";
	}
	
	
    
    
}
