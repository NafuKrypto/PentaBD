package com.penta.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.penta.assignment.model.AdvisorModel;
import com.penta.assignment.model.PendingModel;
import com.penta.assignment.model.Role;
import com.penta.assignment.model.UserModel;
import com.penta.assignment.repository.AdvisorRepository;
import com.penta.assignment.repository.PendingRequestsRepository;
import com.penta.assignment.repository.RoleRepository;
import com.penta.assignment.repository.UserRepository;
import java.util.ArrayList;


@Service
public class UserService implements UserDetailsService {
	
    @Autowired
	private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AdvisorRepository advisorRepository;
    private PendingRequestsRepository pendingRequestsRepository;
//    private UserModel userModel;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel foundedUser = userRepository.findByEmail(email);
		if (foundedUser==null) return null;
	    
		String emailUser=foundedUser.getEmail();
		String passwordUser=foundedUser.getPassword();
		return new User(emailUser,passwordUser,null);
	}
	
	public List<UserModel> getAllTeachers() {
		// TODO Auto-generated method stub 
		 List<UserModel> teacherModels = (List<UserModel>) userRepository.findByRole("teacher");
	        return teacherModels;
	}

	public List<UserModel> getAllStudents() {
		// TODO Auto-generated method stub
		List<UserModel> studentModels = (List<UserModel>) userRepository.findByRole("student");
        return studentModels;
	}

	public UserModel addUser(UserModel user ) {
		// TODO Auto-generated method stub
		 UserModel userModel = new UserModel();
//		 Role roleModel=new Role(role.getId(), role.getRole());
	        userModel.setId(user.getId());
	        userModel.setName(user.getName());
	        userModel.setEmail(user.getEmail());
	        userModel.setPassword(user.getPassword());
	        userModel.setDepartment_name(user.getDepartment_name());
	        userModel.setPhone(user.getPhone());
	        userModel.setAdvisorId(0); 
//	        0 for default
	       

	        UserModel savedUser = userRepository.save(userModel);
//            Role savedRole=roleRepository.save(role);
	        return new UserModel(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPassword(), null, 0, null);
	}

	public Role addRoleToUser(Role role) {
		// TODO Auto-generated method stub
//		Role roleModel=new Role(role.getId(),);
//		roleModel.setId(userId);
//		roleModel.setRole(role);
		Role savedRole=roleRepository.save(role);
		return new Role(savedRole.getId(),savedRole.getRole()) ;
	}

	public void deactivateUser(int userId) {
		Optional<UserModel> optionalUserModel = userRepository.findById(userId);
	    if (optionalUserModel.isPresent()) {
//	        UserModel userModel = optionalUserModel.get();
	       
	        userRepository.deleteAllById(userId);
	    } else {
	        throw new RuntimeException("User not found with id: " + userId);
	    }
		
		// TODO Auto-generated method stub
		
	}

	public void assignTeacherToStudent(int studentId, int teacherId) throws Exception {
		// TODO Auto-generated method stub
		 	UserModel student = userRepository.findById(studentId).orElseThrow(() -> new Exception("Student not found"));
		    UserModel teacher = userRepository.findById(teacherId).orElseThrow(() -> new Exception("Teacher not found"));
		    Role teacherRole= roleRepository.findById(teacherId);
		    if (student.getAdvisorId() != 0) {
		        throw new Exception("Student already has an advisor");
		    }

		    if (!teacherRole.getRole().equals("teacher")) {
		        throw new Exception("User is not a teacher");
		    }

		    student.setAdvisorId(teacher.getId());
		    userRepository.save(student);
	}

	public UserModel getUserById(int teacherId) throws Exception{
		// TODO Auto-generated method stub
		Optional<UserModel> userModelOptional = userRepository.findById(teacherId);
	    if (userModelOptional.isPresent()) {
	        return userModelOptional.get();
	    } else {
	        // handle not found case
	    	throw new Exception("User not found");
	    }
	 
	}

	public UserModel updateUser(int teacherId, UserModel user) throws Exception {
		// TODO Auto-generated method stub
		 Optional<UserModel> optionalUser = userRepository.findById(teacherId);
		    if (optionalUser.isPresent()) {
		        UserModel existingUser = optionalUser.get();
		        existingUser.setName(user.getName());
		        existingUser.setEmail(user.getEmail());
		        existingUser.setPhone(user.getPhone());
		        existingUser.setDepartment_name(user.getDepartment_name());
		        existingUser.setPassword(user.getPassword());
		        existingUser.setAdvisorId(user.getAdvisorId());
		        return userRepository.save(existingUser);
		    } else {
		        throw new IllegalArgumentException("User not found with id: " + teacherId);
		    }
		 
	}

	public UserModel resetUserPassword(int teacherId, String newPassword)throws Exception {
	    Optional<UserModel> optionalUserModel = userRepository.findById(teacherId);
	    
	    if (optionalUserModel.isPresent()) {
	        UserModel userModel = optionalUserModel.get();
	        userModel.setPassword(newPassword);
	        return userRepository.save(userModel);
	    }else {
	    	throw new IllegalArgumentException("User not found with id: " + teacherId);
	    }
	    
	      // user not found
	}

	public List<UserModel> getStudentsByAdvisor(int teacherId) {
		// TODO Auto-generated method stub
		 List<UserModel> studentModels = userRepository.findByRoleAndAdvisor_id("student", teacherId);
	        return studentModels;
		 
	}

	public UserModel acceptStudentRequest(int teacherId, int studentId) throws Exception {
		// TODO Auto-generated method stub
	    UserModel teacher = userRepository.getUserById(teacherId);
	    Role roleTeacher=roleRepository.findById(teacherId);
	    UserModel student = userRepository.getUserById(studentId);
	    Role roleStudent=roleRepository.findById(teacherId); 
	    List<UserModel> students= getStudentsByAdvisor(teacher.getId());
	    
	    if (teacher != null && student != null && roleTeacher.getRole().equals("teacher") && roleStudent.getRole().equals("student")) {
	        student.setAdvisorId(teacher.getId());
	        UserModel saveUser=userRepository.save(student);
	        AdvisorModel advisorModel=new AdvisorModel(teacherId,students);
	        AdvisorModel savedAdvisor=advisorRepository.save(advisorModel );
	        return  saveUser;
	    } else {
	        throw new IllegalArgumentException("Invalid teacher or student ID");
	    }
	}

	@SuppressWarnings("unchecked")
	public List<UserModel> dismissStudentRequest(int teacherId, int studentId) throws Exception {
		// TODO Auto-generated method stub
		
		   UserModel teacher = userRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
		  List<UserModel> students= getStudentsByAdvisor(teacher.getId());
		    if (students.contains(studentId)) {
		    	AdvisorModel advisorModel=new AdvisorModel(teacherId,students);
		        AdvisorModel advisor=advisorRepository.findById(teacherId);
		        students.remove(studentId);
		        AdvisorModel saved=advisorRepository.save(advisorModel);
		        PendingModel pending=pendingRequestsRepository.findById(studentId) ;
		        pendingRequestsRepository.deleteById(pending.getStudent_id());
//		        teacher.setStudentIds(students);
//		        userRepository.save(teacher);
		        return  (List<UserModel>) saved;
		    }else {
		    	throw new Exception("User not found");
		    }
	
	}

	public List<UserModel> removeStudentFromAdvisorList(int teacherId, int studentId) throws Exception {
		// TODO Auto-generated method stub
		 UserModel teacher = userRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
		  List<UserModel> students= getStudentsByAdvisor(teacher.getId());
		    if (students.contains(studentId)) {
		    	AdvisorModel advisorModel=new AdvisorModel(teacherId,students);
		        AdvisorModel advisor=advisorRepository.findById(teacherId);
		        students.remove(studentId);
		        AdvisorModel saved=advisorRepository.save(advisorModel);
		        PendingModel pending=pendingRequestsRepository.findById(studentId) ;
		        pendingRequestsRepository.deleteById(pending.getStudent_id());
//			    PendingModel pendingSave= pendingRequestsRepository.save(pendingModel);
			    
//		        teacher.setStudentIds(students);
//		        userRepository.save(teacher);
		        return  (List<UserModel>) saved;
		    }else {
		    	throw new Exception("User not found");
		    }
	}

	public PendingModel sendAdvisorRequest(int studentId, int teacherId) {
		// TODO Auto-generated method stub
		
		UserModel student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));;
		UserModel teacher = userRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));;
	    
		Role teacherRole=roleRepository.findById(teacherId);
		
//        UserModel studentModel=new UserModel(student)
	    // check if student and teacher exist
	    if (student == null || teacher == null) {
	        return null;
	    }

	    // check if the teacher is not already the advisor of the student
	    if (student.getAdvisorId()!= 0 && student.getAdvisorId() == teacherId) {
	        return null;
	    }	

	    // check if the teacher is a teacher
	    if (!teacherRole.getRole().equals("teacher")) {
	        return null;
	    }

	    // add the teacher to. the list of advisor requests of the student
	    PendingModel pendingModel = new PendingModel(studentId,teacherId,"pending") ;
	    PendingModel pendingSave= pendingRequestsRepository.save(pendingModel);
	    // update the student in the repository
	     

	    return pendingModel;
	}
	
	


	 

}
