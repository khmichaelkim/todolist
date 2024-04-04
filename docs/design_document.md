# Design Document

## Portable To-Do List Design

## 1. Problem Statement

Existing to-do list applications offer diverse functionalities, catering to various preferences. 
However, two significant challenges persist: the difficulty in finding a to-do list app that precisely 
meets individual needs, and the cumbersome process involved in transferring to-do lists between applications. 

This document outlines the design for a portable, feature-rich to-do list API that integrates seamlessly 
with any chosen frontend interface, alongside a lightweight frontend demonstration.

## 2. Top Questions to Resolve in Review

1. Can the selected technology stack (API Gateway, Lambda functions, and DynamoDB) ensure a smooth and efficient user experience? 
2. What is the optimal structure for the DynamoDB tables to ensure scalability and performance, including the choice of partition and sort keys? 
3. How can we ensure data portability across different to-do list applications while maintaining user privacy and security?

## 3. Use Cases

U1. As a user, I want to create a new, named, empty to-do list

U2. As a user, I want to retrieve my to-do list using its unique ID

U3. As a user, I want to add an item to my to-do list

U4. As a user, I want to edit an item in my to-do list

U5. As a user, I want to delete an item from my to-do list

## 4. Project Scope

### 4.1. In Scope

* Creation, retrieval, and updating of to-do lists and their items

### 4.2. Out of Scope

* Development of an advanced frontend
* Direct integration or data import from other to-do list platforms

# 5. Proposed Architecture Overview

The architecture comprises three primary components:

* Frontend Interface: A lightweight, responsive web application facilitating user interactions with to-do lists.
* Backend API: Powered by AWS Lambda and API Gateway, this layer handles business logic, including CRUD operations for to-do lists and items.
* Data Storage: DynamoDB serves as the persistent storage mechanism, optimized for scalability and performance.

This structure promotes a clear separation of concerns, ensuring efficient data flow between user actions and 
database interactions. The use of serverless technologies (Lambda and API Gateway) enables scalability and 
reduces operational overhead, while DynamoDB provides a highly available and durable storage solution.

# 6. API

## 6.1. Public Models

```
// UserModel

String userId;
String email;
String createdAt;
```

```
// TaskModel

String userId; 
String taskId;
String content;
String dueDate;
Priority priority;
```

## 6.2 Create User Endpoint
* Endpoint: POST /users
* Functionality: Creates a new user with the given details. Returns the created user, including a unique user ID.
* Input Validation: Validates that the username is provided and adheres to any specific constraints (not detailed in the OpenAPI spec).
## 6.3 Retrieve User Endpoint
* Endpoint: GET /users/{userId}
* Functionality: Retrieves a user's details by their unique user ID.
* Failure Case: If the specified user ID does not exist, it returns a "User not found" error.
## 6.4 Create Task Endpoint
* Endpoint: POST /users/{userId}/tasks
* Functionality: Creates a new task associated with a specified user ID. Requires task details such as title, description, due date, priority, and status. Returns the created task with a unique task ID.
* Input Validation: Validates that the task title and status are provided, and checks for the validity of the due date format.
## 6.5 Retrieve Task Endpoint
* Endpoint: GET /users/{userId}/tasks/{taskId}
* Functionality: Retrieves a task's details by taskId for a specific user.
* Failure Case: If the specified task ID does not exist under the given user ID, it returns a "Task not found" error.
## 6.6 Update Task Endpoint
* Endpoint: PUT /users/{userId}/tasks/{taskId}
* Functionality: Updates an existing task's details (title, description, due date, priority, status) for a specified user. Returns the updated task.
* Failure Case: Throws an error if the task or user ID does not match existing records.
## 6.7 Delete Task Endpoint
* Endpoint: DELETE /users/{userId}/tasks/{taskId}
* Functionality: Deletes an existing task for a specified user.
* Failure Case: Returns an error if the task or user ID does not match existing records or if the task cannot be deleted.

# 7. Tables

Proposed table designs can be found here: `/docs/usersTable.yaml` `/docs/tasksTable.yaml`

# 8. Pages
![img.png](images%2Fimg.png)
* Enter username to GET the to-do list. Prompt creation if no to-do list exists or selection if multiple exist 

![img_1.png](images%2Fimg_1.png)
* View to-do list and items. Create new to-do list item

![img_2.png](images%2Fimg_2.png)
* Click on item to see details. Update/save or delete item
