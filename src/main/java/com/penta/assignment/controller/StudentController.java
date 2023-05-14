package com.penta.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.penta.assignment.model.UserModel;
import com.penta.assignment.service.UserService;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private UserService userService;

 

  @GetMapping("/{studentId}")
  public UserModel getStudentProfile(@PathVariable int studentId) throws Exception {
    return userService.getUserById(studentId);
  }

  @PutMapping("/{studentId}")
  public UserModel updateStudentProfile(@PathVariable int studentId, @RequestBody UserModel user) throws Exception {
    return userService.updateUser(studentId, user);
  }

  @PutMapping("/{studentId}/password")
  public UserModel resetStudentPassword(@PathVariable int studentId, @RequestBody String newPassword) throws Exception {
    return userService.resetUserPassword(studentId, newPassword);
  }

  @PostMapping("/{studentId}/request/{teacherId}")
  public UserModel sendAdvisorRequest(@PathVariable int studentId, @PathVariable int teacherId) {
    return userService.sendAdvisorRequest(studentId,teacherId);
  }
}


