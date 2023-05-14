package com.penta.assignment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.penta.assignment.model.AdvisorModel;
import com.penta.assignment.model.UserModel;

public interface AdvisorRepository extends MongoRepository<AdvisorModel, String> {

	AdvisorModel findById(int teacherId); 

}
