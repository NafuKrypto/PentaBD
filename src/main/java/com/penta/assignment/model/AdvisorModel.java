package com.penta.assignment.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="advisors")
public class AdvisorModel {
	 @Id
	private int teacher_id;
	private List<UserModel> student_ids;
	public AdvisorModel(){
		
	}
	public AdvisorModel(int teacher_id,List<UserModel> student_ids){
		this.teacher_id=teacher_id;
		this.student_ids=student_ids;
	}
	
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public List<UserModel> getStudent_ids() {
		return student_ids;
	}
	public void setStudent_ids(List<UserModel> student_ids) {
		this.student_ids = student_ids;
	}
}
