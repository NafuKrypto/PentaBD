package com.penta.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.penta.assignment.model.Role;
import com.penta.assignment.model.UserModel;
import com.penta.assignment.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	 @Autowired
	  private UserService userService;

	  @GetMapping("/teachers")
	  public List<UserModel> getAllTeachers() {
	    return userService.getAllTeachers();
	  }

	  @GetMapping("/students")
	  public List<UserModel> getAllStudents() {
	    return userService.getAllStudents();
	  }

	  @PostMapping("/teachers")
	  public UserModel addTeacher(@RequestBody UserModel user) {
	     
	    return userService.addUser(user);
	  }

	  @PostMapping("/students")
	  public UserModel addStudent(@RequestBody UserModel user) {
//		  Role role=new Role(user.getId(),"student");
	    return userService.addUser(user);
	  }

	  @PutMapping("/users/{userId}/role/{role}")
	  public Role addRoleToUser(@PathVariable Long userId, @PathVariable Role role) {
		  role.setId(userId);
	    return userService.addRoleToUser(role);
	  }
	  @DeleteMapping("/users/{userId}")
	  public void deactivateUser(@PathVariable Long userId) {
	    userService.deactivateUser(userId);
	  }
	  
	  @PostMapping("/assign-teacher/{teacherId}/{studentId}")
	  public ResponseEntity<String> assignTeacherToStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
	      try {
	          userService.assignTeacherToStudent(studentId, teacherId);
	          return ResponseEntity.ok("Teacher assigned to student successfully");
	      } catch (Exception e) {
	          return ResponseEntity.badRequest().body("Error assigning teacher to student");
	      }
	  }

	  

}
