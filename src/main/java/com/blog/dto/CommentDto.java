package com.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDto {
	private long id;
	
	@NotEmpty(message = "Name should not be empty.")
	private String name;
	
	@NotEmpty(message = "Email should not be empty.")
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 10, message = "Comment must be minimum 10 characters.")
	private String body;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

}
