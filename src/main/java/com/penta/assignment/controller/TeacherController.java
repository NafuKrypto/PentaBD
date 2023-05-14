package com.penta.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.penta.assignment.model.UserModel;
import com.penta.assignment.service.UserService;
 

@RestController
	@RequestMapping("/teachers")
	public class TeacherController {

	  @Autowired
	  private UserService userService;

	  @Autowired
//	  private StudentService studentService;

	  @GetMapping("/{teacherId}")
	  public UserModel getTeacherProfile(@PathVariable int teacherId) throws Exception {
	    return userService.getUserById(teacherId);
	  }

	  @PutMapping("/{teacherId}")
	  public UserModel updateTeacherProfile(@PathVariable int teacherId, @RequestBody UserModel user) throws Exception {
	    return userService.updateUser(teacherId, user);
	  }

	  @PutMapping("/{teacherId}/password")
	  public UserModel resetTeacherPassword(@PathVariable int teacherId, @RequestBody String newPassword) throws Exception {
	    return userService.resetUserPassword(teacherId, newPassword);
	  }

	  @GetMapping("/{teacherId}/students")
	  public List<UserModel> getStudentsByAdvisor(@PathVariable int teacherId) {
	    return userService.getStudentsByAdvisor(teacherId);
	  }

	  @PutMapping("/{teacherId}/students/{studentId}/accept")
	  public UserModel acceptStudentRequest(@PathVariable int teacherId, @PathVariable int studentId) throws Exception {
	    return userService.acceptStudentRequest(teacherId, studentId);
	  }

	  @PutMapping("/{teacherId}/students/{studentId}/dismiss")
	  public List<UserModel> dismissStudentRequest(@PathVariable int teacherId, @PathVariable int studentId) throws Exception {
	    return userService.dismissStudentRequest(teacherId, studentId);
	  }
	  
	  @DeleteMapping("/{teacherId}/students/{studentId}")
	  public List<UserModel> removeStudentFromAdvisorList(@PathVariable int teacherId, @PathVariable int studentId) throws Exception {
	    return userService.removeStudentFromAdvisorList(teacherId, studentId);
	  }
	  


	}
