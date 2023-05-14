package com.penta.assignment.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="pendingRequest")
public class PendingModel {
    @Id
	private int student_id;
	 
	 
	private int advisor_id;
	private String status;
	public PendingModel() {
		
	}
	 public PendingModel(int student_id,  int advisor_id,String status) {
	        this.student_id = student_id;
	        this.advisor_id = advisor_id;
	        this.status = status;
	    }
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getAdvisor_id() {
		return advisor_id;
	}
	public void setAdvisor_id(int advisor_id) {
		this.advisor_id = advisor_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	 
	 
	 
	 
	
	
}
