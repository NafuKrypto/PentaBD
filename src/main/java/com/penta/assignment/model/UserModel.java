package com.penta.assignment.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserModel {
    @Id
	private int id;
	private String name;
	private String email;
	private String phone;
	private String department_name;
	private int advisor_id;
	private String password;
	public UserModel() {
		
	}
	 public UserModel(int id, String name, String email, String password,String department_name,int advisor_id,String phone) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.department_name = department_name;
	        this.advisor_id = advisor_id;
	        this.phone = phone;
	    }
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public int getAdvisorId() {
		return advisor_id;
	}
	public void setAdvisorId(int teacherId) {
		this.advisor_id = teacherId;
	}
	 
	 
	 
	
	
}
