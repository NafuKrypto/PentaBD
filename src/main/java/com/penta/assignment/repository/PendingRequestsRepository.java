package com.penta.assignment.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.penta.assignment.model.AdvisorModel;
import com.penta.assignment.model.PendingModel;
import com.penta.assignment.model.UserModel;

public interface PendingRequestsRepository extends MongoRepository< PendingModel, String>{

	PendingModel findById(int student_id);

	void deleteById(int student_id);
}
