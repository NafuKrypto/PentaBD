package com.penta.assignment.model;

import org.springframework.data.annotation.Id;

public class Role {
	public static final String TEACHER = null;


	@Id
    private long id;

 
    public Role(long id,String role) {
		super();
		this.id = id;
		this.role = role;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	private String role;

}
