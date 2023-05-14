package com.penta.assignment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.penta.assignment.model.Role;
import com.penta.assignment.model.UserModel;

@Repository
public interface RoleRepository extends MongoRepository<Role, String>{
    List<UserModel> findByRole(String role);

	Role findById(int teacherId);
}
