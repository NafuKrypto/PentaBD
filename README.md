# PentaBD

## Running the Spring Boot Application with MongoDB
To run the Spring Boot application with MongoDB, follow the below steps:

## Prerequisites
1. Java 8 or above
2. MongoDB installed and running on your machine
## Steps
Clone the repository by running the following command:
bash
 ```
 git clone https://github.com/<username>/<repository>.git
```
Open the project in your preferred IDE.

Update the application.properties file located in src/main/resources directory to match your MongoDB configuration:

 ```
 spring.data.mongodb.uri=mongodb://<username>:<password>@<host>:<port>/<database>
 ```
 3.Run the application from your IDE by running the main method in com.example.demo.DemoApplication class.

Once the application is up and running, you can access the API documentation.
## Admin Controller API
The following endpoints are available for the admin controller:

Get all teachers
 
```
GET /admin/teachers
```
Returns a list of all teachers.

Get all students
```
GET /admin/students
```
Returns a list of all students.

Add a teacher
 
```
POST /admin/teachers
```
Adds a new teacher.

Request Body:

json
Copy code
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com",
  "password": "password"
}
Add a student
```
POST /admin/students
```
Adds a new student.

Request Body:

json
Copy code
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "janedoe@example.com",
  "password": "password"
}
Add a role to a user
```
PUT /admin/users/{userId}/role/{role}
```
Adds a role to a user.

Deactivate a user
```
DELETE /admin/users/{userId}
```
Deactivates a user.

Assign a teacher to a student
```
POST /admin/assign-teacher/{teacherId}/{studentId}
```
Assigns a teacher to a student.

Returns:

200 OK if successful
400 Bad Request if unsuccessful
Request Parameters:

teacherId: The ID of the teacher to assign to the student.
studentId: The ID of the student to assign the teacher to.


##
# Registration 
```
POST /registered
```
# authentication 
```
POST /auth: 
```

## Get Student Profile
```
GET /students/{studentId}
```
 
## Student Controller
This controller handles all the endpoints related to student profile and interactions.
# Get Student Profile
```
PUT /students/{studentId}
```
# Update Student Profile
```
PUT /students/{studentId}
```
# Reset Student Password
```
PUT /students/{studentId}/password
```

# Send Advisor Request
```
POST /students/{studentId}/request/{teacherId}
```
Sends a request for a student with the given studentId to be assigned to a teacher with the given teacherId








