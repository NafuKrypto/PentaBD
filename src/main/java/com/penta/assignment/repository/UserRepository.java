package com.penta.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.penta.assignment.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{
UserModel findByEmail(String email);
UserModel findByRole(String role);
Optional<UserModel> findById(int userId);
Object deleteAllById(int userId);
List<UserModel> findByRoleAndAdvisor_id(String string, int teacherId);
 
UserModel getUserById(int teacherId);
//Optional<UserModel> findById(int teacherId);
}

